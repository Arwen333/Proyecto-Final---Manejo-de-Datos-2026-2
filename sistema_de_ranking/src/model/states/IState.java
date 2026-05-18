package model.states;

import java.util.Scanner;

/***
 * Interfaz mínima para cualquier estado de la aplicación.
 * Permite la interacción del usuario con la app mediante el teclado.
 * 
 * @author Brayan Montiel
 */
public interface IState {
    
    IState execute(Scanner sc);
}
