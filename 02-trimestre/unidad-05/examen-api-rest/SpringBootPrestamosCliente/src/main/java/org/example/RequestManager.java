package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestManager {

    private static final Logger LOGGER = Logger.getLogger(RequestManager.class.getName());
    private static final String NOMBRE = "nombreAutor";
    private static final String ERROR_ES = "ERROR_ES E/S: ";

    public static void getRequest() {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/api-rest/autores");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (Scanner sc = new Scanner(conn.getInputStream())) {
                    String response = sc.useDelimiter("\\Z").next();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        LOGGER.info(jsonObject.get("idAutor") + " " +
                                jsonObject.get(NOMBRE) + " " +
                                jsonObject.get("pais"));
                    }
                }
            } else {
                LOGGER.log(Level.WARNING, "Fallo en la conexión. Código de respuesta: {0}", conn.getResponseCode());
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, ERROR_ES, e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error inesperado: ", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static void getRequest(int idAutor) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/api-rest/autores");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (Scanner sc = new Scanner(conn.getInputStream())) {
                    String response = sc.useDelimiter("\\Z").next();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if(jsonObject.get("idAutor").equals(idAutor)){
                            LOGGER.info(jsonObject.get("idAutor") + " " +
                                    jsonObject.get(NOMBRE) + " " +
                                    jsonObject.get("pais"));
                        }
                    }
                }
            } else {
                LOGGER.log(Level.WARNING, "Fallo en la conexión. Código de respuesta: {0}", conn.getResponseCode());
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, ERROR_ES, e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error inesperado: ", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

}
