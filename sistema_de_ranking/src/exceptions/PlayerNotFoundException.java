package exceptions;
/**
 * Ocurre cuando se intenta buscar a un jugador y no es encontrado.
 * @author Brayan Montiel
 */
public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(String message) {
        super(message);
    }
}
