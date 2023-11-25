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

            PreparedStatement ps = con.prepareStatement("INSERT INTO \"Asignaturas\" (nombre, anyo, horas)" +
                    " VALUES(?, ?, ?)");
            String nombre;
            int anyo, horas;
            boolean salir;

            do {
                nombre = EntradaTeclado.pedirCadena("Introduzca un nombre para la asignatura;\n" +
                        "o escriba 'salir' para finalizar de introducir asignaturas: ");
                salir = "salir".equalsIgnoreCase(nombre);
                if(!salir){
                    anyo = EntradaTeclado.pedirEntero("¿Es una asignatura de primero o de segundo? ", 1, 2);
                    horas = EntradaTeclado.pedirEntero("Introduzca un número de horas para el módulo:  ",
                            400, 1000);
                    ps.setString(1, nombre.toUpperCase());
                    ps.setInt(2, anyo);
                    ps.setInt(3, horas);
                    if(ps.executeUpdate() > 0)
                        System.out.println("Asignatura " + nombre + " introducida correctamente");
                }
            } while (!salir);
            ps.close();
        } catch (SQLException e) {
            System.out.println("ERROR. " + e.getMessage());
        }
    }
}