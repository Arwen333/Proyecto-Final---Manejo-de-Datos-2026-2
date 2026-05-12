package domain.exceptions;

public class MenuOptionAlreadyExistsException extends MenuRuntimeException {
    
    public MenuOptionAlreadyExistsException(String message) { super(message); }
}
