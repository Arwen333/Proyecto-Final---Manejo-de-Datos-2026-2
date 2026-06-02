package model;
//version TEMPORAL para pruebas 
import java.time.LocalDate;

public class Match {
    private int id;
    private Player jugador1;
    private Player jugador2;
    private int puntajeJugador1;
    private int puntajeJugador2;
    private LocalDate fecha;
    private String estado;
    
    // Constructor para nueva partida
    public Match(Player jugador1, Player jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.fecha = LocalDate.now();
        this.estado = "pendiente";
        this.puntajeJugador1 = 0;
        this.puntajeJugador2 = 0;
    }
    
    // Constructor completo
    public Match(int id, Player jugador1, Player jugador2, int puntajeJugador1, int puntajeJugador2, LocalDate fecha, String estado) {
        this.id = id;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.puntajeJugador1 = puntajeJugador1;
        this.puntajeJugador2 = puntajeJugador2;
        this.fecha = fecha;
        this.estado = estado;
    }
    
    // Getters
    public int getId() { return id; }
    public Player getJugador1() { return jugador1; }
    public Player getJugador2() { return jugador2; }
    public int getPuntajeJugador1() { return puntajeJugador1; }
    public int getPuntajeJugador2() { return puntajeJugador2; }
    public LocalDate getFecha() { return fecha; }
    public String getEstado() { return estado; }
    
    // Setters
    public void setId(int id) { this.id = id; }
    public void setJugador1(Player jugador1) { this.jugador1 = jugador1; }
    public void setJugador2(Player jugador2) { this.jugador2 = jugador2; }
    public void setPuntajeJugador1(int puntajeJugador1) { this.puntajeJugador1 = puntajeJugador1; }
    public void setPuntajeJugador2(int puntajeJugador2) { this.puntajeJugador2 = puntajeJugador2; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setEstado(String estado) { this.estado = estado; }
    
    // Quién ganó
    public Player getGanador() {
        if (puntajeJugador1 > puntajeJugador2) return jugador1;
        if (puntajeJugador2 > puntajeJugador1) return jugador2;
        return null;
    }
    
    @Override
    public String toString() {
        return "Partida " + id + ": " + jugador1.getNombre() + " vs " + jugador2.getNombre() + 
               " | " + puntajeJugador1 + "-" + puntajeJugador2 + " | " + estado;
    }
}