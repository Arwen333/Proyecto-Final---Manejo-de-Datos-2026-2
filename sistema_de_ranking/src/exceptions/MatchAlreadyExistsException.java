package exceptions;
/**
 * Ocurre cuando se intenta registrar una partida que ya existe.
 * @author Brayan Montiel
 */
public class MatchAlreadyExistsException extends RuntimeException {

    public MatchAlreadyExistsException(String message) {
        super(message);
    }
}
