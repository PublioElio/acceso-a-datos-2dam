package org.example;

import org.example.crud.CrudEmpleado;
import org.example.entidades.EmpleadosEntidad;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {

        int option;

        String menuPrincipal = "------------- MENÚ PRINCIPAL -------------\n" +
                "1. Menú empleados\n" +
                "2. Menú departamentos\n" +
                "3. SALIR\n" +
                "-> ";

        String menuDepartamentos = "------------- MENÚ DEPARTAMENTOS -------------\n" +
                "1. Mostrar departamentos\n" +
                "2. Actualizar departamento\n" +
                "3. Añadir departamento\n" +
                "4. Borrar departamento\n" +
                "5. SALIR\n" +
                "-> ";

        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        try {
            Session session = abrirSession();
            do {
                option = EntradaTeclado.pedirEntero(menuPrincipal, 1, 3);
                switch (option) {
                    case 1 -> getModificarEmpleados(session);
                    case 2 -> System.out.println(""); // modificarDepartamentos();
                    case 3 -> System.out.println("Cerrando aplicación");
                    default -> System.out.println("Debe escoger una opción válida");
                }
            } while (option != 3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void getModificarEmpleados(Session session) {
        String menuEmpleados = "------------- MENÚ EMPLEADOS -------------\n" +
                "1. Listar empleados\n" +
                "2. Actualizar empleado\n" +
                "3. Añadir empleado\n" +
                "4. Borrar empleado\n" +
                "5. SALIR\n" +
                "-> ";
        int option = 0;
        do {
            option = EntradaTeclado.pedirEntero(menuEmpleados, 1, 5);
            switch (option) {
                case 1 -> CrudEmpleado.listarEmpleados(session);
                case 2 -> opcionActualizarEmpleado(session);
                case 3 -> System.out.println();//anyadirEmpleado();
                case 4 -> System.out.println();//borrarEmpleado();
                case 5 -> System.out.println("Volviendo al menú principal");
                default -> System.out.println("Debe escoger una opción válida");
            }
        } while (option != 5);
    }

    private static void opcionActualizarEmpleado(Session session) {
        int id, idDepartamento;
        String nombre, apellido1, apellido2, nif;
        id = EntradaTeclado.pedirEntero("Introduzca la id del empleado que desea modificar: ", 1);
        nombre = EntradaTeclado.pedirCadena("Introduzca el nombre: ");
        apellido1 = EntradaTeclado.pedirCadena("Introduzca el primer apellido: ");
        apellido2 = EntradaTeclado.pedirCadena("Introduzca el segundo apellido: ");
        do {
            nif = EntradaTeclado.pedirCadena("Introduzca un DNI válido para el empleado: ");
        } while (!ValidadorDNI.validarDNI(nif));
        idDepartamento = EntradaTeclado.pedirEntero("Introduzca la id del nuevo departameto: ", 1);
        CrudEmpleado.actualizarEmpleado(session, id, nombre, apellido1, apellido2, nif, idDepartamento);
    }

    static Session abrirSession() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session == null) {
            System.out.println("ERROR. Sesión no iniciada");
        }
        return session;
    }

}