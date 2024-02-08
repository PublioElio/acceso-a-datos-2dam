package org.example;

import org.example.crud.CrudDepartamento;
import org.example.crud.CrudEmpleado;
import org.example.entidades.DepartamentosEntidad;
import org.example.entidades.EmpleadosEntidad;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.logging.Level;


public class Main {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String DEBE_ESCOGER_SESION_VALIDA = "Debe escoger una opción válida";

    public static void main(String[] args) {

        int option;

        String menuPrincipal = "------------- MENÚ PRINCIPAL -------------\n" +
                "1. Menú empleados\n" +
                "2. Menú departamentos\n" +
                "3. SALIR\n" +
                "-> ";

        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        try {
            Session session = abrirSession();
            do {
                option = EntradaTeclado.pedirEntero(menuPrincipal, 1, 3);
                switch (option) {
                    case 1 -> gestionarEmpleados(session);
                    case 2 -> gestionarDepartamentos(session);
                    case 3 -> System.out.println("Cerrando aplicación");
                    default -> System.out.println(DEBE_ESCOGER_SESION_VALIDA);
                }
            } while (option != 3);
        } catch (Exception e) {
            logger.error("Error en el inicio de sesión: " + e.getMessage());
        }
    }

    private static void gestionarDepartamentos(Session session) {
        String menuDepartamentos = "------------- MENÚ DEPARTAMENTOS -------------\n" +
                "1. Mostrar departamentos\n" +
                "2. Actualizar departamento\n" +
                "3. Añadir departamento\n" +
                "4. Borrar departamento\n" +
                "5. SALIR\n" +
                "-> ";
        int option;
        do {
            option = EntradaTeclado.pedirEntero(menuDepartamentos, 1, 5);
            switch (option) {
                case 1 -> mostrarListaDepartamentos(session);
                case 2 -> opcionActualizarDepartamento(session);
                case 3 -> opcionAgregarDepartamento(session);
                case 4 -> opcionBorrarDepartamento(session);
                case 5 -> System.out.println("Volviendo al menú principal");
                default -> System.out.println(DEBE_ESCOGER_SESION_VALIDA);
            }
        } while (option != 5);
    }

    private static void opcionBorrarDepartamento(Session session) {

    }

    private static void opcionAgregarDepartamento(Session session) {

    }

    private static void opcionActualizarDepartamento(Session session) {

    }

    private static void mostrarListaDepartamentos(Session session) {
        List<DepartamentosEntidad> listaDepartamentos = CrudDepartamento.listarDepartamentos(session);
        for (DepartamentosEntidad departamento : listaDepartamentos) {
            System.out.println(departamento.toString());
        }
    }

    private static void gestionarEmpleados(Session session) {
        String menuEmpleados = "------------- MENÚ EMPLEADOS -------------\n" +
                "1. Listar empleados\n" +
                "2. Actualizar empleado\n" +
                "3. Añadir empleado\n" +
                "4. Borrar empleado\n" +
                "5. SALIR\n" +
                "-> ";
        int option;
        do {
            option = EntradaTeclado.pedirEntero(menuEmpleados, 1, 5);
            switch (option) {
                case 1 -> mostrarListaEmpleados(session);
                case 2 -> opcionActualizarEmpleado(session);
                case 3 -> opcionAgregarEmpleado(session);
                case 4 -> opcionBorrarEmpleado(session);
                case 5 -> System.out.println("Volviendo al menú principal");
                default -> System.out.println(DEBE_ESCOGER_SESION_VALIDA);
            }
        } while (option != 5);
    }

    private static void opcionBorrarEmpleado(Session session) {
        int idEmpleado = EntradaTeclado.pedirEntero("Introduzca la id del empleado a borrar: ",
                0, CrudEmpleado.obtenerMaximaId(session));
        if (CrudEmpleado.borrarEmpleado(session, idEmpleado)) {
            logger.info("Empleado borrado correctamente");
        } else{
            logger.error("Error: empleado no borrado");
        }
    }

    private static void mostrarListaEmpleados(Session session) {
        List<EmpleadosEntidad> listaEmpleados = CrudEmpleado.listarEmpleados(session);
        for (EmpleadosEntidad empleado : listaEmpleados) {
            System.out.println(empleado.toString());
        }
    }

    private static void opcionActualizarEmpleado(Session session) {
        EmpleadosEntidad empleadoActualizado = pedirDatosEmpleado();
        if (CrudEmpleado.actualizarEmpleado(session, empleadoActualizado.getId(), empleadoActualizado.getNombre(),
                empleadoActualizado.getApellido1(), empleadoActualizado.getApellido2(), empleadoActualizado.getNif(),
                empleadoActualizado.getIdDepartamento())) {
            logger.info("Empleado actualizado correctamente");
        } else {
            logger.error("Empleado no actualizado");
        }
    }

    private static void opcionAgregarEmpleado(Session session) {
        EmpleadosEntidad nuevoEmpleado = pedirDatosNuevoEmpleado();
        if (CrudEmpleado.insertarEmpleado(session, nuevoEmpleado.getNombre(),
                nuevoEmpleado.getApellido1(), nuevoEmpleado.getApellido2(), nuevoEmpleado.getNif(),
                nuevoEmpleado.getIdDepartamento())) {
            logger.info("Empleado insertado correctamente");
        } else {
            logger.error("Empleado no insertado");
        }
    }

    private static EmpleadosEntidad pedirDatosEmpleado() {
        int id;
        int idDepartamento;
        String nombre;
        String apellido1;
        String apellido2;
        String nif;
        id = EntradaTeclado.pedirEntero("Introduzca la id del empleado: ", 0);
        nombre = EntradaTeclado.pedirCadena("Introduzca el nombre: ");
        apellido1 = EntradaTeclado.pedirCadena("Introduzca el primer apellido: ");
        apellido2 = EntradaTeclado.pedirCadena("Introduzca el segundo apellido: ");
        do {
            nif = EntradaTeclado.pedirCadena("Introduzca un DNI válido para el empleado: ");
        } while (!ValidadorDNI.validarDNI(nif));
        idDepartamento = EntradaTeclado.pedirEntero("Introduzca la id del nuevo departamento: ", 1);
        return new EmpleadosEntidad(id, nombre, apellido1, apellido2, nif, idDepartamento);
    }

    private static EmpleadosEntidad pedirDatosNuevoEmpleado() {
        String nombre;
        String apellido1;
        String apellido2;
        String nif;
        int idDepartamento;
        nombre = EntradaTeclado.pedirCadena("Introduzca el nombre: ");
        apellido1 = EntradaTeclado.pedirCadena("Introduzca el primer apellido: ");
        apellido2 = EntradaTeclado.pedirCadena("Introduzca el segundo apellido: ");
        do {
            nif = EntradaTeclado.pedirCadena("Introduzca un DNI válido para el empleado: ");
        } while (!ValidadorDNI.validarDNI(nif));
        idDepartamento = EntradaTeclado.pedirEntero("Introduzca la id del nuevo departamento: ", 1);
        return new EmpleadosEntidad(nombre, apellido1, apellido2, nif, idDepartamento);
    }


    static Session abrirSession() {
        Session session = null;
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (HibernateException e) {
            logger.error("Error al abrir la sesión de Hibernate: {}", e.getMessage());
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
        return session;
    }

}