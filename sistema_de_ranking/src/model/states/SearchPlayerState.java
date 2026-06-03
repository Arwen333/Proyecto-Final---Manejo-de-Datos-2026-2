package model.states;

import java.util.NoSuchElementException;
import java.util.Scanner;

import controller.dao.IPlayerDAO;
import model.Player;
import view.components.IMenuOption;
import view.components.Menu;
import view.utils.InputValidator;
/**
 * @author 
 */
public class SearchPlayerState implements IState {

    //#region ENUMERACIONES

    /**
     * Contiene las opciones de búsqueda de jugador que este estado despliega.
     */
    private enum SearchMenuOptions implements IMenuOption {
        ByName('a', "Por nombre"),
        ById('b', "Por ID"),
        Cancel('s', "Cancelar");
        
        // Campo:
        private final char key;
        private final String message;

        // Construcción:

        /**
         * Construye una opción del menú con un identificador (llave) y
         * una descripción textual.
         * @param key - Llave de la opción.
         * @param message - Descripción de la opción.
         */
        private SearchMenuOptions(char key, String message) {
            
            this.key = key;
            this.message = message;
        }

        @Override
        public char getKey() { return key; }

        @Override
        public String getMessage() { return message; }

        
    }
    //#endregion

    // Menú de búsqueda:
    private static final Menu SEARCH_MENU = new Menu();
    private final IPlayerDAO playerDAO;

    /**
     * Construye al estado con el DAO que utilizará para la persistencia
     * del jugador.
     * @param playerDAO El Data Access Object (DAO) para los jugadores.
     */
    public SearchPlayerState(IPlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
        if (SEARCH_MENU.isEmpty()) {
            buildMenu();
        }
    }

    @Override
    public IState execute(Scanner sc) {
        // Revisar si ya estaba en la base de datos:
        if (!playerDAO.isAnyPlayerRegistered()) {
            System.out.println("Ningún jugador ha sido registrado todavía.");
            System.out.println("Volviendo al menú principal.");
            return new MainMenuState();
        }

        while (true) {
            SEARCH_MENU.print("Indique cómo desea buscar al jugador");
            
            char key;
            SearchMenuOptions choice;
            
            try {

                key = InputValidator.nextChar(sc);
                
                if (!SEARCH_MENU.contains(key)) {
                    System.out.println("Opción no válida.");
                    continue;
                }

                choice = IMenuOption.fromKey(SearchMenuOptions.class, key);
                
            } catch (Exception e) {
                System.out.println("Opción no válida.");
                continue;
            }

            System.out.println("Ha seleccionado " + choice.key + ") " + choice.message + ".");
            System.out.println();
            
            switch (choice) {
                case ByName:
                    executeSearchByName(sc);
                    break;
                case ById:
                    executeSearchById(sc);
                    break;
                case Cancel:
                    break;
            }
            break;
        }

        System.out.println("Regresando al menú principal");
        return new MainMenuState();
    }

    /**
     * Ejecuta la búsqueda del jugador por nombre.
     * @param sc
     */
    private void executeSearchByName(Scanner sc) {
        while (true) {
            System.out.println("Ingrese el nombre del jugador que desea buscar o 's' para salir.");
            String nameInput;
            try {
                nameInput = sc.next().trim();
            } catch (NoSuchElementException e) {
                System.out.println("Nombre no válido.");
                continue;
            }

            if (nameInput.isEmpty() || (nameInput.length() < 5 && !nameInput.equalsIgnoreCase("s"))) {
                System.out.println("Ningún nombre posee menos de 5 dígitos.");
                continue;
            }

            if (nameInput.equalsIgnoreCase("s")) {
                return; // Returns to the main loop of this state
            }

            // (2) & (3) Optimizing: We retrieve the player directly. If null, it doesn't exist.
            Player player = playerDAO.getPlayerOrNull(nameInput);
            if (player == null) {
                System.out.println("No existe ninguna persona registrada con el nombre: " + nameInput + ".");
                continue;
            }

            System.out.println(player);
            break;
        }
    }

    /**
     * Ejecuta la búsqueda del jugador por ID.
     * @param sc
     */
    private void executeSearchById(Scanner sc) {
        while (true) {
            System.out.println("Ingrese el ID del jugador que desea buscar o 's' para salir.");
            String idInput;
            try {
                idInput = sc.next().trim();
            } catch (NoSuchElementException e) {
                System.out.println("Formato no válido.");
                continue;
            }

            if (idInput.isEmpty()) {
                System.out.println("Formato no válido.");
                continue;
            }

            if (idInput.equalsIgnoreCase("s")) {
                return; // Returns to the main loop of this state
            }

            // Validate that the entire string consists only of digits
            if (!idInput.matches("\\d+")) {
                System.out.println("Formato no válido.");
                continue;
            }

            int id = Integer.parseInt(idInput);

            // (4) & (5) Optimizing: We query the database for the ID directly
            Player player = playerDAO.getPlayerOrNull(id);
            if (player == null) {
                System.out.println("No se encontró algún jugador registrado con el id: " + id + ".");
                continue;
            }

            System.out.println(player);
            break;
        }
    }
    
    private void buildMenu() {
        for (SearchMenuOptions option : SearchMenuOptions.values()) {
            SEARCH_MENU.add(option.key, option.message);
        }
    }
}