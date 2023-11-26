package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
            System.out.println("ERROR. No se ha borrado la tabla " + tableName
                    + ": " + e.getMessage());
        }
        return deleted;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
