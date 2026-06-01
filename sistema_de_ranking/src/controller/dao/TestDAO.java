package controller.dao;

import java.util.ArrayList;
import java.util.List;

import exceptions.MatchAlreadyExistsException;
import exceptions.PlayerAlreadyExistsException;
import exceptions.PlayerNotFoundException;
import model.Match;
import model.Player;

/**
 * Clase de prueba para probar el correcto flujo de la aplicación.
 * @author Arwen Ortiz, Brayan Montiel
 */
public class TestDAO implements IMatchHistoryDAO, IPendingMatchDAO, IPlayerDAO {

    @Override
    public void register(Player player) throws PlayerAlreadyExistsException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public Player getPlayerOrNull(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerOrNull'");
    }

    @Override
    public Player getPlayerOrNull(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerOrNull'");
    }

    @Override
    public boolean isRegistered(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isRegistered'");
    }

    @Override
    public boolean isAnyPlayerRegistered() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAnyPlayerRegistered'");
    }

    @Override
    public ArrayList<Player> getRegisteredPlayers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRegisteredPlayers'");
    }

    @Override
    public ArrayList<Player> getRanking() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRanking'");
    }

    @Override
    public int getPlayersCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayersCount'");
    }

    @Override
    public void enqueueMatch(Match match) throws MatchAlreadyExistsException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enqueueMatch'");
    }

    @Override
    public int getPendingMatchesCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPendingMatchesCount'");
    }

    @Override
    public Match consumeNextPendingMatch() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consumeNextPendingMatch'");
    }

    @Override
    public List<Match> getLastMatches(int playerId, int n) throws PlayerNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastMatches'");
    }

    @Override
    public void saveCompletedMatch(Match match) throws MatchAlreadyExistsException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveCompletedMatch'");
    }

    @Override
    public int getMatchesCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesCount'");
    }
    
}
