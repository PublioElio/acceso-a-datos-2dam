package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
        String user = "postgres";
        String pwd = "A123456a";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR. " + e.getMessage());
        }
        try (Connection con = DriverManager.getConnection(url, user, pwd)){
            Statement statement = con.createStatement();
            String SQLsentence = "ALTER TABLE \"Asignaturas\" ADD COLUMN horas INT;";
            statement.executeUpdate(SQLsentence);
            statement.close();
        } catch (SQLException e) {
            System.out.println("ERROR. " + e.getMessage());
        }
    }
}