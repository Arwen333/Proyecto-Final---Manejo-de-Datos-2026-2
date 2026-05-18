package exceptions;
/**
 * Engloba cualquier excepción relacionada a la interacción con menús
 * al programar o al interactuar con el programa, una vez se encuentra
 * en tiempo de ejecución.
 * @author Brayan Montiel
 */
public class MenuRuntimeException extends RuntimeException {
    
    public MenuRuntimeException(String messsage) { super(messsage); }
}
