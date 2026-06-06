import java.sql.*;

public class ConexionDB {
    
    // Conexión a H2 database(embebida, sin servidor)

    static final String URL = "jdbc:h2:./torneos_db";  // se crea en carpeta local
    static final String USUARIO = "root";
    static final String CLAVE = "";
    
    // Método para obtener conexión
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
    
    // Método para crear las tablas al inicio
    public static void crearTablas() {
        try (Connection con = conectar()) {
            
            String sqlJugadores = "CREATE TABLE IF NOT EXISTS jugadores (" +
                "nombre VARCHAR(50) PRIMARY KEY, " +
                "alias VARCHAR(100), " +
                "puntuacion INT DEFAULT 0)";
            
            String sqlTorneos = "CREATE TABLE IF NOT EXISTS torneos (" +
                "nombre VARCHAR(100) PRIMARY KEY)";
            
            String sqlParticipaciones = "CREATE TABLE IF NOT EXISTS participaciones (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre_torneo VARCHAR(100), " +
                "alias_jugador VARCHAR(50), " +
                "FOREIGN KEY (nombre_torneo) REFERENCES torneos(nombre), " +
                "FOREIGN KEY (alias_jugador) REFERENCES jugadores(alias))";
            
            String sqlPartidas = "CREATE TABLE IF NOT EXISTS partidas (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "alias_j1 VARCHAR(50), " +
                "alias_j2 VARCHAR(50), " +
                "alias_ganador VARCHAR(50), " +
                "FOREIGN KEY (alias_j1) REFERENCES jugadores(alias), " +
                "FOREIGN KEY (alias_j2) REFERENCES jugadores(alias), " +
                "FOREIGN KEY (alias_ganador) REFERENCES jugadores(alias));";
            
            Statement st = con.createStatement();
            st.execute(sqlJugadores);
            st.execute(sqlTorneos);
            st.execute(sqlParticipaciones);
            st.execute(sqlPartidas);
            st.close();
            
            System.out.println("Tablas creadas correctamente.");
            
        } catch (SQLException e) {
            System.out.println("Error al crear tablas: " + e.getMessage());
        }
    }
}