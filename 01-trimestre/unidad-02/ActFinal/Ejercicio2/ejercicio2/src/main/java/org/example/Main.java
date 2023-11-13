package org.example;

import java.io.File;

import static org.example.EntradaTeclado.*;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        String rutaDatos = "jugadores.dat", rutaXml = "jugadores.xml";
        Registro registro = new Registro(rutaDatos, rutaXml);
        Equipo equipo = new Equipo();
        equipo = menuCargar(rutaDatos, rutaXml, equipo, registro);
        menu(equipo);
        menuGuardar(equipo, registro);
    }

    public static void menuGuardar(Equipo equipo, Registro registro) {
        String menu = "1.Datos\n2.XML\n";
        int opcion = pedirEntero("¿Cómo desea guardar el archivo?\n" + menu, 1, 2);
        switch (opcion) {
            case 1 -> {
                registro.guardarArchivo(equipo);
            }
            case 2 -> {
                registro.guardarXml(equipo);
            }
        }
    }

    public static Equipo menuCargar(String rutaDatos, String rutaXml, Equipo equipo, Registro registro) {
        String menu = "1.Datos\n2.XML\n";
        int opcion = pedirEntero("¿Qué documento desea cargar?\n" + menu, 1, 2);
        switch (opcion) {
            case 1 -> {
                File documentoDatos = new File(rutaDatos);
                if (!documentoDatos.exists()) {
                    equipo.setNombre(pedirCadena("Introduzca un nombre para el equipo: "));
                } else {
                    equipo = registro.cargarArchivo();
                }
            }
            case 2 -> {
                File documentoXml = new File(rutaXml);
                if (!documentoXml.exists()) {
                    equipo.setNombre(pedirCadena("Introduzca un nombre para el equipo: "));
                } else {
                    equipo = registro.cargarXml();
                }
            }
        }
        return equipo;
    }

    public static void menu(Equipo equipo) {
        String menu = "1.Crear nuevo jugador\n2.Mostrar lista actual de jugadores\n3.Buscar jugador\n4.Salir\n";
        int opcion;
        do {
            opcion = pedirEntero("Elija una opción:\n" + menu, 1, 4);
            switch (opcion) {
                case 1 -> {
                    String nombre = pedirCadena("Introduzca el nombre: ");
                    String apodo = pedirCadena("Introduzca el apodo: ");
                    int dorsal = pedirEntero("Introduzca el dorsal: ", 1);
                    String posicion = pedirCadena("Introduzca la posición: ");
                    String descripcion = pedirCadena("Introduzca la descripción: ");
                    if (equipo.anyadirJugador(new Jugador(nombre, apodo, dorsal, posicion, descripcion))) {
                        System.out.println("Jugador añadido correctamente");
                    } else {
                        System.out.println("Jugador no añadido");
                    }
                }
                case 2 -> System.out.println(equipo.toString());
                case 3 -> {
                    String nombre = pedirCadena("Introduzca el nombre: ");
                    String apodo = pedirCadena("Introduzca el apodo: ");
                    int dorsal = pedirEntero("Introduzca el dorsal: ", 1);
                    Jugador jugador = equipo.buscarJugador(new Jugador(nombre, apodo, dorsal));
                    if (jugador != null) {
                        System.out.println(jugador);
                    } else {
                        System.out.println("Jugador no encontrado");
                    }
                }
                case 4 -> System.out.println("Cerrando programa...");
            }
        } while (opcion != 4);
    }
}