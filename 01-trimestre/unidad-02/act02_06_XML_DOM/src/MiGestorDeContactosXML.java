import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MiGestorDeContactosXML extends DefaultHandler {
    enum TiposTelefono {
        NINGUNO, CASA, TRABAJO, MOVIL
    }

    private String contenido; // contenido de la etiqueta
    private String datosDelContacto;
    private String telefono;
    private TiposTelefono tipoTelefonoGuardado;
    private boolean analizandoTelefonos;

    public void startElement(String uri, String nombreLocal, String qName, Attributes atributos)
            throws SAXException {
        if (qName.equals("telefonos")) {
            analizandoTelefonos = true;
            tipoTelefonoGuardado = TiposTelefono.NINGUNO;
            telefono = "";
        }
    }

    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
        contenido = new String(ch, inicio, longitud);
    }

    public void endElement(String uri, String nombreLocal, String qName) throws SAXException {
        if (!qName.isBlank()) {
            if (qName.equals("nombre"))
                datosDelContacto = contenido;
            else if (qName.equals("apellidos"))
                datosDelContacto += " " + contenido;
            else if (analizandoTelefonos && qName.equals("casa") && tipoTelefonoGuardado == TiposTelefono.NINGUNO) {
                telefono = contenido;
                tipoTelefonoGuardado = TiposTelefono.CASA;
            } else if (analizandoTelefonos && qName.equals("trabajo") && (tipoTelefonoGuardado == TiposTelefono.CASA
                    || tipoTelefonoGuardado == TiposTelefono.NINGUNO)) {
                telefono = contenido;
                tipoTelefonoGuardado = TiposTelefono.TRABAJO;
            } else if (analizandoTelefonos && qName.equals("movil")) {
                telefono = contenido;
                tipoTelefonoGuardado = TiposTelefono.MOVIL;
            } else if (qName.equals("telefonos")) {
                if (!telefono.isBlank()) {
                    datosDelContacto += " - Teléfono: " + telefono;
                    switch (tipoTelefonoGuardado) {
                        case CASA -> datosDelContacto += " (Casa)";
                        case TRABAJO -> datosDelContacto += " (Trabajo)";
                        case MOVIL -> datosDelContacto += " (Móvil)";
                    }
                }
                analizandoTelefonos = false;
            } else if (qName.equals("contacto")) {
                System.out.println(datosDelContacto);
            }
        }
    }
}