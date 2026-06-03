package model.states;
 
import java.util.NoSuchElementException;
import java.util.Scanner;
 
import controller.dao.IPendingMatchDAO;
import controller.dao.IPlayerDAO;
import exceptions.MatchAlreadyExistsException;
import model.Match;
import model.Player;
 
/**
 * Maneja la lógica para registrar una nueva partida entre dos jugadores
 * y agregarla a la cola de partidas pendientes del torneo.
 * @author Arwen Ortiz
 */
public class CreateMatchState implements IState {
 
    private final IPlayerDAO playerDAO;
    private final IPendingMatchDAO pendingMatchDAO;
 
    /**
     * Construye el estado con los DAOs necesarios para crear una partida.
     * @param playerDAO - DAO para buscar jugadores.
     * @param pendingMatchDAO - DAO para encolar la partida pendiente.
     */
    public CreateMatchState(IPlayerDAO playerDAO, IPendingMatchDAO pendingMatchDAO) {
        this.playerDAO = playerDAO;
        this.pendingMatchDAO = pendingMatchDAO;
    }
 
    @Override
    public IState execute(Scanner sc) {
 
        if (!playerDAO.isAnyPlayerRegistered()) {
            System.out.println("No hay jugadores registrados. Registre al menos dos jugadores primero.");
            System.out.print("Presione 'm' para volver al menú principal: ");
            esperarM(sc);
            return new MainMenuState();
        }
 
        // Buscar jugador 1
        System.out.println("=== CREAR PARTIDA ===");
        System.out.println();
        Player jugador1 = pedirJugador(sc, 1);
        if (jugador1 == null) {
            return new MainMenuState();
        }
 
        // Buscar jugador 2
        Player jugador2 = pedirJugador(sc, 2);
        if (jugador2 == null) {
            return new MainMenuState();
        }
 
        // Validar que no sean el mismo jugador
        if (jugador1.getName().equalsIgnoreCase(jugador2.getName())) {
            System.out.println("Un jugador no puede competir contra sí mismo.");
            System.out.print("Presione 'm' para volver al menú principal: ");
            esperarM(sc);
            return new MainMenuState();
        }
 
        // Crear y encolar la partida
        try {
            Match nuevaPartida = new Match(jugador1, jugador2);
            pendingMatchDAO.enqueueMatch(nuevaPartida);
            System.out.println();
            System.out.println("Partida creada con éxito:");
            System.out.println(jugador1.getName() + " vs " + jugador2.getName());
            System.out.println("La partida ha sido agregada a la cola de pendientes.");
        } catch (MatchAlreadyExistsException e) {
            System.out.println("Error: esta partida ya existe en la cola.");
        }
 
        System.out.println();
        System.out.print("Presione 'm' para volver al menú principal: ");
        esperarM(sc);
        return new MainMenuState();
    }
 
    /**
     * Solicita al usuario el nombre de un jugador y lo busca en la base de datos.
     * @param sc - Scanner para leer la entrada.
     * @param numero - Número del jugador (1 o 2) para mostrar en pantalla.
     * @return El jugador encontrado, o {@code null} si el usuario canceló.
     */
    private Player pedirJugador(Scanner sc, int numero) {
        while (true) {
            System.out.println("Ingrese el nombre del jugador " + numero + " o 's' para cancelar:");
 
            String input;
            try {
                input = sc.next().trim();
            } catch (NoSuchElementException e) {
                System.out.println("Entrada no válida.");
                continue;
            }
 
            if (input.equalsIgnoreCase("s")) {
                return null;
            }
 
            if (input.length() < 5) {
                System.out.println("El nombre debe tener al menos 5 caracteres.");
                continue;
            }
 
            Player player = playerDAO.getPlayerOrNull(input);
            if (player == null) {
                System.out.println("No existe ningún jugador registrado con el nombre: " + input);
                continue;
            }
 
            return player;
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