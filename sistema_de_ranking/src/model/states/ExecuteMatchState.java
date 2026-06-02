package model.states;

import java.util.Scanner;

import controller.dao.IMatchHistoryDAO;
import controller.dao.IPendingMatchDAO;
import controller.dao.IPlayerDAO;
import exceptions.MatchAlreadyExistsException;
import model.Match;
import model.Player;

public class ExecuteMatchState implements IState {
    
    private IPlayerDAO playerDAO;
    private IMatchHistoryDAO matchHistoryDAO;
    private IPendingMatchDAO pendingMatchDAO;
    
    public ExecuteMatchState(IPlayerDAO playerDAO, IMatchHistoryDAO matchHistoryDAO, IPendingMatchDAO pendingMatchDAO) {
        this.playerDAO = playerDAO;
        this.matchHistoryDAO = matchHistoryDAO;
        this.pendingMatchDAO = pendingMatchDAO;
    }
    
    @Override
    public IState execute(Scanner sc) {
        System.out.println("\n=== EJECUTAR PARTIDA ===");
        
        int pendientes = pendingMatchDAO.getPendingMatchesCount();
        
        if (pendientes == 0) {
            System.out.println("No hay partidas pendientes.");
            System.out.println("   Cree una partida primero (opción e).");
            System.out.print("\nPresione 'm' para volver al menú principal, cualquier otra tecla para continuar: ");
            String opcion = sc.nextLine();
            if (opcion.equalsIgnoreCase("m")) {
                return new MainMenuState();
            }
            return new MainMenuState();
        }
        
        System.out.println("Partidas pendientes: " + pendientes);
        
        Match partida = pendingMatchDAO.consumeNextPendingMatch();
        
        if (partida == null) {
            System.out.println("Error al obtener la partida.");
            System.out.print("\nPresione 'm' para volver al menú principal, cualquier otra tecla para continuar: ");
            String opcion = sc.nextLine();
            if (opcion.equalsIgnoreCase("m")) {
                return new MainMenuState();
            }
            return new MainMenuState();
        }
        
        Player j1 = partida.getJugador1();
        Player j2 = partida.getJugador2();
        
        System.out.println("\n🎮 Partida: " + j1.getNombre() + " vs " + j2.getNombre());
        
        System.out.print("\nPuntaje de " + j1.getNombre() + ": ");
        int score1 = sc.nextInt();
        
        System.out.print("Puntaje de " + j2.getNombre() + ": ");
        int score2 = sc.nextInt();
        sc.nextLine();
        
        if (score1 < 0 || score2 < 0) {
            System.out.println("Error: Los puntajes no pueden ser negativos.");
            System.out.print("\nPresione 'm' para volver al menú principal, cualquier otra tecla para continuar: ");
            String opcion = sc.nextLine();
            if (opcion.equalsIgnoreCase("m")) {
                return new MainMenuState();
            }
            return new MainMenuState();
        }
        
        partida.setPuntajeJugador1(score1);
        partida.setPuntajeJugador2(score2);
        partida.setEstado("completada");
        
        j1.agregarPuntaje(score1);
        j2.agregarPuntaje(score2);
        
        try {
            matchHistoryDAO.saveCompletedMatch(partida);
            System.out.println("\nPartida guardada en el historial.");
        } catch (MatchAlreadyExistsException e) {
            System.out.println("La partida ya existía en el historial.");
        }
        
        System.out.println("\nRESULTADO:");
        System.out.println("   " + j1.getNombre() + ": " + score1 + " pts (Total: " + j1.getPuntajeAcumulado() + ")");
        System.out.println("   " + j2.getNombre() + ": " + score2 + " pts (Total: " + j2.getPuntajeAcumulado() + ")");
        
        if (score1 > score2) {
            System.out.println("GANADOR: " + j1.getNombre());
        } else if (score2 > score1) {
            System.out.println("GANADOR: " + j2.getNombre());
        } else {
            System.out.println("EMPATE!");
        }
        
        System.out.println("\nPartidas restantes: " + pendingMatchDAO.getPendingMatchesCount());
        
        System.out.print("\nPresione 'm' para volver al menú principal, cualquier otra tecla para continuar: ");
        String opcion = sc.nextLine();
        if (opcion.equalsIgnoreCase("m")) {
            return new MainMenuState();
        }
        return new MainMenuState();
    }
}