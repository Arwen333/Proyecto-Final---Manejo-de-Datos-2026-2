package model.states;

import java.util.Scanner;
/**
 * @author 
 */
public class ExecuteMatchState implements IState {

    public IState execute(Scanner sc) {

        System.out.println(getClass().toString() + " no implementado. Regresando al menú principal");
        return new MainMenuState();
    }
}
