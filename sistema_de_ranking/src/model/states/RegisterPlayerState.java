package model.states;

import java.util.NoSuchElementException;
import java.util.Scanner;

import controller.dao.IPlayerDAO;
import exceptions.PlayerAlreadyExistsException;
import model.Player;

/**
 * Maneja la lógica de validación y registro de un jugador en el sistema
 * de ranking.
 * @author Brayan Montiel.
 */
public class RegisterPlayerState implements IState {

    private final IPlayerDAO playerDAO;

    /**
     * Construye al estado con el DAO que utilizará para la persistencia
     * del jugador.
     * @param playerDAO El Data Access Object (DAO) para los jugadores.
     */
    public RegisterPlayerState(IPlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Override
    public IState execute(Scanner sc) {
        String name = "";
        
        // Solicitar el nombre:
        while (true) {
            System.out.println("Ingrese un nombre de al menos 5 dígitos o 's' para salir.");
            
            try {
                name = sc.next().trim(); 
            } catch (NoSuchElementException e) {
                System.out.println("Nombre no válido.");
                continue;
            }

            if (name.equalsIgnoreCase("s")) {
                System.out.println("Regresando al menú principal.");
                return new MainMenuState(); 
            }

            if (name.isEmpty() || name.length() < 5) {
                System.out.println("Nombre no válido.");
                continue;
            }
            if (playerDAO.isRegistered(name)) {
                System.out.println("El nombre ingresado ya ha sido usado por otro usuario");
                continue;
            }
            break;
        }
        System.out.println();
        // Solicitar el nivel:
        while (true) {
            System.out.println("Ingrese el nivel del jugador o 's' para salir.");
            String levelInput = "";
            
            try {
                levelInput = sc.next().trim();
            } catch (NoSuchElementException e) {
                System.out.println("El nivel ingresado no es válido.");
                continue;
            }

            if (levelInput.isEmpty()) {
                System.out.println("El nivel ingresado no es válido.");
                continue;
            }

            if (levelInput.equalsIgnoreCase("s")) {
                System.out.println("Regresando al menú principal.");
                return new MainMenuState();
            }

            int level;
            try {
                level = Integer.parseInt(levelInput);
            } catch (NumberFormatException e) {
                System.out.println("El nivel ingresado no es válido.");
                continue;
            }

            if (level < 0) {
                System.out.println("Nivel ingresado no válido. Debe ser mayor o igual a cero.");
                continue;
            }

            // Registro del jugador:
            try {
                Player newPlayer = new Player(name, level, 0);
                
                playerDAO.register(newPlayer);
                
                System.out.println("Jugador " + newPlayer.getName() + " registrado con éxito.");
                break;
                
            } catch (PlayerAlreadyExistsException e) {
                System.out.println("El nombre ingresado ya ha sido usado por otro usuario");
                return this;
            }
        }
        System.out.println();
        System.out.println("Regresando al menú principal.");

        return new MainMenuState();
    }
    
}