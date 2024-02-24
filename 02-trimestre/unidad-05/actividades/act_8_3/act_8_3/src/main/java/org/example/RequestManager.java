package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class RequestManager {
    private static final Logger LOGGER = Logger.getLogger(RequestManager.class.getName());
    private static final String NOMBRE = "nombre";
    private static final String ERROR_ES = "ERROR_ES E/S: ";
    
    public static void getRequest() {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/api-rest/departamentos");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (Scanner sc = new Scanner(conn.getInputStream())) {
                    String response = sc.useDelimiter("\\Z").next();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        LOGGER.info(jsonObject.get("depno") + " " + jsonObject.get(NOMBRE));
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

    public static void postRequest() throws JSONException {
        HttpURLConnection conn = null;
        String jsonInputString = new JSONObject()
                .put("empno", 1234)
                .put(NOMBRE, "Díez")
                .put("puesto", "Dependiente")
                .put("departamento", new JSONObject()
                        .put("depno", 20)
                        .put(NOMBRE, "Marketing")
                        .put("ubicacion", "Barcelona").toString()).toString();
        try {
            URL url = new URL("http://localhost:8080/api-rest/departamentos");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                LOGGER.info("Empleado insertado exitosamente.");
            } else {
                LOGGER.log(Level.SEVERE, "Error al insertar empleado. Código de respuesta: {0}", responseCode);
                try (Scanner sc = new Scanner(conn.getErrorStream())) {
                    String response = sc.useDelimiter("\\Z").next();
                    JSONObject jsonObject = new JSONObject(response).getJSONArray("errors").getJSONObject(0);
                    LOGGER.severe("Mensaje de error: " + jsonObject.getString("defaulMessage"));
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, ERROR_ES, e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

    }

    public static void deleteRequest(String codeToDelete) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/api-rest/empleados/" + codeToDelete);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NO_CONTENT)
                LOGGER.info("Empleado borrado exitosamente.");
            else
                LOGGER.log(Level.SEVERE, "Error al borrar empleado. Código de respuesta: {0}", responseCode);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "URL mal formada: {0}", e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error de E/S al intentar borrar empleado: {0}", e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }

}

