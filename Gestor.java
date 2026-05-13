import java.util.*;
public class Gestor {
    protected Map<String,Jugador> jugadores;
    protected Map<String,List<Jugador>> torneos;
    protected List<Partida> partidas;

    public Gestor(){
        this.jugadores=new HashMap<>();
        this.torneos=new HashMap<>();
        this.partidas= new ArrayList<>();
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

    public void registrarPartida(Jugador j1,Jugador j2,String aliasGanador){
        partidas.add(new Partida(j1, j2, aliasGanador));
        if(j1.equals(aliasGanador)){
            j1.puntuacion+=3;
        }else if(j2.equals(aliasGanador)){
            j2.puntuacion+=3;
        }else{
            System.out.println("Alias ganador no correspondiente a los jugadores");
        }
    }

    public int partidasGanadasJugador(String alias){
        int partidasGanadas=0;
        for (Partida partida : partidas) {
            if(partida.aliasGanador.equals(alias)){
                partidasGanadas++;
            }
        }
        return partidasGanadas;
    }

    public Jugador mejorJugador(){
        Jugador mejorJugador=Collections.max(rankingGlobal());
        return mejorJugador;
    }
}

