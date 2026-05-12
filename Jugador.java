

import java.util.Comparator;

public class Jugador implements Comparator<Jugador>{
    protected String nombre;
    protected String alias;
    protected Integer puntuacion;
    
    public Jugador(String nombre, String alias) {
        this.nombre = nombre;
        this.alias = alias;
        this.puntuacion=0;
    }

    @Override
    public int compare(Jugador j1, Jugador j2) {
        return j1.puntuacion.compareTo(j2.puntuacion);
    }



    
}

