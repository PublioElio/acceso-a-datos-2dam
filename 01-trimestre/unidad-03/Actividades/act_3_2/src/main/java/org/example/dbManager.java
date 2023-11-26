package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbManager {
    private String url;
    private String user;
    private String pass;
    private String driver;
    private Connection con;

    public dbManager(String url, String user, String pass, String driver) {
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
    
    public void setUrl(String url) {
        this.url = url;
    }
}
