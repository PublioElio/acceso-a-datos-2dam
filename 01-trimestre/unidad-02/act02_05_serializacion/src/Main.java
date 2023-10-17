import java.io.*;
import java.util.Objects;

public class Main {

    /**
     * Este menú realiza todas las operaciones sobre la agenda, menos cargar o guardar, eso se hace en el main
     * (más abajo). El menú permite añadir contactos, borrar contactos, listarlos, buscar por nombre y apellidos y
     * buscar por teléfono
     *
     * @param agenda la agenda con la que queremos operar
     */
    private static void menu(Agenda agenda) {
        String textoMenu = """
                Elija una opción:
                1. Añadir contacto
                2. Eliminar contacto
                3. Listar contactos
                4. Buscar contactos por nombre completo
                5. Buscar contactos por teléfono
                6. Guardar y salir
                """, noEncontrado = "Contacto no encontrado.";
        int opcion;
        do {
            opcion = EntradaTeclado.pedirEntero(textoMenu, 1, 6);
            switch (opcion) {
                case 1 -> {
                    System.out.println("------------ AÑADIR CONTACTO ------------");
                    Contacto nuevo = pedirDatos();
                    agenda.nuevoContacto(nuevo);
                }
                case 2 -> {
                    System.out.println("------------ ELIMINAR CONTACTO ------------");
                    Contacto eliminar = pedirDatos();
                    agenda.borrarContacto(eliminar);
                }
                case 3 -> System.out.println(agenda);
                case 4 -> {
                    System.out.println("------------ BUSCAR POR NOMBRE COMPLETO ------------");
                    String nombre = EntradaTeclado.pedirCadena("Introduzca el nombre: "),
                            apellidos = EntradaTeclado.pedirCadena("Introduzca los apellidos: "),
                            encontrado = agenda.buscarPorNombreCompleto(nombre + " " + apellidos);
                    System.out.println(Objects.requireNonNullElse(encontrado, noEncontrado));
                }
                case 5 -> {
                    System.out.println("------------ BUSCAR POR TELÉFONO ------------");
                    String tlfn = EntradaTeclado.pedirCadena("Introduzca el teléfono: "),
                            encontrado = agenda.buscarPorTlfn(tlfn);
                    System.out.println(Objects.requireNonNullElse(encontrado, noEncontrado));
                }
                case 6 -> System.out.println("Cerrando agenda...");
            }
        } while (opcion != 6);

    }

    /**
     * Esta función pide una serie de datos, para devolver un contacto y trabajar con él
     *
     * @return un contacto con los datos introducidos
     */
    private static Contacto pedirDatos() {
        String nombre = EntradaTeclado.pedirCadena("Introduzca el nombre: ");
        String apellido = EntradaTeclado.pedirCadena("Introduzca los apellidos: ");
        String tlfno = EntradaTeclado.pedirCadena("Introduzca el teléfono: ");
        String correo = EntradaTeclado.pedirCadena("Introduzca el email: ");
        String descripcion = EntradaTeclado.pedirCadena("Introduzca una descripción: ");
        return new Contacto(nombre, apellido, tlfno, correo, descripcion);
    }

    /**
     * Esta función es para cargar la agenda desde la ruta de destino
     *
     * @param agenda la agenda que queremos cargar
     * @return la agenda cargada
     */
    public static Agenda cargarArchivo(Agenda agenda) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(agenda.getRuta()))) {
            agenda = (Agenda) in.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Problema al cargar archivo: " + exception.getMessage());
        }
        return agenda;
    }

    /**
     * Esta función es para guardar una agenda en un archivo (ruta) destino
     *
     * @param agenda la agenda que queremos guardar
     * @return true si la operación se ha completado correctamente
     */
    public static boolean guardarArchivo(Agenda agenda){
        boolean guardado = false;
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(agenda.getRuta()))){
            out.writeObject(agenda);
            guardado = true;
        } catch (IOException exception) {
            System.out.println("Problema al guardar archivo: " + exception.getMessage());
        }
        return guardado;
    }

    public static void main(String[] args) {
        Agenda agenda = new Agenda("contactos.obj"); // creamos la agenda, especificando la ruta dónde guardaremos los datos
        agenda = cargarArchivo(agenda); // cargamos la agenda (si ya existe)
        menu(agenda); // ejecutamos el menú

        if(guardarArchivo(agenda)){ // guardamos la agenda
            System.out.println("Agenda guardada correctamente."); // si se ha guardado correctamente, confirmo
        }else{
            System.out.println("Agenda no guardada.");
        }
    }
}