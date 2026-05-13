import java.util.*;
public class Gestor {
    protected Map<String,Jugador> jugadores;
    protected Map<String,List<Jugador>> torneos;

    public Gestor(){
        this.jugadores=new HashMap<>();
        this.torneos=new HashMap<>();
    }

    public void nuevoJugador(Jugador nuevo){
        if(!jugadores.containsKey(nuevo.alias)){
            jugadores.put(nuevo.alias,nuevo);
        }else{
            System.out.println("Jugador ya existente");
        }
    }

    public void nuevoTorneo(String nombreTorneo){
        if(!torneos.containsKey(nombreTorneo)){
            List <Jugador> participantes= new ArrayList<>();
            torneos.put(nombreTorneo,participantes);
        }else{
            System.out.println("Torneo ya existente");
        }
    }

    public void inscribirJugador(String nombreTorneo,String alias){
        torneos.get(nombreTorneo).add(jugadores.get(alias));

    }
    
    public List<Jugador> rankingTorneo(String nombreTorneo){
        if(torneos.containsKey(nombreTorneo)){
    List<Jugador> jugadoresTorneo= new ArrayList<>(torneos.get(nombreTorneo));
    Collections.sort(jugadoresTorneo);
    return jugadoresTorneo;
    }else{
        return new ArrayList<>();
        }
    }

    public Set<Jugador> rankingGlobal (){
        Set <Jugador> todosJugadores= new TreeSet<>();
        for (List<Jugador> lista : torneos.values()) {
            todosJugadores.addAll(lista);
        }
        return todosJugadores;
    }

    public void eliminarJugadorTorneo(String torneo, String aliasJugador) {
    if (torneos.containsKey(torneo)) {
        Iterator<Jugador> it = torneos.get(torneo).iterator();
        while (it.hasNext()) {
            Jugador j = it.next();
            if (j.alias.equals(aliasJugador)) {
                it.remove();
                break;
            }
        }
        }
    }
    

    public List<String> torneoJugador(String aliasJugador) {
    List<String> torneosDeJugador = new ArrayList<>();
    for (Map.Entry<String, List<Jugador>> entrada : torneos.entrySet()) {
        for (Jugador j : entrada.getValue()) {
            if (j.alias.equals(aliasJugador)) {
                torneosDeJugador.add(entrada.getKey());
            }
        }
    }
    return torneosDeJugador;
}

}

