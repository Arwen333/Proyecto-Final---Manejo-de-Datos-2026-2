import java.util.Scanner;

import model.states.ExitState;
import model.states.IState;
import model.states.MainMenuState;

public class App {

    private static IState _currentState;
    private static final boolean TEST_MODE = true;
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("¡Bienvenido al sistema de Ranking!");

        _currentState = new MainMenuState();

        while (!(_currentState instanceof ExitState)) {

            if (TEST_MODE) {
                System.out.println("App comienza estado " + _currentState.getClass().toString());
            }
            _currentState = _currentState.execute(sc);
        }

        sc.close();

        System.out.println("Gracias por utilziar nuestro sistema de Ranking");

        
    }
}
