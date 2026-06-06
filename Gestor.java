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
    
    public void nuevoJugador(Jugador nuevo) {
        if (JugadorDAO.obtenerPorAlias(nuevo.alias) == null) {
            JugadorDAO.insertar(nuevo);
            System.out.println("Jugador creado.");
        } else {
            System.out.println("Jugador ya existe.");
        }
    }
    
    public void nuevoTorneo(String nombreTorneo) {
        if (!TorneoDAO.existe(nombreTorneo)) {
            TorneoDAO.insertar(nombreTorneo);
            System.out.println("Torneo creado.");
        } else {
            System.out.println("Torneo ya existe.");
        }
    }
    
    public void inscribirJugador(String nombreTorneo, String alias) {
        if (!TorneoDAO.existe(nombreTorneo)) {
            System.out.println("Torneo no existe.");
            return;
        }
        if (JugadorDAO.obtenerPorAlias(alias) == null) {
            System.out.println("Jugador no existe.");
            return;
        }
        ParticipacionDAO.inscribir(nombreTorneo, alias);
        System.out.println("Jugador inscrito.");
    }
    
    public List<Jugador> rankingTorneo(String nombreTorneo){
        if(TorneoDAO.existe(nombreTorneo)){
    List<Jugador> jugadoresTorneo= new ArrayList<>(ParticipacionDAO.obtenerJugadoresDeTorneo(nombreTorneo));
    Collections.sort(jugadoresTorneo);
    return jugadoresTorneo;
    }else{
        return new ArrayList<>();
        }
    }

    public Set<Jugador> rankingGlobal (){
        Set <Jugador> todosJugadores= new TreeSet<>(JugadorDAO.obtenerTodos());
        return todosJugadores;
    }

    public void eliminarJugadorTorneo(String torneo, String aliasJugador) {
    if(!TorneoDAO.existe(torneo)){
        System.out.println("torneo no existente");
        return;
    }
    if(JugadorDAO.obtenerPorAlias(aliasJugador)==null){
        System.out.println("Jugador no existente");
        return;
    }
    ParticipacionDAO.eliminar(torneo, aliasJugador);
    }
    

    public List<String> torneoJugador(String aliasJugador) {
    return ParticipacionDAO.obtenerTorneosDeJugador(aliasJugador);
    }

    public void registrarPartida(String aliasJ1 ,String aliasJ2,String aliasGanador){
        if(JugadorDAO.obtenerPorAlias(aliasJ1)==null || JugadorDAO.obtenerPorAlias(aliasJ2)==null){
            System.out.println("Alguno de los dos jugadores no existe");
        }else{
            PartidaDAO.insertar(aliasJ1, aliasJ2, aliasGanador);
        }
    }

    public int partidasGanadasJugador(String alias){
        return PartidaDAO.contarGanadas(alias);
    }

    public Jugador mejorJugador(){
        List<Jugador> rankingLista=new ArrayList<>(rankingGlobal());
        Jugador mejorJugador=Collections.max(rankingLista);
        return mejorJugador;
    }
}

