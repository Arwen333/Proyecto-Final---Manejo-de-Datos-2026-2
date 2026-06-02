package model.states;

import java.util.ArrayList;
import java.util.Scanner;

import controller.dao.IPendingMatchDAO;
import controller.dao.IPlayerDAO;
import model.Match;
import model.Player;

public class CreateMatchState implements IState {
    
    private IPlayerDAO playerDAO;
    private IPendingMatchDAO pendingMatchDAO;
    
    public CreateMatchState(IPlayerDAO playerDAO, IPendingMatchDAO pendingMatchDAO) {
        this.playerDAO = playerDAO;
        this.pendingMatchDAO = pendingMatchDAO;
    }
    
    @Override
    public IState execute(Scanner sc) {
        System.out.println("\n=== CREAR NUEVA PARTIDA ===");
        
        ArrayList<Player> jugadores = playerDAO.getRegisteredPlayers();
        
        if (jugadores.size() < 2) {
            System.out.println(" Error: Se necesitan al menos 2 jugadores para crear una partida.");
            System.out.println("   Registre más jugadores primero.");
            System.out.print("\nPresione 'm' para volver al menú principal, cualquier otra tecla para continuar: ");
            String opcion = sc.nextLine();
            if (opcion.equalsIgnoreCase("m")) {
                return new MainMenuState();
            }
            return new MainMenuState();
        }
        
        System.out.println("\n📋 Jugadores registrados:");
        System.out.println("┌────┬──────────────────────┬────────────┐");
        System.out.println("│ ID │ Nombre               │ Nivel      │");
        System.out.println("├────┼──────────────────────┼────────────┤");
        for (Player p : jugadores) {
            System.out.printf("│ %-2d │ %-20s │ %-10d │\n", p.getId(), p.getNombre(), p.getNivel());
        }
        System.out.println("└────┴──────────────────────┴────────────┘");
        
        System.out.print("\nID del Jugador 1: ");
        int id1 = sc.nextInt();
        sc.nextLine();
        
        Player jugador1 = playerDAO.getPlayerOrNull(id1);
        if (jugador1 == null) {
            System.out.println("Error: Jugador 1 no encontrado.");
            System.out.print("\nPresione 'm' para volver al menú principal, cualquier otra tecla para continuar: ");
            String opcion = sc.nextLine();
            if (opcion.equalsIgnoreCase("m")) {
                return new MainMenuState();
            }
            return new MainMenuState();
        }
        
        System.out.print("ID del Jugador 2: ");
        int id2 = sc.nextInt();
        sc.nextLine();
        
        Player jugador2 = playerDAO.getPlayerOrNull(id2);
        if (jugador2 == null) {
            System.out.println(" Error: Jugador 2 no encontrado.");
            System.out.print("\nPresione 'm' para volver al menú principal, cualquier otra tecla para continuar: ");
            String opcion = sc.nextLine();
            if (opcion.equalsIgnoreCase("m")) {
                return new MainMenuState();
            }
            return new MainMenuState();
        }
        
        if (id1 == id2) {
            System.out.println(" Error: Un jugador no puede jugar contra sí mismo.");
            System.out.print("\nPresione 'm' para volver al menú principal, cualquier otra tecla para continuar: ");
            String opcion = sc.nextLine();
            if (opcion.equalsIgnoreCase("m")) {
                return new MainMenuState();
            }
            return new MainMenuState();
        }
        
        Match nuevaPartida = new Match(jugador1, jugador2);
        pendingMatchDAO.enqueueMatch(nuevaPartida);
        
        System.out.println("\nPartida creada exitosamente!");
        System.out.println("   " + jugador1.getNombre() + " vs " + jugador2.getNombre());
        System.out.println("   Partidas pendientes: " + pendingMatchDAO.getPendingMatchesCount());
        
        System.out.print("\nPresione 'm' para volver al menú principal, cualquier otra tecla para continuar: ");
        String opcion = sc.nextLine();
        if (opcion.equalsIgnoreCase("m")) {
            return new MainMenuState();
        }
        return new MainMenuState();
    }
}