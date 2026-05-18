package view.components;

import java.util.LinkedHashMap;
import java.util.Map;

import exceptions.MenuOptionAlreadyExistsException;

/**
 * Componente de una interfaz de usuario que permite su interacción con la
 * aplicación. Brinda herramientas para construir menús de manera dinámica.
 * @author Brayan Montiel
 */
public class Menu {
    
    //#region Campo

    // Diccionario ordenado que relaciona opciones (char) y mensajes (String) del menú.
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
     * Añade una opción en formato de caracter, asociada a un mensaje que acompaña su
     * impresión.
     * @param option - Caracter que sirve como llave de un mensaje que representa una
     * opción en el menú.
     * @param message - Descripción de la opción.
     * @throws MenuOptionAlreadyExistsException Si {@code option} ya se encuentra como
     * llave de alguna opción en este menú.
     */
    public void add(char option, String message) throws MenuOptionAlreadyExistsException {

        if (contains(option)) {
            throw new MenuOptionAlreadyExistsException("Opción \'" + option + "\' ya existe.");
        }
        _options.put(option, message);
    }
    
    //#endregion

    //#region Consulta

    /**
     * Imprime el menú en su estado de construcción actual.
     * @param message - mensaje que acompaña al menú.
     */
    public void print(String message) {

        System.out.println(message);

        for (Map.Entry<Character, String> entry : _options.entrySet()) {
            System.out.println(entry.getKey() + ") " + entry.getValue() + ".");
        }
    }
    
    /**
     * Indica si el menú está vacío (no le han añadido opciones).
     * @return {@code false} si tiene alguna opción añadida y {@code true} si no.
     */
    public boolean isEmpty() {
        return _options.isEmpty();
    }

    /**
     * Indica si el menú contiene una opción.
     * @param option - Opción cuya existencia se busca en el menú.
     * @return {@code true} si contiene la opción buscada y {@code false} si no.
     */
    public boolean contains(char option) {
        return _options.containsKey(option);
    }
    //#endregion

}
