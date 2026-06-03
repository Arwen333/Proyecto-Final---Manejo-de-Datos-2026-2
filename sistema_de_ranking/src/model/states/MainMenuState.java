package model.states;

import java.util.Scanner;

import controller.dao.TestDAO;
import exceptions.MenuOptionNotFoundException;
import view.components.IMenuOption;
import view.components.Menu;
import view.utils.InputValidator;
/**
 * Despliega el menú principal y permite al usuario elegir una opción dentro de las
 * funciones de la aplicación.
 * 
 * @author Brayan Montiel
 */
public class MainMenuState implements IState {

    //#region ENUMERACIONES

    /**
     * Contiene a todas las opciones que ofrece el sistema de ranking y que
     * este estado despliega como menú principal.
     */
    private enum MainMenuOptions implements IMenuOption {
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
         * Construye una opción del menú con un identificador (llave) y
         * una descripción textual.
         * @param key - Llave de la opción.
         * @param message - Descripción de la opción.
         */
        private MainMenuOptions(char key, String message) {
            this.key = key;
            this.message = message;
        }

        // Implementaciones:
        
        @Override
        public char getKey() { return key; }

        @Override
        public String getMessage() { return message; }
        
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
            
            MainMenuOptions option = IMenuOption.fromKey( 
                MainMenuOptions.class,
                InputValidator.nextChar(sc)
            );
            System.out.println("Ha elegido la opción " + option.key + ") " + option.message);
            switch (option) {
                case RegisterPlayer:
                    return new RegisterPlayerState(TestDAO.getInstance());
                case ShowPlayers:
                    return new ShowPlayersState(TestDAO.getInstance());
                case SearchPlayer:
                    return new SearchPlayerState(TestDAO.getInstance());
                case PlayerHistory:
                    return new PlayerHistoryState(TestDAO.getInstance(), TestDAO.getInstance());
                case CreateMatch:
                    return new CreateMatchState(TestDAO.getInstance(), TestDAO.getInstance());
                case ConsultRanking:
                    return new ConsultRankingState(TestDAO.getInstance());
                case ExecuteMatch:
                    return new ExecuteMatchState(TestDAO.getInstance(), TestDAO.getInstance(), TestDAO.getInstance());
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