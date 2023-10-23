import java.io.*;
import java.util.Objects;

public class Main {

    private static void menu(Equipo equipo) {
        String textoMenu = """
                Elija una opción:
                1. Añadir jugador
                2. Listar jugadores
                3. Buscar jugadores por nombre 
                4. Buscar jugadores por apodo 
                5. Buscar jugadores por dorsal
                6. Guardar y salir
                """, noEncontrado = "Jugador no encontrado.", encontrado;
        int opcion;
        do {
            opcion = EntradaTeclado.pedirEntero(textoMenu, 1, 6);
            switch (opcion) {
                case 1 -> {
                    System.out.println("------------ AÑADIR JUGADOR ------------");
                    Jugador nuevo = pedirDatos();
                    equipo.nuevoJugador(nuevo);
                }
                case 2 -> System.out.println(equipo);
                case 3 -> {
                    System.out.println("------------ BUSCAR POR NOMBRE ------------");
                    String nombre = EntradaTeclado.pedirCadena("Introduzca el nombre: ");
                    encontrado = equipo.buscarNombre(nombre);
                    System.out.println(Objects.requireNonNullElse(encontrado, noEncontrado));
                }
                case 4 -> {
                    System.out.println("------------ BUSCAR POR APODO ------------");
                    String apodo = EntradaTeclado.pedirCadena("Introduzca el apodo: ");
                    encontrado = equipo.buscarApodo(apodo);
                    System.out.println(Objects.requireNonNullElse(encontrado, noEncontrado));
                }
                case 5 -> {
                    System.out.println("------------ BUSCAR POR DORSAL ------------");
                    int dorsal = EntradaTeclado.pedirEntero("Introduzca el dorsal: ");
                    encontrado = equipo.buscarDorsal(dorsal);
                    System.out.println(Objects.requireNonNullElse(encontrado, noEncontrado));
                }
                case 6 -> System.out.println("Cerrando programa...");
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

    public static void main(String[] args) {
        Equipo equipo = new Equipo("jugadores.obj");
        menu(equipo);

        if (guardarArchivo(equipo)) {
            System.out.println("Archivo guardado correctamente.");
        } else {
            System.out.println("Equipo no guardado.");
        }
    }
}