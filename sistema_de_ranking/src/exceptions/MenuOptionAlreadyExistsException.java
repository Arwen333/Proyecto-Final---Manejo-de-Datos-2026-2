package exceptions;
/**
 * Ocurre cuando se intenta ingresar una opción existente en la lista de opciones
 * de un menú.
 * @author Brayan Montiel
 */
public class MenuOptionAlreadyExistsException extends MenuRuntimeException {
    
    public MenuOptionAlreadyExistsException(String message) { super(message); }
}
