package org.acdat.jdbc;

import org.acdat.negocio.Cliente;
import org.acdat.negocio.Vuelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VueloDao {
    public List<Vuelo> mostrarVuelos(Connection connection) throws SQLException, SQLException {
        List<Vuelo> vueloList = new ArrayList<Vuelo>();
        Vuelo vuelo = null;
        String sql = "SELECT * FROM vuelos";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                vuelo = new Vuelo();
                vuelo.setId(resultSet.getInt("vuelo_id"));
                vuelo.setOrigen(resultSet.getString("origen"));
                vuelo.setDestino(resultSet.getString("destino"));
                vuelo.setFecha_salida(resultSet.getString("fecha_salida"));
                vuelo.setFecha_llegada(resultSet.getString("fecha_llegada"));
                vuelo.setCosto(resultSet.getInt("costo"));
                vueloList.add(vuelo);
            }
            return vueloList;
        }
    }

    public boolean agregarVuelo(Connection connection,  Vuelo Vuelo) throws SQLException {
        boolean respuesta = false;
        int res = 0;
        String sql = "INSERT INTO vuelos (origen, destino, fecha_salida,  fecha_llegada, costo) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, Vuelo.getOrigen());
            preparedStatement.setString(2, Vuelo.getDestino());
            preparedStatement.setString(3, Vuelo.getFecha_salida());
            preparedStatement.setString(4, Vuelo.getFecha_llegada());
            preparedStatement.setDouble(5, Vuelo.getCosto());
            res = preparedStatement.executeUpdate();
            if (res > 0) respuesta = true;
        }
        return respuesta;
    }

    public boolean actualizarVuelo(Connection connection,  Vuelo Vuelo) throws SQLException {
        boolean respuesta = false;
        int res = 0;

        String sql = "UPDATE vuelos SET origen=?, destino=?, fecha_salida=?, fecha_llegada=?, costo=? WHERE cliente_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, Vuelo.getOrigen());
            preparedStatement.setString(2, Vuelo.getDestino());
            preparedStatement.setString(3, Vuelo.getFecha_salida());
            preparedStatement.setString(4, Vuelo.getFecha_llegada());
            preparedStatement.setDouble(5, Vuelo.getCosto());
            res = preparedStatement.executeUpdate();
            if (res == 1) respuesta = true;
        }
        return respuesta;
    }

    public Vuelo cargarVuelo (Connection connection, int id) throws SQLException {

        Vuelo Vuelo = null;
        String sql = "SELECT * FROM vuelos WHERE id = " + id;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Vuelo = new Vuelo();
                Vuelo.setId(resultSet.getInt("vuelo_id"));
                Vuelo.setOrigen(resultSet.getString("origen"));
                Vuelo.setDestino(resultSet.getString("destino"));
                Vuelo.setFecha_salida(resultSet.getString("fecha_salida"));
                Vuelo.setFecha_llegada(resultSet.getString("fecha_llegada"));
                Vuelo.setCosto(resultSet.getInt("costo"));
            }
            return Vuelo;
        }
    }

    public boolean existeVuelo (Connection connection, int id) throws SQLException {
        boolean respuesta = true;
        String sql = "SELECT * FROM vuelos WHERE id = " + id;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()  ) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean eliminarVuelo(Connection connection, int VueloId) throws SQLException {
        boolean respuesta = false;

        return respuesta;
    }
}
