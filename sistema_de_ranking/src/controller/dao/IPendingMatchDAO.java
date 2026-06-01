package controller.dao;

import exceptions.MatchAlreadyExistsException;
import model.Match;
/**
 * Proporciona una interfaz común para el acceso a la base de datos, para todo lo
 * relacionado con el manejo de partidas pendientes.
 * @author Arwen Ortiz, Brayan Montiel.
 */
public interface IPendingMatchDAO {

    /**
     * Agrega una partida a la cola de partidas pendientes en la base de datos.
     * @param match - Partida que se añade a la cola de pendientes.
     * @throws MatchAlreadyExistsException Si ya existe una partida registrada
     * con el mismo ID de {@code match} dentro de la cola o en la base de datos.
     */
    void enqueueMatch(Match match) throws MatchAlreadyExistsException;
    /**
     * Calcula el número de partidas pendientes.
     * @return - El número de partidas pendientes.
     */
    int getPendingMatchesCount();
    /**
     * Regresa la siguiente partida pendiente en la cola.
     * @return La partida que sigue en la cola, o {@code null} si ya no queda
     * ninguna pendiente.
     */
    Match consumeNextPendingMatch();
}
