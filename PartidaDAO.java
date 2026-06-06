import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {
    
    // Registrar partida
    public static void insertar(String aliasJ1, String aliasJ2, String aliasGanador) {
        String insertarQuery="INSERT INTO partidas (alias_j1, alias_j2, alias_ganador) VALUES (?, ?, ?);";
        try (Connection con=ConexionDB.conectar();
            PreparedStatement ps=con.prepareStatement(insertarQuery)) {
            ps.setString(1, aliasJ1);
            ps.setString(2, aliasJ2);
            ps.setString(3, aliasGanador);
            ps.executeUpdate();
            
            if(aliasJ1.equals(aliasGanador)){
                JugadorDAO.actualizarPuntuacion(aliasJ1, JugadorDAO.obtenerPorAlias(aliasJ1).getPuntuacion()+3);
            }else{
                JugadorDAO.actualizarPuntuacion(aliasJ2, JugadorDAO.obtenerPorAlias(aliasJ2).getPuntuacion()+3);
            }
            System.out.println("Nueva partida registrado");

        } catch (Exception e) {
            System.out.println("Error al insertar el jugador");
        }

        // Y luego actualizar la puntuación del ganador
    }
    
    // Obtener todas las partidas
    public static List<Partida> obtenerTodas() {
        List<Partida> lista = new ArrayList<>(); 
        String consultaQuery="SELECT * FROM partidas;";
        try(Connection con=ConexionDB.conectar();
            Statement stmn= con.createStatement();
            ResultSet rs=stmn.executeQuery(consultaQuery)){
            while (rs.next()) {
                Partida p=new Partida(rs.getString(1), rs.getString(2),rs.getString(3));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Hubo un error al obtener las partidas "+e.getMessage());
        }
        // Para cada fila: crear Jugador y añadir a lista
        return lista;
    }
    
    // Contar partidas ganadas por un jugador
    public static int contarGanadas(String alias) {
        String consultaQuery="SELECT COUNT(*) FROM partidas WHERE alias_ganador = ?;";
        try(Connection con=ConexionDB.conectar();
            PreparedStatement ps= con.prepareStatement(consultaQuery)){
                ps.setString(1, alias);
                try (ResultSet rs= ps.executeQuery()) {
                    int cantidad=rs.getInt(1);
                    return cantidad;
                } catch (Exception e) {
                    System.out.println("Error al consultar las partidas");
                }
            }catch (Exception e) {
            System.out.println("Error al obtener la info de la base de datos");
    }
        return 0;
    }
}
