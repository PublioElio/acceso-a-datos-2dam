package org.example;


import org.hibernate.Session;

import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {

        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        Session miSession = abrirSession();

    }
}