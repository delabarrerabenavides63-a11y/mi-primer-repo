public class Partida {
    protected Jugador jugador1;
    protected Jugador jugador2;
    protected String aliasGanador;

    public Partida(Jugador j1,Jugador j2,String aliasGanador){
        this.jugador1=j1;
        this.jugador2=j2;
        this.aliasGanador=aliasGanador;
    }

    public Partida(String aliasJ1, String aliasJ2, String aliasGanador) {
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Partida [(jugador1=" + jugador1.alias + ", jugador2=" + jugador2.alias + ")GANADOR: "+aliasGanador+"]";
    }
    
    
}
