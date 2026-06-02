package controller.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import exceptions.MatchAlreadyExistsException;
import exceptions.PlayerAlreadyExistsException;
import exceptions.PlayerNotFoundException;
import model.Match;
import model.Player;

/**
 * Clase de prueba para probar el correcto flujo de la aplicación.
 * Simula una base de datos en memoria.
 * @author Arwen Ortiz, Brayan Montiel
 */
public class TestDAO implements IMatchHistoryDAO, IPendingMatchDAO, IPlayerDAO {
    
    // Simulación de base de datos en memoria
    private ArrayList<Player> jugadores = new ArrayList<>();
    private Queue<Match> colaPendientes = new LinkedList<>();
    private ArrayList<Match> historialPartidas = new ArrayList<>();
    private int nextPlayerId = 1;
    private int nextMatchId = 1;
    
    @Override
    public void register(Player player) throws PlayerAlreadyExistsException {
        if (isRegistered(player.getNombre())) {
            throw new PlayerAlreadyExistsException("Player " + player.getNombre() + " already exists");
        }
        player.setId(nextPlayerId++);
        jugadores.add(player);
        System.out.println("[TESTDAO] Player registered: " + player);
    }
    
    @Override
    public Player getPlayerOrNull(String name) {
        for (Player p : jugadores) {
            if (p.getNombre().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }
    
    @Override
    public Player getPlayerOrNull(int id) {
        for (Player p : jugadores) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    
    @Override
    public boolean isRegistered(String name) {
        for (Player p : jugadores) {
            if (p.getNombre().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean isAnyPlayerRegistered() {
        return !jugadores.isEmpty();
    }
    
    @Override
    public ArrayList<Player> getRegisteredPlayers() {
        return new ArrayList<>(jugadores);
    }
    
    @Override
    public ArrayList<Player> getRanking() {
        ArrayList<Player> ranking = new ArrayList<>(jugadores);
        ranking.sort((p1, p2) -> Integer.compare(p2.getPuntajeAcumulado(), p1.getPuntajeAcumulado()));
        return ranking;
    }
    
    @Override
    public int getPlayersCount() {
        return jugadores.size();
    }
    
    @Override
    public void enqueueMatch(Match match) throws MatchAlreadyExistsException {
        match.setId(nextMatchId++);
        colaPendientes.offer(match);
        System.out.println("[TESTDAO] Match enqueued: " + match);
    }
    
    @Override
    public int getPendingMatchesCount() {
        return colaPendientes.size();
    }
    
    @Override
    public Match consumeNextPendingMatch() {
        Match match = colaPendientes.poll();
        if (match != null) {
            System.out.println("[TESTDAO] Match consumed from queue: " + match);
        }
        return match;
    }
    
    @Override
    public List<Match> getLastMatches(int playerId, int n) throws PlayerNotFoundException {
        Player jugador = getPlayerOrNull(playerId);
        if (jugador == null) {
            throw new PlayerNotFoundException("Player with ID " + playerId + " not found");
        }
        
        List<Match> resultado = new ArrayList<>();
        for (Match m : historialPartidas) {
            if (m.getEstado() != null && m.getEstado().equals("completada")) {
                if (m.getJugador1().getId() == playerId || m.getJugador2().getId() == playerId) {
                    resultado.add(m);
                }
            }
        }
        
        resultado.sort((m1, m2) -> m2.getFecha().compareTo(m1.getFecha()));
        if (resultado.size() > n) {
            return resultado.subList(0, n);
        }
        return resultado;
    }
    
    @Override
    public void saveCompletedMatch(Match match) throws MatchAlreadyExistsException {
        match.setEstado("completada");
        historialPartidas.add(match);
        System.out.println("[TESTDAO] Completed match saved: " + match);
    }
    
    @Override
    public int getMatchesCount() {
        return historialPartidas.size();
    }
}