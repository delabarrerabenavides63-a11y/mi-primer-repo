import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {
    
    // Insertar jugador
    public static void insertar(Jugador j) {
        String insertarQuery="INSERT INTO jugadores (nombre, alias, puntuacion) VALUES (?, ?, ?);";
        try (Connection con=ConexionDB.conectar();
            PreparedStatement ps=con.prepareStatement(insertarQuery)) {
            ps.setString(1, j.getNombre());
            ps.setString(2, j.getAlias());
            ps.setInt(3, j.getPuntuacion());
            ps.executeUpdate();
            System.out.println("Nuevo jugador registrado");
        } catch (Exception e) {
            System.out.println("Error al insertar el jugador");
        }
    }
    
    // Obtener todos los jugadores
    public static List<Jugador> obtenerTodos() {
        List<Jugador> lista = new ArrayList<>();
        String consultaQuery="SELECT * FROM jugadores;";
        try(Connection con=ConexionDB.conectar();
            Statement stmn= con.createStatement();
            ResultSet rs=stmn.executeQuery(consultaQuery)){
            while (rs.next()) {
                Jugador j=new Jugador(rs.getString(1), rs.getString(2),rs.getInt(3));
                lista.add(j);
            }

        } catch (Exception e) {
            System.out.println("Hubo un error al obtener los jugadores "+e.getMessage());
        }
        // Para cada fila: crear Jugador y añadir a lista
        return lista;
    }
    
    // Obtener un jugador por alias
    public static Jugador obtenerPorAlias(String alias) {
        String consultaAliasQuery="SELECT * FROM jugadores WHERE alias = ?;";
        try(Connection con=ConexionDB.conectar();
            Statement stmn= con.createStatement();
            ResultSet rs=stmn.executeQuery(consultaAliasQuery)){
                while (rs.next()) {
                    if(rs.getString(2).equals(alias)){
                    Jugador j= new Jugador(rs.getString(1),rs.getString(2),rs.getInt(3));
                    System.out.println("Jugador encontrado");
                    return j;
                    }
                    
                }
            }
        // Si existe, devolver el Jugador. Si no, devolver null
        catch (Exception e) {
            System.out.println("Error al obtener la info del alias");
    }
        return null;
}
    
    // Actualizar puntuación
    public static void actualizarPuntuacion(String alias, int nuevaPuntuacion) {
        String updateQuery="UPDATE jugadores SET puntuacion = ? WHERE alias = ?;";
        try(Connection con=ConexionDB.conectar();
            PreparedStatement ps= con.prepareStatement(updateQuery);
            ){
            ps.setInt(1, nuevaPuntuacion);
            ps.setString(2, alias);
            ps.executeUpdate();
            System.out.println("Puntuacion actualiza exitosamente");
        } catch (Exception e) {
            System.out.println("Hubo un error al obtener los jugadores "+e.getMessage());
        }
    }
    
    // Eliminar jugador
    public static void eliminar(String alias) {
    String eliminarQuery="DELETE FROM jugadores WHERE alias = ?;";
    try(Connection con=ConexionDB.conectar();
            PreparedStatement ps= con.prepareStatement(eliminarQuery);
            ){
            ps.setString(1, alias);
            ps.executeUpdate();
            System.out.println("Jugador eliminado exitosamente");
        } catch (Exception e) {
            System.out.println("Hubo un error al obtener los jugadores "+e.getMessage());
        }
    }
}