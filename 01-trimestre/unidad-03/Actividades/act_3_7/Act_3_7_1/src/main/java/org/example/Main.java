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

            String puesto = EntradaTeclado.pedirCadena("Introduzca el puesto: ");
            CallableStatement statement =
                    con.prepareCall("{call lista_emp_puesto('" + puesto + "')}");
            ResultSet rs = statement.executeQuery();
            System.out.println("Código nombre" + "\t\tDepartamento");
            System.out.println("---------------------------------");
            while(rs.next()){
                System.out.printf("%6d %-15s %2d\n", rs.getInt(1), rs.getString(2), rs.getInt(4));

            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("ERROR. " + e.getMessage());
        }

    }
}