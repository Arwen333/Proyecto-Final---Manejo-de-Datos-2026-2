package controller.dao;

import java.util.ArrayList;

import exceptions.PlayerAlreadyExistsException;
import model.Player;
/**
 * Declara una interfaz común para cualquier Data Access Object (DAO) que
 * opere sobre la base de datos con la entidad Player.
 * @author Arwen Ortiz, Brayan Montiel.
 */
public interface IPlayerDAO {

    /**
     * Registra a un jugador en la base de datos.
     * @param player - Jugador a registrar.
     * @throws PlayerAlreadyExistsException Si el jugador ya había sido
     * registrado.
     */
    void register(Player player) throws PlayerAlreadyExistsException;
    /**
     * Obtiene a un jugador en la base de datos por su nombre.
     * @param name - El nombre del jugador que se busca en la base de datos.
     * @return Al jugador con nombre {@code name} si lo encuentra, o {@code null}
     * si no.
     */
    Player getPlayerOrNull(String name);
    /**
     * Obtiene a un jugador en la base de datos por su ID.
     * @param id - El ID del jugador que se busca en la base de datos.
     * @return Al jugador con ID {@code id} si lo encuentra, o {@code null}
     * si no.
     */
    Player getPlayerOrNull(int id);
    /**
     * Indica si un nombre ya está registrado en la base de datos.
     * @param name - El nombre que se busca saber si ya ha sido registrado.
     * @return - {@code true} si {@code name} ya está en uso por algún jugador
     * en la base de datos, y {@code false} si no.
     */
    boolean isRegistered(String name);
    /**
     * Indica si algún jugador ha sido registrado en la base de datos.
     * @return {@code true} si existe al menos un jugador registrado en la base
     * de datos, y {@code false} si no.
     */
    boolean isAnyPlayerRegistered();
    /**
     * Enlista todos los jugadores registrados en la base de datos.
     * @return - Una lista con todos los jugadores registrados en la base de datos,
     * vacía en caso de que ninguno lo esté aún.
     */
    ArrayList<Player> getRegisteredPlayers();
    /**
     * Enlista a los jugadores de mayor a menor puntaje, consultando la base de datos.
     * @return - Una lista con todos los jugadores ordenados de mayor a menor puntaje,
     * vacía en caso de que ninguno haya sido registrado aún.
     */
    ArrayList<Player> getRanking();
    /**
     * Obtiene la cantidad de jugadores registrados en la base de datos.
     * @return El número de jugadores.
     */
    int getPlayersCount();
}
