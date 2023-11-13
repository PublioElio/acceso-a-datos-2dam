package org.example;


import java.io.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;


public class Registro implements Serializable {

    private String rutaDatos;
    private String rutaXml;

    public Registro(String rutaDatos, String rutaXml) {
        this.rutaDatos = rutaDatos;
        this.rutaXml = rutaXml;
    }

    public Equipo cargarXml() {
        Equipo equipo;
        try {

            JAXBContext context = JAXBContext.newInstance(Equipo.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            equipo = (Equipo) unmarshaller.unmarshal(new File(rutaXml));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return equipo;
    }

    public boolean guardarXml(Equipo equipo){
        boolean guardado = false;
        try {

            JAXBContext context = JAXBContext.newInstance(Equipo.class);
            Marshaller marshaller = context.createMarshaller();
            try {
                marshaller.marshal(equipo, new FileWriter(rutaXml));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return guardado;
    }

    public boolean guardarArchivo(Equipo equipo) {
        boolean guardado = false;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rutaDatos))) {
            out.writeObject(equipo);
            guardado = true;
        } catch (IOException exception) {
            System.out.println("Problema al guardar archivo: " + exception.getMessage());
        }
        return guardado;
    }

    public Equipo cargarArchivo() {
        Equipo equipo = new Equipo();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(rutaDatos))) {
            equipo = (Equipo) in.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Problema al cargar archivo: " + exception.getMessage());
        }
        return equipo;
    }
}
