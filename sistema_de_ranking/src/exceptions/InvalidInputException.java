package exceptions;
/**
 * Ocurre cuando un input no cumple las condiciones de quien lo valida
 * o solicita.
 * @author Brayan Montiel
 */
public class InvalidInputException extends RuntimeException {
    
    public InvalidInputException(String message) { super(message); }
}
