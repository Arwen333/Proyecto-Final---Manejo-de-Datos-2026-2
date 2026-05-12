package presentation.console.utils;

import java.util.NoSuchElementException;
import java.util.Scanner;

import domain.exceptions.InvalidOutputException;

public class InputValidator {

    private InputValidator() {}

    /**
     * 
     * @param sc
     * @return
     * @throws InvalidOutputException
     * @throws NoSuchElementException
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
