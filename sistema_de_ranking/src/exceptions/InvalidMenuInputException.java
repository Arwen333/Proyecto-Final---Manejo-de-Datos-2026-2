package exceptions;
/**
 * Excepción para inputs no válidos ingresados por el usuario durante
 * @author Brayan Montiel
 */
public class InvalidMenuInputException extends MenuRuntimeException {

    public InvalidMenuInputException(String message) { super(message); }
}
