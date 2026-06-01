package exceptions;
/**
 * Ocurre cuando se intenta registrar a un jugador que ya existe o ha sido registrado.
 * @author Brayan Montiel
 */
public class PlayerAlreadyExistsException extends RuntimeException {

    public PlayerAlreadyExistsException(String message) {
        super(message);
    }
}
