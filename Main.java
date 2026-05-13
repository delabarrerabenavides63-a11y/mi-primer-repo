import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner (System.in);
        Gestor gestorPrincipal= new Gestor();
        int entrada=0;
        do{
            System.out.println(
                "Bienvenido al Gestor de Torneos de videojuegos, que quieres hacer hoy?\n"+
                    "1.Nuevo Jugador\n"+
                    "2.Nuevo Torneo\n"+
                    "3.Inscribir jugador en un torneo\n"+
                    "4.Registrar Partida\n"+
                    "5.Raking de un torneo\n"+
                    "6.Raking Global\n"+
                    "7.Partidas Ganadas de un jugador\n"+
                    "8.Mejor jugador\n"+
                    "9.En que torneos esta un jugador\n"+
                    "10.Salir");
            entrada=sc.nextInt();
            sc.nextLine();
            switch(entrada){
                case 1:
                    System.out.println("Ingresa el nombre del jugador y su alias");
                    gestorPrincipal.nuevoJugador(new Jugador(sc.nextLine(),sc.next()));
                    break;
                case 2:
                    System.out.println("Ingrese el nombre de torneo");
                    gestorPrincipal.nuevoTorneo(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Ingresa el nombre del Torneo y el alias del jugador");
                    gestorPrincipal.inscribirJugador(sc.nextLine(),sc.next());
                    break;
                case 4:
                    System.out.println("Ingrese el alias de los dos Jugadores y por ultimo el alias del ganador");
                    gestorPrincipal.registrarPartida(sc.next(),sc.next(),sc.next());
                    break;
                case 5:
                    System.out.println("Ingresa el nombre del torneo del que quieres tener el ranking");
                    System.out.println(gestorPrincipal.rankingTorneo(sc.nextLine()));
                    break;
                case 6:
                    System.out.println(gestorPrincipal.rankingGlobal());
                    break;
                case 7:
                    System.out.println("Ingresa el alias del jugador");
                    System.out.println(gestorPrincipal.partidasGanadasJugador(sc.next()+" partidas ganadas"));
                    break;
                case 8:
                    System.out.println("El mejor jugado del torneo es: "+gestorPrincipal.mejorJugador());
                    break;
                case 9:
                    System.out.println("Ingresa el alias del jugador que buscas");
                    System.out.println(gestorPrincipal.torneoJugador(sc.next()));
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while(entrada!=10);
    }
}
