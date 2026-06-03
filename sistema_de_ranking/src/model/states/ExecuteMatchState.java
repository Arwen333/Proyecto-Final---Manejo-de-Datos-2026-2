package model.states;
 
import java.util.Scanner;
 
import controller.dao.IMatchHistoryDAO;
import controller.dao.IPendingMatchDAO;
import exceptions.MatchAlreadyExistsException;
import model.Match;
import model.Player;
 
/**
 * Maneja la lógica para ejecutar la siguiente partida pendiente en la cola
 * y actualizar el ranking de los jugadores.
 * @author Arwen Ortiz
 */
public class ExecuteMatchState implements IState {
 
    private final IMatchHistoryDAO matchHistoryDAO;
    private final IPendingMatchDAO pendingMatchDAO;
 
    /**
     * Construye el estado con los DAOs necesarios para ejecutar una partida.
     * @param matchHistoryDAO - DAO para guardar el historial de partidas.
     * @param pendingMatchDAO - DAO para manejar la cola de partidas pendientes.
     */
    public ExecuteMatchState(IMatchHistoryDAO matchHistoryDAO, IPendingMatchDAO pendingMatchDAO) {
        this.matchHistoryDAO = matchHistoryDAO;
        this.pendingMatchDAO = pendingMatchDAO;
    }
 
    @Override
    public IState execute(Scanner sc) {
        System.out.println("=== EJECUTAR SIGUIENTE PARTIDA ===");
        System.out.println();
 
        // Verificar si hay partidas pendientes
        if (pendingMatchDAO.getPendingMatchesCount() == 0) {
            System.out.println("No hay partidas pendientes en la cola.");
            System.out.print("Presione 'm' para volver al menú principal: ");
            esperarM(sc);
            return new MainMenuState();
        }
 
        // Obtener la siguiente partida de la cola
        Match partida = pendingMatchDAO.consumeNextPendingMatch();
        if (partida == null) {
            System.out.println("No hay partidas pendientes en la cola.");
            System.out.print("Presione 'm' para volver al menú principal: ");
            esperarM(sc);
            return new MainMenuState();
        }
 
        Player jugador1 = partida.getJugador1();
        Player jugador2 = partida.getJugador2();
 
        System.out.println("Partida en turno:");
        System.out.println(jugador1.getName() + " vs " + jugador2.getName());
        System.out.println();
 
        // Pedir puntaje del jugador 1
        int puntaje1 = pedirPuntaje(sc, jugador1.getName());
        // Pedir puntaje del jugador 2
        int puntaje2 = pedirPuntaje(sc, jugador2.getName());
 
        // Asignar puntajes a la partida
        partida.setPuntajeJugador1(puntaje1);
        partida.setPuntajeJugador2(puntaje2);
        partida.setEstado("completada");
 
        // Actualizar puntaje acumulado de los jugadores
        jugador1.setAcumScore(jugador1.getAcumScore() + puntaje1);
        jugador2.setAcumScore(jugador2.getAcumScore() + puntaje2);
 
        // Guardar la partida en el historial
        try {
            matchHistoryDAO.saveCompletedMatch(partida);
        } catch (MatchAlreadyExistsException e) {
            System.out.println("Advertencia: la partida ya estaba registrada en el historial.");
        }
 
        // Mostrar resultado
        System.out.println();
        System.out.println("=== RESULTADO ===");
        System.out.println(jugador1.getName() + ": " + puntaje1 + " puntos");
        System.out.println(jugador2.getName() + ": " + puntaje2 + " puntos");
        System.out.println();
 
        Player ganador = partida.getGanador();
        if (ganador != null) {
            System.out.println("Ganador: " + ganador.getName() );
        } else {
            System.out.println("Resultado: ¡Empate!");
        }
 
        System.out.println();
        System.out.print("Presione 'm' para volver al menú principal: ");
        esperarM(sc);
        return new MainMenuState();
    }
 
    /**
     * Solicita al usuario el puntaje obtenido por un jugador.
     * @param sc - Scanner para leer la entrada.
     * @param nombreJugador - Nombre del jugador para mostrar en pantalla.
     * @return El puntaje ingresado.
     */
    private int pedirPuntaje(Scanner sc, String nombreJugador) {
        while (true) {
            System.out.println("Ingrese el puntaje obtenido por " + nombreJugador + ":");
            String input = sc.next().trim();
 
            if (!input.matches("\\d+")) {
                System.out.println("Puntaje no válido. Debe ser un número mayor o igual a cero.");
                continue;
            }
 
            return Integer.parseInt(input);
        }
    }
 
    /**
     * Espera a que el usuario presione 'm' para continuar.
     * @param sc - Scanner para leer la entrada.
     */
    private void esperarM(Scanner sc) {
        while (true) {
            String input = sc.next().trim();
            if (input.equalsIgnoreCase("m")) {
                break;
            }
            System.out.print("Ingrese 'm' para volver al menú principal: ");
        }
    }
}