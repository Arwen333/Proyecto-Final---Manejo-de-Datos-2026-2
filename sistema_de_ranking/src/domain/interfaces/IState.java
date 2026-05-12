package domain.interfaces;

import java.util.Scanner;

/***
 * Interfaz mínima para cualquier estado y sub-estado de la aplicación.
 * Permite la interacción del usuario con esta mediante el teclado y la
 * consulta a un identificador que lo distingue por la clase (estado),
 * no por el objeto.
 * 
 * @author Brayan Montiel
 */
public interface IState<TStateId> {
    
    IState<TStateId> execute(Scanner sc);

    TStateId getId();
}
