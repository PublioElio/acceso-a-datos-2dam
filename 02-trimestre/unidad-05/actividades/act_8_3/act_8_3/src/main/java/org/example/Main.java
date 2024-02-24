package org.example;

import org.json.JSONException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        RequestManager.getRequest();
        try {
            RequestManager.postRequest();
        } catch (JSONException e) {
            LOGGER.log(Level.SEVERE, "Error con el JSON");
        }
        RequestManager.deleteRequest("1234");
    }
}
