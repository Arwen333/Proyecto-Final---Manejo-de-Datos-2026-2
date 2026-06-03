package model.states;
 
import java.util.ArrayList;
import java.util.Scanner;
 
import controller.dao.IPlayerDAO;
import model.Player;
 
/**
 * Muestra el listado de jugadores ordenado de mayor a menor puntaje,
 * representando el ranking general del torneo.
 * @author Arwen Ortiz
 */
public class ConsultRankingState implements IState {
 
    private final IPlayerDAO playerDAO;
 
    /**
     * Construye el estado con el DAO necesario para consultar el ranking.
     * @param playerDAO - DAO para acceder a los jugadores.
     */
    public ConsultRankingState(IPlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }
 
    @Override
    public IState execute(Scanner sc) {
 
        ArrayList<Player> ranking = playerDAO.getRanking();
 
        if (ranking.isEmpty()) {
            System.out.println("Ningún jugador ha sido registrado todavía.");
            System.out.println("Volviendo al menú principal.");
            return new MainMenuState();
        }
 
        System.out.println("=== RANKING GENERAL ===");
        System.out.println();
 
        int position = 1;
        for (Player player : ranking) {
            System.out.println(position + ". " + player.getName()
                + " | Nivel: " + player.getLevel()
                + " | Puntaje: " + player.getAcumScore());
            position++;
        }
 
        System.out.println();
        System.out.print("Presione 's' para volver al menú principal: ");
 
        while (true) {
            String input = sc.next().trim();
            if (input.equalsIgnoreCase("s")) {
                break;
            }
            System.out.print("Ingrese 's' para volver al menú principal: ");
        }
 
        return new MainMenuState();
    }
}