import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {
    public static void main(String[] args) {
        try {
            SAXParser saxParser =
                    SAXParserFactory.newNSInstance().newSAXParser();
            saxParser.parse("contactos.xml", new MiGestorDeContactosXML()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}