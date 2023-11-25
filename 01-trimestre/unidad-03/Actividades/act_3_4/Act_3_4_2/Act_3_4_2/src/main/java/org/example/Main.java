package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
            String user = "postgres";
            String pass = "A123456a";
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement statement = con.createStatement();
            String SQLsentence = "INSERT INTO \"Asignaturas\" (nombre, anyo) VALUES('LENGUAJES DE MARCAS', 1)";
            System.out.println("Número de filas insertadas: " + statement.executeUpdate(SQLsentence));
            SQLsentence = "SELECT nombre, anyo, codigo FROM \"Asignaturas\";";
            ResultSet rs = statement.executeQuery(SQLsentence);
            System.out.println("Nombre" + "\t\t\t\t\t" + "Año" + "\t\t\t\t\t" + "Código");
            System.out.println("----------------------------------------------------");
            while(rs.next()){
                System.out.println(rs.getString(1) + "\t" + rs.getString(2)
                        + "\t" + rs.getString(3));
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
