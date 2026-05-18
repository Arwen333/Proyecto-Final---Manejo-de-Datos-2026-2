package view.utils;

import java.util.NoSuchElementException;
import java.util.Scanner;

import exceptions.InvalidOutputException;
/**
 * Brinda métodos para validación de entradas a la hora de
 * comunicar al usuario con la aplicación.
 * @author Brayan Montiel
 */
public class InputValidator {

    /**
     * Constructor privado que hace a la clase no instanciable.
     */
    private InputValidator() {}

    /**
     * Lee la línea ingresada por el usuario, esperando recibir
     * un caracter no vacío.
     * @param sc - Scanner con el que se comunican el usuario y la aplicación.
     * @return El caracter que el usuario ingresó.
     * @throws InvalidOutputException Si el usuario ingresa una cadena de longitud
     * mayor a 1.
     * @throws NoSuchElementException - Si el usuario ingresa una cadena vacía.
     */
    public static char nextChar(Scanner sc) throws 
    NoSuchElementException,
    InvalidOutputException {
        
        String line = sc.next();
        if (line.isEmpty()) {
            throw new NoSuchElementException("Línea vacía fue ingresada.");
        }
        if (line.length() > 1) {
            throw new InvalidOutputException("No se permite más de un carácter.");
        }
        return line.charAt(0);
    }

}
