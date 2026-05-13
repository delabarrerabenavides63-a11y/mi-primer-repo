

import java.util.Comparator;

public class Jugador implements Comparable<Jugador>{
    protected String nombre;
    protected String alias;
    protected Integer puntuacion;
    
    public Jugador(String nombre, String alias) {
        this.nombre = nombre;
        this.alias = alias;
        this.puntuacion=0;
    }

    @Override
    public int compareTo(Jugador otro) {
        return otro.puntuacion.compareTo(this.puntuacion);
    }

    @Override
    public String toString() {
        return "Jugador [nombre=" + nombre + ", alias=" + alias + ", puntuacion=" + puntuacion + "]";
    }

    

    
}

