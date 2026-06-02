package model.states;

import java.util.ArrayList;
import java.util.Scanner;

import controller.dao.IPlayerDAO;
import model.Player;

public class ConsultRankingState implements IState {
    
    private IPlayerDAO playerDAO;
    
    public ConsultRankingState(IPlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }
    
    @Override
    public IState execute(Scanner sc) {
        System.out.println("\n=== RANKING GENERAL ===");
        
        ArrayList<Player> ranking = playerDAO.getRanking();
        
        if (ranking.isEmpty()) {
            System.out.println(" No hay jugadores registrados.");
        } else {
            System.out.println("\n┌────┬──────────────────────┬────────────┐");
            System.out.println("│ #  │ Nombre               │ Puntaje    │");
            System.out.println("├────┼──────────────────────┼────────────┤");
            
            int posicion = 1;
            for (Player p : ranking) {
                System.out.printf("│ %-2d │ %-20s │ %-10d │\n", posicion, p.getNombre(), p.getPuntajeAcumulado());
                posicion++;
            }
            System.out.println("└────┴──────────────────────┴────────────┘");
        }
        
        System.out.print("\nPresione 'm' para volver al menú principal, cualquier otra tecla para continuar: ");
        String opcion = sc.nextLine();
        if (opcion.equalsIgnoreCase("m")) {
            return new MainMenuState();
        }
        return new MainMenuState();
    }
}