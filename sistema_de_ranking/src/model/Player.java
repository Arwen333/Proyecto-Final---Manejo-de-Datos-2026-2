package model;
/**
 * Representa a un piloto o competidor dentro del sistema de ranking del juego de carreras.
 * <p>
 * Esta clase actúa como una entidad del dominio que encapsula la información de perfil,
 * progreso y rendimiento de un jugador, incluyendo su nivel de experiencia y el puntaje 
 * que ha acumulado a lo largo de sus distintas partidas en el torneo.
 * </p>
 * <p>
 * Las instancias de esta clase se crean inicialmente en memoria con un identificador (ID) 
 * provisional, el cual es asignado de forma definitiva y única por el mecanismo de persistencia 
 * de la base de datos al momento de registrarse.
 * </p>
 * * @author Nicolas Coello
 */
public class Player {

    private int id;
    private String name;
    private int level;
    private int acumScore;

    /**
     * Crea a un jugador de carreras con un ID temporal y su información
     * de juego.
     * @param name - Nombre del jugador.
     * @param level - Nivel del jugador.
     * @param acumScore - Puntaje acumulado del jugador.
     */
    public Player(String name, int level, int acumScore) {
        id = -1;
        this.name = name;
        this.level = level;
        this.acumScore = acumScore;
    }

    //#region ACCESO
    
    /**
     * @return El identificador único del jugador en la base de datos, 
     * o {@code -1} si la entidad permanece únicamente en memoria.
     */
    public int getId() {
        return id;
    }

    /**
     * Asigna el ID definitivo provisto por el sistema de almacenamiento.
     * @param id - El nuevo ID único para este jugador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return El nombre o pseudónimo del competidor.
     */
    public String getName() {
        return name;
    }

    /**
     * Actualiza el nombre del jugador.
     * @param name - El nuevo nombre para el jugador.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return El nivel de experiencia alcanzado por el jugador.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Modifica el nivel de experiencia del jugador.
     * @param level - El nuevo nivel a asignar.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return La suma total de puntos obtenidos por el jugador en su historial de carreras.
     */
    public int getAcumScore() {
        return acumScore;
    }

    /**
     * Actualiza el puntaje acumulado del jugador tras participar en competiciones o eventos.
     * @param acumScore - El nuevo puntaje acumulado total.
     */
    public void setAcumScore(int acumScore) {
        this.acumScore = acumScore;
    }
    //#endregion
    
    //#region UTILERÍA
    
    /**
     * Devuelve una representación en formato de texto con los detalles completos del perfil del jugador.
     * @return Una cadena formateada para la visualización de los datos en la interfaz de consola.
     */
    @Override
    public String toString() {
        return
            "Nombre: " + name
            + "\nID: " + id
            + "\nNivel: " + level
            + "\nPuntaje acumulado: " + acumScore;
    }
    //#endregion
}