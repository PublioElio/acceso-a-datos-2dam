import java.sql.*;

public class ConsultaBD1 {
    public static void main(String[] args) {
        Connection conn;
        try{
            Class.forName("org.postgresql.Driver"); // esto es para decirle el tipo de bases de datos que hay que usar
            String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
            String user = "postgres";
            String pass = "A123456a";
            conn = DriverManager.getConnection(url,user,pass);
            PreparedStatement consulta = conn.prepareStatement("INSERT INTO \"Asignaturas\" (nombre,anyo) VALUES (?,?)");
            consulta.setString(1,"Lenguaje de marcas");
            consulta.setInt(2,1);
            consulta.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar el controlador.");
        }catch (SQLException e){
            System.out.println("Error al conectar la BD.");
        }
    }
}