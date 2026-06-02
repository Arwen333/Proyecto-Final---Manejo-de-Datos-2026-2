package model.states;

import java.util.List;
import java.util.Scanner;

import controller.dao.IMatchHistoryDAO;
import controller.dao.IPlayerDAO;
import exceptions.PlayerNotFoundException;
import model.Match;
import model.Player;

public class PlayerHistoryState implements IState {
    
    private IPlayerDAO playerDAO;
    private IMatchHistoryDAO matchHistoryDAO;
    
    public PlayerHistoryState(IPlayerDAO playerDAO, IMatchHistoryDAO matchHistoryDAO) {
        this.playerDAO = playerDAO;
        this.matchHistoryDAO = matchHistoryDAO;
    }
    
    @Override
    public IState execute(Scanner sc) {
        System.out.println("\n=== HISTORIAL DE PARTIDAS ===");
        System.out.print("Ingrese ID del jugador: ");
        
        int id = sc.nextInt();
        sc.nextLine();
        
        Player jugador = playerDAO.getPlayerOrNull(id);
        
        if (jugador == null) {
            System.out.println(" Jugador no encontrado.");
            System.out.print("\nPresione 'm' para volver al menú principal, cualquier otra tecla para continuar: ");
            String opcion = sc.nextLine();
            if (opcion.equalsIgnoreCase("m")) {
                return new MainMenuState();
            }
            return new MainMenuState();
        }
        
        System.out.println("\nJugador: " + jugador.getNombre());
        System.out.println("Nivel: " + jugador.getNivel());
        System.out.println("Puntaje acumulado: " + jugador.getPuntajeAcumulado());
        System.out.println("\nÚltimas 5 partidas:\n");
        
        try {
            List<Match> historial = matchHistoryDAO.getLastMatches(id, 5);
            
            if (historial.isEmpty()) {
                System.out.println("   No hay partidas registradas para este jugador.");
            } else {
                int contador = 1;
                for (Match match : historial) {
                    System.out.println(contador + ". " + match.toString());
                    contador++;
                }
            }
        } catch (PlayerNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.print("\nPresione 'm' para volver al menú principal, cualquier otra tecla para continuar: ");
        String opcion = sc.nextLine();
        if (opcion.equalsIgnoreCase("m")) {
            return new MainMenuState();
        }
        return new MainMenuState();
    }
}