package org.example;

import static org.example.EntradaTeclado.*;

/**
 * El programa debe permitir crear nuevos
 * jugadores, mostrar la lista actual de jugadores y buscar un jugador por su
 * nombre, apodo y dorsal. Los datos deben almacenarse y recuperarse en un
 * archivo llamado jugadores.txt. Si no existe, debe crearse.
 */
public class Main {
    public static void main(String[] args) {
        Equipo equipo = new Equipo("Test");
        Registro registro = new Registro("jugadores.txt");
        menu(equipo);
        registro.escribir(equipo.toString());
    }

    public static void menu(Equipo equipo){
        String menu = "1.Crear nuevo jugador\n2.Mostrar lista actual de jugadores\n3.Buscar jugador\n4.Salir\n";
        int opcion;
        do{
            opcion = pedirEntero("Elija una opción:\n" + menu, 1, 4);
            switch (opcion){
                case 1 ->{
                    String nombre = pedirCadena("Introduzca el nombre: ");
                    String apodo = pedirCadena("Introduzca el apodo: ");
                    int dorsal = pedirEntero("Introduzca el dorsal: ", 1);
                    String posicion = pedirCadena("Introduzca la posición: ");
                    String descripcion = pedirCadena("Introduzca la descripción: ");
                    if(equipo.anyadirJugador(new Jugador(nombre, apodo, dorsal, posicion, descripcion))){
                        System.out.println("Jugador añadido correctamente");
                    }else{
                        System.out.println("Jugador no añadido");
                    }
                }
                case 2 -> System.out.println(equipo.toString());
                case 3 -> {
                    String nombre = pedirCadena("Introduzca el nombre: ");
                    String apodo = pedirCadena("Introduzca el apodo: ");
                    int dorsal = pedirEntero("Introduzca el dorsal: ", 1);
                    Jugador jugador = equipo.buscarJugador(new Jugador(nombre, apodo, dorsal));
                    if(jugador != null){
                        System.out.println(jugador);
                    }else{
                        System.out.println("Jugador no encontrado");
                    }
                }
                case 4 -> System.out.println("Cerrando programa...");
            }
        }while(opcion != 4);
    }
}