package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
        String user = "postgres";
        String pass = "A123456a";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR. " + e.getMessage());
        }

        try (Connection con = DriverManager.getConnection(url, user, pass)) {

            PreparedStatement ps = con.prepareStatement("ALTER TABLE \"Asignaturas\" ADD COLUMN curso INTEGER, " +
                    "ADD CONSTRAINT fk_curso FOREIGN KEY (curso) REFERENCES \"Cursos\" (codigo);");

            if (ps.executeUpdate() == 0) {
                System.out.println("Clave foránea creada con éxito");
            } else {
                System.out.println("Error al crear la clave foránea tabla");
            }

            ps.close();
        } catch (SQLException e) {
            System.out.println("ERROR. " + e.getMessage());
        }

    }
}