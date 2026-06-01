package controller.dao;

import java.util.List;

import exceptions.MatchAlreadyExistsException;
import exceptions.PlayerNotFoundException;
import model.Match;
/**
 * Proporciona una interfaz común para acceder a la base de datos para realizar consultas
 * y cambios en el historial de partidas jugadas.
 * @author Arwen Ortiz, Brayan Montiel.
 */
public interface IMatchHistoryDAO {

    /**
     * Regresa un número específico de las últimas partidas jugadas por un jugador, del
     * registro en la base de datos.
     * @param playerId - ID del jugador.
     * @param n - Número de partidas que se desean obtener.
     * @return Una lista con máximo las últimas {@code n} partidas jugadas por el jugador
     * con id {@code playerId}.
     * @throws PlayerNotFoundException Si no se encuentra registrado un jugador con el id
     * {@code playerId}.
     */
    List<Match> getLastMatches(int playerId, int n) throws PlayerNotFoundException;
    /**
     * Guarda una partida nueva dentro de la base de datos.
     * @param match - Partida que se registra.
     * @throws MatchAlreadyExistsException Si una partida con el mismo ID de
     * {@code match} ya ha sido registrada en la base de datos.
     */
    void saveCompletedMatch(Match match) throws MatchAlreadyExistsException;
    /**
     * Obtiene el número de partidas totales que han sido registradas en la base de datos.
     * @return El número de partidas registradas.
     */
    int getMatchesCount();
}
