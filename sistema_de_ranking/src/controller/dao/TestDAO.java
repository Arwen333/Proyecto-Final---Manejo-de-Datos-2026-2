package controller.dao;
import java.util.ArrayList;
import java.util.List;
import exceptions.MatchAlreadyExistsException;
import exceptions.PlayerAlreadyExistsException;
import exceptions.PlayerNotFoundException;
import model.Match;
import model.Player;
/**
 * Clase de prueba para comprobar el correcto flujo de la aplicación.
 * @author Arwen Ortiz, Brayan Montiel
 */
public class TestDAO implements IMatchHistoryDAO, IPendingMatchDAO, IPlayerDAO {
    private static TestDAO instance;
    private TestDAO() {
        // Aquí inicializa a todos los campos con valores por defecto (al iniciar la app por 1era vez), menos a 'instance'
    }
    public static TestDAO getInstance() {
        if (instance == null)
        {
            instance = new TestDAO();
        }
        return instance;
    }
 
    // ── De Brayan - no modificar ─────────────────────────────────────────────
 
    @Override
    public void register(Player player) throws PlayerAlreadyExistsException {
        System.out.println("[TestDAO] Método register ejecutado con éxito.");
    }
    @Override
    public Player getPlayerOrNull(String name) {
        System.out.println("[TestDAO] Método getPlayerOrNull ejecutado con éxito");
        return new Player("Panchito", 0, 0);
    }
    @Override
    public Player getPlayerOrNull(int id) {
        System.out.println("[TestDAO] Método getPlayerOrNull ejecutado con éxito");
        return new Player("Panchito", 0, 0);
    }
    @Override
    public boolean isRegistered(String name) {
        System.out.println("[TestDAO] Método isRegistered ejecutado con éxito");
        return false;
    }
    @Override
    public boolean isAnyPlayerRegistered() {
        System.out.println("[TestDAO] Método isAnyPlayerRegistered ejecutado con éxito");
        return true;
    }
    @Override
    public ArrayList<Player> getRegisteredPlayers() {
        System.out.println("[TestDAO] Método getRegisteredPlayers ejecutado con éxito");
        ArrayList<Player> output = new ArrayList<>();
        output.add(new Player("Panchito", 0, 0));
        return output;
    }
 
    // ── De Arwen - métodos para mis estados ──────────────────────────────────
 
    @Override
    public ArrayList<Player> getRanking() {
        System.out.println("[TestDAO] Generando ranking");
        ArrayList<Player> output = new ArrayList<>();
        output.add(new Player("Carlos", 3, 320));
        output.add(new Player("Ana", 2, 150));
        output.add(new Player("Luis", 1, 80));
        return output;
    }
    @Override
    public int getPlayersCount() {
        System.out.println("[TestDAO] Método getPlayersCount ejecutado con éxito");
        return 3;
    }
    @Override
    public void enqueueMatch(Match match) throws MatchAlreadyExistsException {
        System.out.println("[TestDAO] Partida encolada con éxito.");
    }
    @Override
    public int getPendingMatchesCount() {
        System.out.println("[TestDAO] Método getPendingMatchesCount ejecutado con éxito");
        return 1;
    }
    @Override
    public Match consumeNextPendingMatch() {
        System.out.println("[TestDAO] Método consumeNextPendingMatch ejecutado con éxito");
        Player p1 = new Player("Carlos", 3, 320);
        Player p2 = new Player("Ana", 2, 150);
        return new Match(p1, p2);
    }
    @Override
    public List<Match> getLastMatches(int playerId, int n) throws PlayerNotFoundException {
        System.out.println("[TestDAO] Método getLastMatches ejecutado con éxito");
        List<Match> output = new ArrayList<>();
        Player p1 = new Player("Carlos", 3, 320);
        Player p2 = new Player("Ana", 2, 150);
        output.add(new Match(p1, p2));
        return output;
    }
    @Override
    public void saveCompletedMatch(Match match) throws MatchAlreadyExistsException {
        System.out.println("[TestDAO] Partida guardada en historial con éxito.");
    }
    @Override
    public int getMatchesCount() {
        System.out.println("[TestDAO] Método getMatchesCount ejecutado con éxito");
        return 1;
    }
}