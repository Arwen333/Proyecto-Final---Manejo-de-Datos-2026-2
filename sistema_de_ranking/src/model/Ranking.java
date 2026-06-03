package model;
//Ranking temporal se nos pide en app.java
import java.util.ArrayList;
import java.util.List;

public class Ranking {
    private List<Player> jugadores;
    
    public Ranking() {
        this.jugadores = new ArrayList<>();
    }
    
    public Ranking(List<Player> jugadores) {
        this.jugadores = new ArrayList<>(jugadores);
        ordenar();
    }
    
    public void agregarJugador(Player player) {
        jugadores.add(player);
        ordenar();
    }
    
    private void ordenar() {
        jugadores.sort((p1, p2) -> Integer.compare(p2.getAcumScore(), p1.getAcumScore()));
    }
    
    public List<Player> getRanking() {
        ordenar();
        return new ArrayList<>(jugadores);
    }
    
    public int getPosicion(Player player) {
        return jugadores.indexOf(player) + 1;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n=== RANKING ===\n");
        for (int i = 0; i < jugadores.size(); i++) {
            sb.append((i + 1) + ". " + jugadores.get(i).getName() + " - " + jugadores.get(i).getAcumScore() + " pts\n");
        }
        return sb.toString();
    }
}