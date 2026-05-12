package presentation.console;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import domain.exceptions.InvalidMenuInputException;
import domain.exceptions.InvalidOutputException;
import domain.exceptions.MenuOptionAlreadyExistsException;
import domain.exceptions.MenuOptionNotFoundException;
import presentation.console.utils.InputValidator;

public class Menu {
    
    //#region CONSTANTES
    public final char exitOption;
    //#endregion

    //#region CAMPO
    public Map<Character, String> _options = new LinkedHashMap<>();
    //#endregion

    //#region CONSTRUCCIÓN
    public Menu() {
        super();
        exitOption = 's';
    }
    public Menu(char exitOption) {
        super();
        this.exitOption = exitOption;
    }

    public void add(Character option, String message) {

        if (_options.containsKey(option)) {
            throw new MenuOptionAlreadyExistsException("Opción \'" + option + "\' ya existe.");
        }
        _options.put(option, message);
    }
    //#endregion

    //#region ACCESO PÚBLICO
    // Consulta:
    public char ask(Scanner sc, String message) throws
        InvalidMenuInputException,
        MenuOptionNotFoundException {

        return ask(sc, message, false);
    }
    
    public char ask(Scanner sc, String message, boolean includeValidation) throws
        InvalidMenuInputException,
        MenuOptionNotFoundException {
        
        printMenu(message);

        try {
            char election = InputValidator.nextChar(sc);

            if (!_options.containsKey(election)) {
                throw new MenuOptionNotFoundException("Elección no encontrada como opción.");
            }
            if (includeValidation) {

                System.out.println("Ha elegido \'" + election + ") " + _options.get(election) + '\'');
            }
            return election;

        } catch (InvalidOutputException ioe) {
            throw new InvalidMenuInputException("Cadena de longitud mayor a uno no válida como opción.");
        } catch (NoSuchElementException nsee) {
            throw new InvalidMenuInputException("Cadena vacía no válidad como opción.");
        }
    }
    // Impresión:
    public void printMenu(String message) {

        System.out.println(message);

        for (Map.Entry<Character, String> entry : _options.entrySet()) {
            System.out.println(entry.getKey() + ") " + entry.getValue());
        }
    }
    //#endregion

}
