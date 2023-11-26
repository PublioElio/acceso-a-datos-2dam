package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/empleados";
        String user = "postgres";
        String pass = "A123456a";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR. " + e.getMessage());
        }

        try (Connection con = DriverManager.getConnection(url, user, pass)) {

            String departamento = EntradaTeclado.pedirCadena("Introduzca el departamento: ");
            CallableStatement statement =
                    con.prepareCall("{call lista_emp_dept('" + departamento + "')}");
            ResultSet rs = statement.executeQuery();
            System.out.println("Código nombre" + "\t\tPuesto");
            System.out.println("---------------------------------");
            while(rs.next()){
                System.out.printf("%6d %-12s %-10s\n", rs.getInt(1), rs.getString(2), rs.getString(3));

            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("ERROR. " + e.getMessage());
        }
    }
}