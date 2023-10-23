import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Main {

    private static void menu(Equipo equipo) {
        String textoMenu = "Elija una opción:\n1. Añadir jugador\n2. Listar jugadores\n3. Buscar jugadores por nombre\n" +
                "4. Buscar jugadores por apodo\n5. Buscar jugadores por dorsal\n6. Guardar y salir\n",
                noEncontrado = "Jugador no encontrado.", encontrado;
        int opcion;
        do {
            opcion = EntradaTeclado.pedirEntero(textoMenu, 1, 6);
            switch (opcion) {
                case 1:
                    System.out.println("------------ AÑADIR JUGADOR ------------");
                    Jugador nuevo = pedirDatos();
                    equipo.nuevoJugador(nuevo);
                    break;
                case 2:
                    System.out.println(equipo);
                    break;
                case 3:
                    System.out.println("------------ BUSCAR POR NOMBRE ------------");
                    String nombre = EntradaTeclado.pedirCadena("Introduzca el nombre: ");
                    encontrado = equipo.buscarNombre(nombre);
                    if (encontrado == null) {
                        System.out.println(noEncontrado);
                    } else {
                        System.out.println(encontrado);
                    }
                    break;
                case 4:
                    System.out.println("------------ BUSCAR POR APODO ------------");
                    String apodo = EntradaTeclado.pedirCadena("Introduzca el apodo: ");
                    encontrado = equipo.buscarApodo(apodo);
                    if (encontrado == null) {
                        System.out.println(noEncontrado);
                    } else {
                        System.out.println(encontrado);
                    }
                break;
                case 5:
                    System.out.println("------------ BUSCAR POR DORSAL ------------");
                    int dorsal = EntradaTeclado.pedirEntero("Introduzca el dorsal: ");
                    encontrado = equipo.buscarDorsal(dorsal);
                    if (encontrado == null) {
                        System.out.println(noEncontrado);
                    } else {
                        System.out.println(encontrado);
                    }
                break;
                case 6:
                    System.out.println("Cerrando programa...");
                    break;
            }
        } while (opcion != 6);

    }

    private static Jugador pedirDatos() {
        String nombre = EntradaTeclado.pedirCadena("Introduzca el nombre: ");
        String apodo = EntradaTeclado.pedirCadena("Introduzca el apodo: ");
        String puesto = EntradaTeclado.pedirCadena("Introduzca el puesto: ");
        int dorsal = EntradaTeclado.pedirEntero("Introduzca el dorsal: ");
        String descripcion = EntradaTeclado.pedirCadena("Introduzca una descripción: ");
        return new Jugador(nombre, apodo, puesto, dorsal, descripcion);
    }

    public static boolean guardarArchivo(Equipo equipo) {
        boolean guardado = false;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(equipo.getRuta()))) {
            out.writeObject(equipo);
            guardado = true;
        } catch (IOException exception) {
            System.out.println("Problema al guardar archivo: " + exception.getMessage());
        }
        return guardado;
    }

    private static void JavaWriteXmlJaxbEx (Equipo equipo) throws JAXBException {

        var context = JAXBContext.newInstance(Equipo.class);
        var m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        m.marshal(equipo, System.out);

        m.marshal(equipo, new File("jugadores.xml"));
    }

    private static void JavaReadJaxbEx() throws JAXBException, FileNotFoundException {
        Equipo equipo = null;
        JAXBContext context = JAXBContext.newInstance(Equipo.class);

        equipo = (Equipo) context.createUnmarshaller().unmarshal(new FileReader("jugadores.obj"));

    }

    public static void main(String[] args) {
        Equipo equipo = new Equipo("jugadores.obj");
        menu(equipo);

        if (guardarArchivo(equipo)) {
            System.out.println("Archivo guardado correctamente.");
        } else {
            System.out.println("Equipo no guardado.");
        }

        try {
            JavaWriteXmlJaxbEx(equipo);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }

}