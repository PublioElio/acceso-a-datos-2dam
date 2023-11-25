package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
            String user = "postgres";
            String pwd = "A123456a";
            Connection con = DriverManager.getConnection(url, user, pwd);
            Statement statement = con.createStatement();
            String SQLsentence = "ALTER TABLE \"Asignaturas\" ADD COLUMN horas INT;";
            statement.executeUpdate(SQLsentence);
            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}