package model.states;

import java.util.Scanner;
/**
 * @author 
 */
public class ShowPlayersState implements IState {

    public IState execute(Scanner sc) {

        System.out.println(getClass().toString() + " no implementado. Regresando al menú principal");
        return new MainMenuState();
    }
}