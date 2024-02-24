package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class RequestManager {
    private static final Logger LOGGER = Logger.getLogger(RequestManager.class.getName());

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
                        LOGGER.info(jsonObject.get("depno") + " " + jsonObject.get("nombre"));
                    }
                }
            } else {
                LOGGER.log(Level.WARNING, "Fallo en la conexión. Código de respuesta: {0}", conn.getResponseCode());
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error de E/S: ", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error inesperado: ", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static void postRequest() throws JSONException {
        HttpURLConnection conn;
        String jsonInputString = new JSONObject()
                .put("empno", 1234)
                .put("nombre", "Díez")
                .put("puesto", "Dependiente")
                .put("departamento", new JSONObject()
                        .put("depno", 20)
                        .put("nombre", "Marketing")
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
            if (conn.getResponseCode() == 200)
                LOGGER.info("Empleado insertado");
            else {
                LOGGER.log(Level.SEVERE, "Error de conexión");
                Scanner sc = new Scanner(conn.getErrorStream());
                String response = sc.useDelimiter("\\Z").next();
                sc.close();

                JSONObject jsonObject = new JSONObject(response).getJSONArray("errors").getJSONObject(0);
                System.out.println(jsonObject.get("defaulMessage"));
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error inesperado: ", e);
        }
    }
}

