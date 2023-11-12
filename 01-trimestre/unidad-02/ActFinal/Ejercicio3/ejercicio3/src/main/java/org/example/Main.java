package org.example;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("videojuegos.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(VideojuegosList.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            VideojuegosList videojuegosList = (VideojuegosList) jaxbUnmarshaller.unmarshal(file);

            for (Item item : videojuegosList.getItems()) {
                if ("Activa".equalsIgnoreCase(item.getEstado())) {
                    System.out.println("Título: " + item.getTitulo());
                    System.out.println("Semilla: " + item.getSemilla());
                    System.out.println("Palabras Clave: " + item.getPalabrasClave());
                    System.out.println("------------------------");
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
