package org.example;

import java.sql.*;

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

        try (Connection con = DriverManager.getConnection(url, user, pwd)) {
            Statement statement = con.createStatement();
            String SQLsentence = "SELECT * FROM \"Asignaturas\" order by codigo;";
            ResultSet rs = statement.executeQuery(SQLsentence);
            System.out.println("Nombre" + "\t\t\t\t\t" + "Año" + "\t\t\t\t\t" + "Código");
            System.out.println("----------------------------------------------------");
            while(rs.next()){
                System.out.println(rs.getString(1) + "\t" + rs.getString(2)
                        + "\t" + rs.getString(3));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }
}