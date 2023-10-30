import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebaConexionBD {
    public static void main(String[] args)
            throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
            String user = "postgres";
            String password = "A123456a";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida.");
        } catch (ClassNotFoundException ex) {
            System.out.println("No se pudo cargar controlador.");
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la BD.");
        } finally {
            if (conn != null) conn.close();
        }
    }
}