import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipacionDAO {
    
    // Inscribir jugador en torneo
    public static void inscribir(String nombreTorneo, String aliasJugador) {
        String insertarQuery="INSERT INTO participaciones (nombre_torneo, alias_jugador) VALUES (?, ?);";
    try(Connection con=ConexionDB.conectar();
            PreparedStatement ps= con.prepareStatement(insertarQuery);
            ){
            ps.setString(1,nombreTorneo);
            ps.setString(2,aliasJugador);
            ps.executeUpdate();
            System.out.println("Jugador inscrito exitosamente");
        } catch (Exception e) {
            System.out.println("Hubo un error al obtener los jugadores "+e.getMessage());
        }
    }
    
    
    // Obtener jugadores de un torneo
    public static List<Jugador> obtenerJugadoresDeTorneo(String nombreTorneo) {
        List<Jugador> lista = new ArrayList<>();
        String consultaQuery="SELECT j.* FROM jugadores j "+ 
            "JOIN participaciones p ON j.alias = p.alias_jugador "+
            "WHERE p.nombre_torneo = ?;";
        try(Connection con=ConexionDB.conectar();
            PreparedStatement ps= con.prepareStatement(consultaQuery)){
            ps.setString(1, nombreTorneo);
            try(ResultSet rs=ps.executeQuery()){
                while (rs.next()) {
                Jugador j=new Jugador(rs.getString(1), rs.getString(2),rs.getInt(3));
                lista.add(j);
            }
            }
        } catch (Exception e) {
            System.out.println("Hubo un error al obtener los jugadores "+e.getMessage());
        }
        return lista;
    }
    
    // Obtener torneos de un jugador
    public static List<String> obtenerTorneosDeJugador(String aliasJugador) {
        List<String> lista = new ArrayList<>();
        String consultaQuery="SELECT nombre_torneo FROM participaciones WHERE alias_jugador = ?;";
        try (Connection con=ConexionDB.conectar();
            PreparedStatement ps= con.prepareStatement(consultaQuery)
        ) {
            ps.setString(1, aliasJugador);
            try (ResultSet rs=ps.executeQuery()) {
                while(rs.next()){
                    lista.add(rs.getString(1));
                }
            } catch (Exception e) {
                System.out.println("Hubo un error al obtener los torneos "+e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("Error en la conexion");
        }

        return lista;
    }
    
    // Eliminar jugador de un torneo
    public static void eliminar(String nombreTorneo, String aliasJugador) {
        String eliminarQuery="DELETE FROM participaciones WHERE nombre_torneo = ? AND alias_jugador = ?;";
        try (Connection con=ConexionDB.conectar();
            PreparedStatement ps= con.prepareStatement(eliminarQuery)) {
            ps.setString(1,nombreTorneo);
            ps.setString(2,aliasJugador);
            ps.executeUpdate();
        } catch (Exception e) {
        System.out.println("No se pudo eliminar el jugador");
        }
    }
}
