package model;
// version TEMPORAL solo para Prueba 
public class Player {
    private int id;
    private String nombre;
    private int nivel;
    private int puntajeAcumulado;
    
    // Constructor para nuevo jugador
    public Player(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.puntajeAcumulado = 0;
    }
    
    // Constructor completo
    public Player(int id, String nombre, int nivel, int puntajeAcumulado) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.puntajeAcumulado = puntajeAcumulado;
    }
    
    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getNivel() { return nivel; }
    public int getPuntajeAcumulado() { return puntajeAcumulado; }
    
    // Setters
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    public void setPuntajeAcumulado(int puntajeAcumulado) { this.puntajeAcumulado = puntajeAcumulado; }
    
    // Método para agregar puntaje
    public void agregarPuntaje(int puntos) {
        this.puntajeAcumulado += puntos;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | " + nombre + " | Nivel: " + nivel + " | Puntaje: " + puntajeAcumulado;
    }
}