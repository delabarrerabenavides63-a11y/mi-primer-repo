import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TorneoDAO {
    
    // Insertar torneo
    public static void insertar(String nombre) {
        String insertarQuery="INSERT INTO torneos (nombre) VALUES (?);";
        try (Connection con=ConexionDB.conectar();
            PreparedStatement ps=con.prepareStatement(insertarQuery)) {
            ps.setString(1,nombre);
            ps.executeUpdate();
            System.out.println("Nuevo torneo registrado");
        } catch (Exception e) {
            System.out.println("Error al insertar el torneo");
        }
    }
    
    // Obtener todos los torneos
    public static List<String> obtenerTodos() {
        List<String> lista = new ArrayList<>();
        String consultaQuery="SELECT * FROM torneos;";
        try(Connection con=ConexionDB.conectar();
            Statement stmn= con.createStatement();
            ResultSet rs=stmn.executeQuery(consultaQuery)){
            while (rs.next()) {
                lista.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Hubo un error al obtener los torneos "+e.getMessage());
        }
        // Para cada fila: crear Jugador y añadir a lista
        return lista;
    }
    
    // Verificar si existe un torneo
    public static boolean existe(String nombre) {
    String consultaAliasQuery="SELECT * FROM torneos WHERE nombre = ?;";
        try(Connection con=ConexionDB.conectar();
            Statement stmn= con.createStatement();
            ResultSet rs=stmn.executeQuery(consultaAliasQuery)){
                while (rs.next()) {
                    if(rs.getString(1).equals(nombre)){
                    System.out.println("Torneo encontrado");
                    return true;
                    }
                    
                }
            }
        // Si existe, devolver el Jugador. Si no, devolver null
        catch (Exception e) {
            System.out.println("Error al obtener la info del nombre");
    }
        return false;
}
    
    // Eliminar torneo
    public static void eliminar(String nombre) {
        String eliminarQuery="DELETE FROM torneos WHERE nombre = ?;";
    try(Connection con=ConexionDB.conectar();
            PreparedStatement ps= con.prepareStatement(eliminarQuery);
            ){
            ps.setString(1, nombre);
            ps.executeUpdate();
            System.out.println("Torneo eliminado exitosamente");
        } catch (Exception e) {
            System.out.println("Hubo un error al obtener el torneo "+e.getMessage());
        }
    }
}
