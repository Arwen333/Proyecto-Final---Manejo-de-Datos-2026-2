package model.states;

import java.util.ArrayList;
import java.util.Scanner;

import controller.dao.IPlayerDAO;
import model.Player;
/**
 * @author 
 */
public class ShowPlayersState implements IState {

    private final IPlayerDAO playerDAO;
    /**
     * Constructs the show players state.
     */
    public ShowPlayersState(IPlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Override
    public IState execute(Scanner sc) {

        ArrayList<Player> players = playerDAO.getRegisteredPlayers();

        if (players.isEmpty()) {
            System.out.println("Ningún jugador ha sido registrado todavía.");
            System.out.println("Volviendo al menú principal.");
            return new MainMenuState();
        }

        System.out.println("Mostrando información de los jugadores:");
        
        for (Player player : players) {
            System.out.println(player);
        }

        System.out.println("Volviendo al menú principal.");
        return new MainMenuState();
    }
}