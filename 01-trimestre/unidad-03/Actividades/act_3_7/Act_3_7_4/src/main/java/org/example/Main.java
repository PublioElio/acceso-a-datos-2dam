package org.example;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/empleados";
        String user = "postgres";
        String pass = "A123456a";
        String textoMenu = """
                Elija una opción.
                1. Listar empleados por puesto
                2. Listar empleados por departamento
                3. Listar empleados por nombre
                4. Finalizar programa
                ->\s""";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR. " + e.getMessage());
        }


        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            int opcion = EntradaTeclado.pedirEntero(textoMenu, 1, 4);
            CallableStatement statement = null;
            while (opcion < 4) {
                switch (opcion) {
                    case 1 -> statement = buscarPorPuesto(con);
                    case 2 -> statement = buscarPorDepartamento(con);
                    case 3 -> statement = buscarPorNombre(con);
                }
                opcion = EntradaTeclado.pedirEntero(textoMenu, 1, 4);
            }
            System.out.println("Fin del programa");
            if(statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("ERROR. " + e.getMessage());
        }

    }

    private static CallableStatement buscarPorNombre(Connection con) throws SQLException {
        CallableStatement statement;
        String nombre = EntradaTeclado.pedirCadena("Buscar empleados por nombre: ");
        statement = con.prepareCall("{call lista_emp_nombre('" + nombre + "')}");
        ResultSet rs = statement.executeQuery();
        System.out.println("Código nombre" + "\t\tPuesto");
        System.out.println("---------------------------------");
        while (rs.next()) {
            System.out.printf("%6d %-12s %s\n", rs.getInt(1), rs.getString(2), rs.getString(3));

        }
        System.out.println();
        return statement;
    }

    private static CallableStatement buscarPorDepartamento(Connection con) throws SQLException {
        CallableStatement statement;
        String departamento = EntradaTeclado.pedirCadena("Introduzca el departamento: ");
        statement = con.prepareCall("{call lista_emp_dept('" + departamento + "')}");
        ResultSet rs = statement.executeQuery();
        System.out.println("Código nombre" + "\t\tPuesto");
        System.out.println("---------------------------------");
        while (rs.next()) {
            System.out.printf("%6d %-12s %-10s\n", rs.getInt(1), rs.getString(2), rs.getString(3));

        }
        System.out.println();
        return statement;
    }

    private static CallableStatement buscarPorPuesto(Connection con) throws SQLException {
        CallableStatement statement;
        String puesto = EntradaTeclado.pedirCadena("Introduzca el puesto: ");
        statement = con.prepareCall("{call lista_emp_puesto('" + puesto + "')}");
        ResultSet rs = statement.executeQuery();
        System.out.println("Código nombre" + "\t\tDepartamento");
        System.out.println("---------------------------------");
        while (rs.next()) {
            System.out.printf("%6d %-15s %2d\n", rs.getInt(1), rs.getString(2), rs.getInt(4));

        }
        System.out.println();
        return statement;
    }
}