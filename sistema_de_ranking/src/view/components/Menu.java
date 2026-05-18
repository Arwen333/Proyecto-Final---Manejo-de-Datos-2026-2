package view.components;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import exceptions.InvalidMenuInputException;
import exceptions.InvalidOutputException;
import exceptions.MenuOptionAlreadyExistsException;
import exceptions.MenuOptionNotFoundException;
import view.utils.InputValidator;

/**
 * Representa una interacción entre el usuario y la aplicación
 * mediante impresiones en consola. Permite añadir opciones y construir
 * menús de manera dinámica.
 * @author Brayan Montiel
 */
public class Menu {
    
    //#region Campo
    private Map<Character, String> _options = new LinkedHashMap<>();
    //#endregion

    //#region Construcción

    /**
     * Construye un menú vacío.
     */
    public Menu() {
        super();
    }
    /**
     * Añade una opción en formato de caracter, asociada
     * a un mensaje que acompaña su impresión.
     * @param option - Caracter que sirve como llave de un mensaje que
     * representa una opción en el menú.
     * @param message - Descripción de la opción.
     */
    public void add(Character option, String message) {

        if (_options.containsKey(option)) {
            throw new MenuOptionAlreadyExistsException("Opción \'" + option + "\' ya existe.");
        }
        _options.put(option, message);
    }
    //#endregion

    //#region Funcionalidades

    /**
     * Imprime un mensaje en consola junto precedido por el estado actual
     * de construcción del menú, y regresa la opción que el usuario escoge.
     * @param sc - Scanner con el que el usuario se comunica con el menú.
     * @param message - Mensaje que precede a las opciones del menú.
     * @return La llave de la opción que el usuario ha escogido.
     * @throws InvalidMenuInputException Si el usuario ingresa
     * @throws MenuOptionNotFoundException
     */
    public char ask(Scanner sc, String message) throws
        InvalidMenuInputException,
        MenuOptionNotFoundException {

        return ask(sc, message, false);
    }
    /**
     * Imprime un mensaje en consola junto precedido por el estado actual
     * de construcción del menú, y regresa la opción que el usuario escoge.
     * @param sc - Scanner con el que el usuario se comunica con el menú.
     * @param message - Mensaje que precede a las opciones del menú.
     * @param includeValidation - {@code true} si imprime un mensaje genérico
     * notificando la opción que ha sido elegida y {@code false} si no.
     * @return La llave de la opción que el usuario ha escogido.
     * @throws InvalidMenuInputException Si el usuario ingresa una cadena vacía
     * o de longitud mayor a 1.
     * @throws MenuOptionNotFoundException Si el usuario ingresa una opción que
     * no se encuentra dentro del rango del menú.
     */
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
            throw new InvalidMenuInputException("Cadena vacía no válida como opción.");
        }
    }
    /**
     * Imprime el menú en su estado de construcción actual.
     * @param message - mensaje que acompaña al menú.
     */
    public void printMenu(String message) {

        System.out.println(message);

        for (Map.Entry<Character, String> entry : _options.entrySet()) {
            System.out.println(entry.getKey() + ") " + entry.getValue());
        }
    }
    
    //#endregion

}
