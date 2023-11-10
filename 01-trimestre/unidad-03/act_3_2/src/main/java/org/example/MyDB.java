package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {

    private String url;
    private String user;
    private String pwd;
    private String drivers;
    private Connection conn = null;

    public MyDB(String url, String user, String pwd, String drivers){
        this.url = url;
        this.user = user;
        this.pwd = pwd;
        this.drivers = drivers;
    }

    public void createTable(){

    }
    CREATE TABLE departamento (
            id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
            nombre VARCHAR(100) NOT NULL,
    presupuesto DOUBLE UNSIGNED NOT NULL,
    gastos DOUBLE UNSIGNED NOT NULL
);


    public boolean connect(){
        boolean connected = false;
        try {
            Class.forName(drivers);
            conn = DriverManager.getConnection(url, user, pwd);
            connected = true;
        } catch (ClassNotFoundException ex) {
            System.out.println("No se pudo cargar controlador.");
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la BD.");
        }
        return connected;
    }

    public boolean closeConnection(){
        boolean closed = false;
        try {
            conn.close();
            closed = true;
        } catch (SQLException ex) {
            System.out.println("ERROR. No se ha cerrado la base de datos: " + ex.getErrorCode());
        }
        return closed;
    }
}
