import java.util.Scanner;

import domain.interfaces.IState;
import domain.models.states.AppStates;
import domain.models.states.MainMenuState;

public class App {

    private static IState<AppStates> _currentState;
    private static final boolean TEST_MODE = true;
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("¡Bienvenido al sistema de Ranking!");

        _currentState = new MainMenuState();

        while (_currentState.getId() != AppStates.Exit) {

            if (TEST_MODE) {
                System.out.println("App comienza estado " + _currentState.getId().toString());
            }

            _currentState = _currentState.execute(sc);
        }

        sc.close();

        System.out.println("Gracias por utilziar nuestro sistema de Ranking");

        
    }
}
