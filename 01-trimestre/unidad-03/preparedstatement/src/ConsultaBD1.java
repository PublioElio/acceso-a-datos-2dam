import java.sql.*;

public class ConsultaBD1 {
    public static void main(String[] args) {
        Connection conn;
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/institutoFP";
            String user = "postgres";
            String pass = "123";
            conn = DriverManager.getConnection(url,user,pass);
            PreparedStatement consulta = conn.prepareStatement("INSERT INTO \"Asignaturas\"(nombre,anyo) VALUES (?,?)");
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