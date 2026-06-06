

import java.util.Comparator;

public class Jugador implements Comparable<Jugador>{
    protected String nombre;
    protected String alias;
    protected Integer puntuacion;
    
    public Jugador(String nombre, String alias, Integer puntuacion) {
        this.nombre = nombre;
        this.alias = alias;
        this.puntuacion=puntuacion;
    }

    
    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getAlias() {
        return alias;
    }


    public void setAlias(String alias) {
        this.alias = alias;
    }


    public Integer getPuntuacion() {
        return puntuacion;
    }


    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
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

