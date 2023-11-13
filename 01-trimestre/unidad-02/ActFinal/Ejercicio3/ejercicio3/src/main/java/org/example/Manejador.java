package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {
    private String contenido;
    private String datosVideojuego;
    private boolean activo;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenido = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Título")) {
            datosVideojuego = "\nTítulo: " + contenido;
        } else if (qName.equals("Semilla")) {
            datosVideojuego += "\nSemilla: " + contenido;
        } else if (qName.equals("Palabras_clave")) {
            datosVideojuego += "\nPalabras clave: " + contenido;
        } else if (qName.equals("Estado")) {
            activo = false;
            if (contenido.equals("Activa")) {
                datosVideojuego += "\nEstado" + contenido;
                activo = true;
            }
        } else if (qName.equals("item")) {
            if (activo) {
                System.out.println(datosVideojuego);
            }
        }
    }
}
