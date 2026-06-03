package model.states;
 
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
 
import controller.dao.IMatchHistoryDAO;
import controller.dao.IPlayerDAO;
import exceptions.PlayerNotFoundException;
import model.Match;
import model.Player;
 
/**
 * Muestra el historial de las últimas 5 partidas jugadas por un jugador,
 * cargando la información en una pila.
 * @author Arwen Ortiz
 */
public class PlayerHistoryState implements IState {
 
    private final IPlayerDAO playerDAO;
    private final IMatchHistoryDAO matchHistoryDAO;
 
    /**
     * Construye el estado con los DAOs necesarios para consultar el historial.
     * @param playerDAO - DAO para buscar al jugador.
     * @param matchHistoryDAO - DAO para obtener el historial de partidas.
     */
    public PlayerHistoryState(IPlayerDAO playerDAO, IMatchHistoryDAO matchHistoryDAO) {
        this.playerDAO = playerDAO;
        this.matchHistoryDAO = matchHistoryDAO;
    }
 
    @Override
    public IState execute(Scanner sc) {
        System.out.println("=== HISTORIAL DE PARTIDAS ===");
        System.out.println();
 
        // Pedir ID del jugador
        int id = -1;
        while (true) {
            System.out.println("Ingrese el ID del jugador o 's' para cancelar:");
            String input = sc.next().trim();
 
            if (input.equalsIgnoreCase("s")) {
                return new MainMenuState();
            }
 
            if (!input.matches("\\d+")) {
                System.out.println("ID no válido. Debe ser un número.");
                continue;
            }
 
            id = Integer.parseInt(input);
            break;
        }
 
        // Buscar jugador
        Player jugador = playerDAO.getPlayerOrNull(id);
        if (jugador == null) {
            System.out.println("No se encontró ningún jugador con el ID: " + id);
            System.out.print("Presione 's' para volver al menú principal: ");
            esperarM(sc);
            return new MainMenuState();
        }
 
        System.out.println();
        System.out.println("Jugador: " + jugador.getName());
        System.out.println("Nivel: " + jugador.getLevel());
        System.out.println("Puntaje acumulado: " + jugador.getAcumScore());
        System.out.println();
        System.out.println("Últimas 5 partidas:");
        System.out.println();
 
        // Cargar historial en una pila
        try {
            List<Match> historial = matchHistoryDAO.getLastMatches(id, 5);
 
            if (historial.isEmpty()) {
                System.out.println("No hay partidas registradas para este jugador.");
            } else {
                Stack<Match> pila = new Stack<>();
                for (Match match : historial) {
                    pila.push(match);
                }
 
                int contador = 1;
                while (!pila.isEmpty()) {
                    System.out.println(contador + ". " + pila.pop().toString());
                    contador++;
                }
            }
 
        } catch (PlayerNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
 
        System.out.println();
        System.out.print("Presione 's' para volver al menú principal: ");
        esperarM(sc);
        return new MainMenuState();
    }
 
    /**
     * Espera a que el usuario presione 's' para continuar.
     * @param sc - Scanner para leer la entrada.
     */
    private void esperarM(Scanner sc) {
        while (true) {
            String input = sc.next().trim();
            if (input.equalsIgnoreCase("s")) {
                break;
            }
            System.out.print("Ingrese 's' para volver al menú principal: ");
        }
    }
}