package domain.models.states;

import java.util.Scanner;

import domain.interfaces.IState;

public class ExitState implements IState<AppStates> {

    public IState<AppStates> execute(Scanner sc) {

        throw new UnsupportedOperationException("Método no implementado");
    }
    public AppStates getId() { return AppStates.Exit; }
}
