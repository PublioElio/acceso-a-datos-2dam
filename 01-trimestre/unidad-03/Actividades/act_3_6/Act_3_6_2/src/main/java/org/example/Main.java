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
            PreparedStatement ps = con.prepareStatement("CREATE TABLE \"Cursos\" (nombre VARCHAR(90)," +
                    "codigo SERIAL)");
            if (ps.executeUpdate() == 0) {
                System.out.println("Nueva tabla creada con éxito");
            } else {
                System.out.println("Error al crear la nueva tabla");
            }

            ps = con.prepareStatement("INSERT INTO \"Cursos\" (nombre) VALUES(?);");
            String nombreCurso;
            boolean salir;

            do {
                nombreCurso = EntradaTeclado.pedirCadena("Introduzca un nombre para el nuevo curso;\n" +
                        "o escriba 'salir' para finalizar: ");
                salir = "salir".equalsIgnoreCase(nombreCurso);
                if (!salir) {
                    ps.setString(1, nombreCurso.toUpperCase());
                    if (ps.executeUpdate() > 0)
                        System.out.println("Curso: " + nombreCurso + " introducido correctamente");
                }
            } while (!salir);
            ps.close();
        } catch (SQLException e) {
            System.out.println("ERROR. " + e.getMessage());
        }
    }
}