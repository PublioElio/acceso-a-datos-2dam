import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {
    public static void main(String[] args) {
        try {
            // Un parser es un conversor que coge un archivo y te dice si cumple unas normas
            SAXParser saxParser =
                    SAXParserFactory.newNSInstance().newSAXParser();
            saxParser.parse("contactos.xml", new MiGestorDeContactosXML()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}