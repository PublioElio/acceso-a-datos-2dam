package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private String url;
    private String user;
    private String pass;
    private String driver;
    private Connection con;

    public DBManager(String url, String user, String pass, String driver) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.driver = driver;
    }

//    UPDATE empleados
//    SET salario = salario * 1.1
//    WHERE nombre = 'Juan' AND apellido = 'Pérez';

//    INSERT INTO empleados (nombre, apellido, salario) VALUES ('Juan', 'Pérez', 3000.00), ('María', 'Gómez', 3500.00), ('Carlos', 'López', 3200.00);

//    CREATE TABLE empleados (
//    id SERIAL PRIMARY KEY,
//    nombre VARCHAR(100),
//    apellido VARCHAR(100),
//    salario NUMERIC(10, 2)
//    );

    /**
     * Este método se conecta con la base de datos
     *
     * @return true si logra conectarse
     */
    public boolean connect() {
        boolean connected = false;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
            connected = true;
        } catch (ClassNotFoundException ex) {
            System.out.println("No se pudo cargar controlador.");
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
        return connected;
    }

    /**
     * Este método cierra la conexión con la base de datos
     *
     * @return true si se cierra la conexión
     */
    public boolean closeConnection() {
        boolean closed = false;
        try {
            con.close();
            closed = true;
        } catch (SQLException ex) {
            System.out.println("ERROR. No se ha cerrado la base de datos: " + ex.getMessage());
        }
        return closed;
    }

    /**
     * Este método crea una base de datos nueva con el nombre especificado
     *
     * @param databaseName el nombre de la nueva base de datos
     * @return true si la crea correctamente
     */
    public boolean createDatabase(String databaseName) {
        boolean created = false;
        String createDBQuery = "CREATE DATABASE " + databaseName + " WITH ENCODING 'UTF8';";
        Statement statement;
        try {
            statement = con.createStatement();
            statement.executeUpdate(createDBQuery);
            created = true;
        } catch (SQLException e) {
            System.out.println("ERROR. No se ha creado la base de datos: " + e.getMessage());
        }
        return created;
    }

    /**
     * Este método borra una base de datos, siempre que esta exista
     *
     * @param databaseName el nombre de la base de datos
     */
    public void deleteDatabase(String databaseName) {
        String createDBQuery = "DROP DATABASE IF EXISTS " + databaseName;
        Statement statement;
        try {
            statement = con.createStatement();
            statement.executeUpdate(createDBQuery);
        } catch (SQLException e) {
            System.out.println("ERROR. No se ha borrado la base de datos: " + e.getMessage());
        }
    }

    /**
     * Este método crea una tabla nueva en la base de datos, hay que enviar la sentencia SQL para
     * postgres completa
     *
     * @param newTable la sentencia completa de la tabla que se va a crear
     * @return true si se ha creado correctamente
     */
    public boolean createTable(String newTable) {
        boolean created = false;
        Statement statement;
        try {
            statement = con.createStatement();
            statement.executeUpdate(newTable);
            created = true;
        } catch (SQLException e) {
            System.out.println("ERROR. No se ha creado la tabla: " + e.getMessage());
        }
        return created;
    }

    /**
     * Esta función borra una tabla existente en la base de datos, con todas sus dependencias
     *
     * @param tableName la tabla que hay que borrar
     * @return true si se ha borrado adecuadamente
     */
    public boolean deleteTable(String tableName) {
        boolean deleted = false;
        String dropTableQuery = "DROP TABLE " + tableName + " CASCADE;";
        Statement statement;
        try {
            statement = con.createStatement();
            statement.executeUpdate(dropTableQuery);
            deleted = true;
        } catch (SQLException e) {
            System.out.println("ERROR. No se ha creado la tabla: " + e.getMessage());
        }
        return deleted;
    }

    /**
     * Este es un método para insertar departamentos con un Prepared Statement. Recibe un array bidimensional de Objetos,
     * en cada posición del array, hay una fila con los datos a insertar. A partir de este método se puede crear
     * cualquier método para insertar elementos en una tabla, sabiendo el nombre, número de elementos y tipos de cada
     * columna (ver ejemplo de insertar empleados)
     *
     * @param newDept los nuevos departamentos que vamos a insertar
     * @return la cantidad de filas insertadas (integer)
     */
    public int insertDept(Object[][] newDept) {
        String insertQuery = "INSERT INTO departamentos (nombre, presupuesto, gastos) VALUES (?, ?, ?)";
        int totalNewDept = 0;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(insertQuery);
            for (Object[] row : newDept) {
                preparedStatement.setString(1, row[0].toString());
                preparedStatement.setDouble(2, (Double) row[1]);
                preparedStatement.setDouble(3, (Double) row[2]);
                preparedStatement.executeUpdate();
                totalNewDept++;
            }
        } catch (SQLException e) {
            System.out.println("Error insertando departamentos: " + e.getMessage());
        }
        return totalNewDept;
    }

    /**
     * El siguiente método introduce nuevos empleados en la tabla empleados, con sus campos correspondientes, hay que
     * tener en cuenta que algunos de los campos de esta tabla pueden ser nulos, el método los maneja con sentencias
     * if/else
     *
     * @param newEmp un array bidimensional de objetos que contiene los datos a insertar
     * @return un entero con el total de empleados nuevos insertados
     */
    public int insertEmp(Object[][] newEmp) {
        String insertQuery = "INSERT INTO empleados (nif, nombre, apellido1, apellido2, id_departamento) " +
                "VALUES (?, ?, ?, ?, ?)";
        int totalNewEmp = 0;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(insertQuery);
            for (Object[] row : newEmp) {
                preparedStatement.setString(1, row[0].toString());
                preparedStatement.setString(2, row[1].toString());
                preparedStatement.setString(3, row[2].toString());
                if (row[3] != null) {
                    preparedStatement.setString(4, row[3].toString());
                } else {
                    preparedStatement.setNull(4, java.sql.Types.VARCHAR); // este campo puede ser null
                }
                if (row[4] != null) {
                    preparedStatement.setInt(5, (Integer) row[4]);
                } else {
                    preparedStatement.setNull(4, Types.INTEGER); // este campo puede ser null
                }
                preparedStatement.executeUpdate();
                totalNewEmp++;
            }
        } catch (SQLException e) {
            System.out.println("Error insertando empleados: " + e.getMessage());
        }
        return totalNewEmp;
    }

    /**
     * Este método hace una consulta de una sola columna de una tabla de la base de datos
     *
     * @param col   columna que vamos a listar
     * @param table tabla que vamos a consultar
     * @return una lista de String con el resultado de la consulta
     */
    public List<String> singleColumQuery(String col, String table) {
        List<String> list = new ArrayList<>();
        String query = "SELECT " + col + " FROM " + table + ";";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String result = resultSet.getString(col);
                list.add(result);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar la columna " + col
                    + " de la tabla " + table + ": " + e.getMessage());
        }
        return list;
    }

    /**
     * Este método te devuelve una relación de resultados distintos de una columna
     *
     * @param col   la columna de la que quieres obtener resultados distintos
     * @param table la tabla donde vas a hacer la consulta
     * @return un listado de String
     */
    public List<String> singleColumDistinct(String col, String table) {
        List<String> list = new ArrayList<>();
        String query = "SELECT DISTINCT " + col + " FROM " + table
                + " WHERE " + col + " IS NOT NULL;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String result = resultSet.getString(col);
                list.add(result);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return list;
    }

    /**
     * Este método muestra por pantalla los dos departamentos con menor gasto
     */
    public void deptMenorGasto() {
        ResultSet rs;
        try {
            Statement query = con.createStatement();
            rs = query.executeQuery("SELECT nombre, gastos FROM departamentos ORDER BY gastos LIMIT 2;");
            System.out.println("Los dos departamentos con menor gasto son:");
            while (rs.next()) {
                System.out.println(rs.getString("nombre") + ", gasto: " + rs.getDouble("gastos"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }

    }

    /**
     * Este método muestra aquellos departamentos con un presupuesto mayor de 150000
     */
    public void deptPrepMayor() {
        ResultSet rs;
        try {
            Statement query = con.createStatement();
            rs = query.executeQuery("SELECT nombre, presupuesto FROM departamentos WHERE presupuesto >= 150000;");
            System.out.println("Los departamentos con un presupuesto mayor a 150.000 son:");
            while (rs.next()) {
                System.out.println(rs.getString("nombre") + ", presupuesto: "
                        + rs.getDouble("presupuesto"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    /**
     * Este método devuelve los empleados con los datos de sus departamentos
     */
    public void empDept() {
        ResultSet rs;
        try {
            Statement query = con.createStatement();
            rs = query.executeQuery("SELECT empleados.nombre AS empNombre, empleados.apellido1 AS empApellido1," +
                    " empleados.apellido2 AS empApellido2, departamentos.nombre AS deptNombre " +
                    "FROM empleados JOIN departamentos ON empleados.id_departamento = departamentos.id;");
            System.out.println("Los empleados con los datos de sus departamentos son:");
            while (rs.next()) {
                System.out.println(rs.getString("empNombre") + " "
                        + rs.getString("empApellido1")
                        + " " + rs.getString("empApellido2")
                        + ", departamento: " + rs.getString("deptNombre"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    /**
     * Este método lista los empleados y sus departamentos, ordenado alfabéticamente por el nombre del departamento y
     * por los apellidos y el nombre de los empleados.
     */
    public void listarEmpDep() {
        ResultSet rs;
        try {
            Statement query = con.createStatement();

            rs = query.executeQuery("SELECT empleados.nombre AS empNombre, empleados.apellido1 AS empApellido1, " +
                    "empleados.apellido2 AS empApellido2, departamentos.nombre AS deptNombre FROM empleados " +
                    "JOIN departamentos ON empleados.id_departamento = departamentos.id " +
                    "ORDER BY departamentos.nombre, empleados.apellido1, empleados.apellido2;");
            System.out.println("Listado de empleados y los departamentos donde trabajan, " +
                    "ordenados:");
            while (rs.next()) {
                System.out.println(rs.getString("empNombre") + " " + rs.getString("empApellido1")
                        + " " + rs.getString("empApellido2") + ", departamento: "
                        + rs.getString("deptNombre"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    /**
     * Este método devuelve la ID y el nombre de los departamentos que tienen empleados
     */
    public void depIdConEmpleados() {
        ResultSet rs;
        try {
            Statement query = con.createStatement();

            rs = query.executeQuery("SELECT id, nombre FROM departamentos " +
                    "WHERE id NOT IN (SELECT id_departamento FROM empleados);");
            System.out.println("Los departamentos que tienen empleados son los siguientes:");
            while (rs.next()) {
                System.out.println(rs.getString("nombre") + " " + rs.getInt("id"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    /**
     * Este método obtiene los departamentos donde trabaja un empleado, buscando por su NIF
     */
    public void buscarDeptPorNifEmp(){
        ResultSet rs;
        try {
            Statement query = con.createStatement();
            rs = query.executeQuery("SELECT nombre FROM departamentos WHERE id = " +
                    "(SELECT id_departamento FROM empleados WHERE nif = '38382980M');");
            System.out.println("Departamento/s donde trabaja el empleado con el NIF 38382980M: " );
            while(rs.next()){
                System.out.println(rs.getString("nombre"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    /**
     * Este método devuelve la suma total del presupuesto de todos los departamentos
     */
    public void sumarPresupuesto(){
        ResultSet rs;
        try {
            Statement query = con.createStatement();
            rs = query.executeQuery("SELECT SUM(presupuesto) AS sumaPresupuesto FROM departamentos;");
            System.out.println("El presupuesto total de todos los departamentos es:");
            while(rs.next()){
                System.out.println(rs.getDouble("sumaPresupuesto"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
