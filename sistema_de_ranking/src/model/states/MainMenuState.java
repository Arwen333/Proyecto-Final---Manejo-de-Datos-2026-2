package model.states;

import java.util.Scanner;
import exceptions.MenuOptionNotFoundException;
import view.components.Menu;
import view.utils.InputValidator;
import controller.dao.TestDAO;  // ← AGREGAR ESTA IMPORTACIÓN

/**
 * Despliega el menú principal y permite al usuario elegir una opción dentro de las
 * funciones de la aplicación.
 * 
 * @author Brayan Montiel
 */
public class MainMenuState implements IState {

    //#region ENUMERACIONES
    /**
     * Contiene opciones en un formato llave-mensaje enumeradas, correspondientes
     * al menú principal que este {@code MainMenuState} despliega.
     */
    private enum MainMenuOptions {
        // Opciones del menú principal, con su llave y mensaje asociado:
        RegisterPlayer('a', "Registrar jugador"),
        ShowPlayers('b', "Mostrar jugadores"),
        SearchPlayer('c', "Buscar jugador"),
        PlayerHistory('d', "Mostrar historial de partidas por jugador"),
        CreateMatch('e', "Crear partida"),
        ConsultRanking('f', "Consultar ranking general"),
        ExecuteMatch('g', "Ejecutar siguiente partida"),
        Exit('h', "Salir");

        // Campo:
        public final char key;
        public final String message;

        // Construcción:
        /**
         * Crea un par llave-mensaje asociado a una enumeración,
         * para representar una opción del menú principal.
         * @param key
         * @param message
         */
        private MainMenuOptions(char key, String message) {
            this.key = key;
            this.message = message;
        }

        // Consulta:
        /**
         * Regresa una opción identificada de acuerdo a su caracter llave.
         * @param key - Llave de la opción buscada.
         * @return La opción que cuya llave es el caracter {@code key}.
         * @throws MenuOptionNotFoundException Si ninguna opción tiene la llave {@code key}. 
         */
        public static MainMenuOptions fromKey(char key) throws MenuOptionNotFoundException {
            for (var option : MainMenuOptions.values()) {
                if (option.key == key) return option;
            }
            throw new MenuOptionNotFoundException("Ninguna opción contiene la llave \'" + key + "\'");
        }
        
    }
    //#endregion

    //#region CAMPO

    // Menú que el estado despliega.
    private static final Menu MAIN_MENU = new Menu();

    //#endregion

    //#region IMPLEMENTACIONES

    public IState execute(Scanner sc) {
        MAIN_MENU.print("Seleccione la opción deseada:");
        
        try {
            MainMenuOptions option = MainMenuOptions.fromKey(
                InputValidator.nextChar(sc)
            );
            System.out.println("Ha elegido la opción " + option.key + ") " + option.message);
            
            // Crear una instancia de TestDAO para simular la base de datos
            TestDAO testDAO = new TestDAO();
            
            switch (option) {
                case RegisterPlayer:
                    return new RegisterPlayerState();        // Lo ajusta Brayan
                case ShowPlayers:
                    return new ShowPlayersState();           // Lo ajusta Brayan
                case SearchPlayer:
                    return new SearchPlayerState();          // Lo ajusta Brayan
                case PlayerHistory:
                    return new PlayerHistoryState(testDAO, testDAO);
                case CreateMatch:
                    return new CreateMatchState(testDAO, testDAO);
                case ConsultRanking:
                    return new ConsultRankingState(testDAO);
                case ExecuteMatch:
                    return new ExecuteMatchState(testDAO, testDAO, testDAO);
                case Exit:
                    return new ExitState();
            }
        } catch (MenuOptionNotFoundException e) {
            System.out.println("Ingresó una opción que no se encuentra en el menú. Intente de nuevo.");
        } catch (RuntimeException e) {
            System.out.println("Opción no válida. Intente de nuevo.");
        }
        return this;
    }

    //#endregion

    //#region CONSTRUCCIÓN
    /**
     * Construye un estado donde se imprime el menú principal
     * de la aplicación.
     */
    public MainMenuState() {

        if (MAIN_MENU.isEmpty()) {
            buildMainMenu();
        }
    }
    
    //#endregion

    //#region APOYO
    /**
     * Construye el menú principal.
     */
    private void buildMainMenu() {

        for (var element : MainMenuOptions.values()) {
            MAIN_MENU.add(element.key, element.message);
        }
    }
    //#endregion
}
