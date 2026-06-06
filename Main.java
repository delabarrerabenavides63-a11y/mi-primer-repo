import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConexionDB.crearTablas();
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
                    String nombre=sc.nextLine();
                    System.out.println("Ingresa el alias del jugador");
                    String alias=sc.nextLine();
                    System.out.println("Ingrese la puntuacion del jugador");
                    int puntuacion=sc.nextInt();
                    sc.nextLine();

                    gestorPrincipal.nuevoJugador(new Jugador(nombre,alias,puntuacion));
                    break;
                case 2:
                    System.out.println("Ingrese el nombre de torneo");
                    String nombreTorneo=sc.nextLine();
                    gestorPrincipal.nuevoTorneo(nombreTorneo);
                    break;
                case 3:
                    System.out.println("Ingresa el nombre del Torneo");
                    nombreTorneo=sc.nextLine();
                    System.out.println("Ingresa el alias del jugador");
                    alias=sc.nextLine();
                    gestorPrincipal.inscribirJugador(nombreTorneo,alias);
                    break;
                case 4:
                    System.out.println("Ingrese el alias de los dos Jugadores");
                    String aliasJ1=sc.nextLine();
                    String aliasJ2=sc.nextLine();
                    System.out.println("Y por ultimo el alias del ganador");
                    String aliasGanador=sc.nextLine();
                    gestorPrincipal.registrarPartida(aliasJ1,aliasJ2,aliasGanador);
                    break;
                case 5:
                    System.out.println("Ingresa el nombre del torneo del que quieres tener el ranking");
                    nombreTorneo=sc.nextLine();
                    System.out.println(gestorPrincipal.rankingTorneo(nombreTorneo));
                    break;
                case 6:
                    System.out.println(gestorPrincipal.rankingGlobal());
                    break;
                case 7:
                    System.out.println("Ingresa el alias del jugador");
                    alias=sc.nextLine();
                    int ganadas=gestorPrincipal.partidasGanadasJugador(alias);
                    System.out.println(gestorPrincipal.partidasGanadasJugador(ganadas+" partidas ganadas"));
                    break;
                case 8:
                    System.out.println("El mejor jugado del torneo es: "+gestorPrincipal.mejorJugador());
                    break;
                case 9:
                    System.out.println("Ingresa el alias del jugador que buscas");
                    alias=sc.nextLine();
                    System.out.println(gestorPrincipal.torneoJugador(alias));
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while(entrada!=10);
    }
}
