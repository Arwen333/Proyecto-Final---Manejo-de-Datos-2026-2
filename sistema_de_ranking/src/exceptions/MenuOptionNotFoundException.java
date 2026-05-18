package exceptions;
/**
 * Ocurre cuando una opción es buscada en cualquier tipo de menú, pero no es encontrada.
 * @author Brayan Montiel
 */
public class MenuOptionNotFoundException extends RuntimeException {

    public MenuOptionNotFoundException(String message) { super(message); }
    
}
