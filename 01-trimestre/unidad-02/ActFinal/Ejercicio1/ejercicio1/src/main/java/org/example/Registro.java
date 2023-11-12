package org.example;

import java.io.*;

public class Registro {

    private String ruta;

    public Registro(String ruta) {
        this.ruta = ruta;
    }

    public boolean escribir(String linea) {
        boolean escrito;
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write(linea);
            writer.newLine();
            escrito = true;
        } catch (IOException e) {
            System.out.println("Error de lectura/escritura: " + e.getMessage());
            escrito = false;
        }
        return escrito;
    }
}
