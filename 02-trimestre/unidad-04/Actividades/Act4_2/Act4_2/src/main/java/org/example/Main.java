package org.example;

import jakarta.persistence.*;
import org.example.entidades.DepartamentosEntityJPA;
import org.example.entidades.EmpleadosEntityJPA;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Este es un programa para gestionar Empleados y Depatamentos con JPA e Hibernate
 */
public class Main {

    private static final String ELIJA_OPCION = "Elija una opción:\n";
    private static final String ELIJA_OPCION_VALIDA = "Elija una opción válida";
    private static final String VOLVIENDO_AL_MENU_PRINCIPAL = "Volviendo al menú principal";
    public static final String INTRODUZCA_LA_ID_DEL_EMPLEADO = "Introduzca la ID del empleado: ";
    public static final String EMPLEADO_NO_ENCONTRADO = "Empleado no encontrado";
    public static final String VOLVER_AL_MENU_PRINCIPAL = "Volver al menú principal\n";

    public static void main(String[] args) {
        String textoMenuPrincipal = "------------ MENÚ PRINCIPAL ------------\n" + ELIJA_OPCION +
                "1. Gestionar Empleados:\n" +
                "2. Gestionar Departamentos:\n" +
                "3. Salir\n" +
                "-> ";

        int opcion;

        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);


        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {


            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();
                comprobarEstadoSesion(session);

                CrudManager crud = new CrudManager(sessionFactory);
                do {
                    opcion = EntradaTeclado.pedirEntero(textoMenuPrincipal, 1, 3);
                    switch (opcion) {
                        case 1 -> gestionarEmpleados(crud);
                        case 2 -> getGestionarDepartamentos(crud);
                        case 3 -> System.out.println("Cerrando programa");
                        default -> System.out.println(ELIJA_OPCION_VALIDA);
                    }

                } while (opcion != 3);

                transaction.commit();
            } catch (Exception e) {
                gestionarExcepcionesEnMain(e, transaction);

            } finally {
                cerrarTodo(session, sessionFactory);
            }
        }
    }

    private static void getGestionarDepartamentos(CrudManager crud) {
        int opcion;
        String textoMenuDepartamentos = "------------ MENÚ DEPARTAMENTOS ------------\n" + ELIJA_OPCION +
                "1. Añadir departamento:\n" +
                "2. Ver datos de un departamento:\n" +
                "3. Listar departamentos:\n" +
                "4. " + VOLVER_AL_MENU_PRINCIPAL +
                "-> ";
        do {
            opcion = EntradaTeclado.pedirEntero(textoMenuDepartamentos, 1, 4);
            switch (opcion) {
                case 1 -> getAnyadirDepartamento(crud);
                case 2 -> getVerDatosDeDepartamento(crud);
                case 3 -> {
                    List<DepartamentosEntityJPA> listaDepartamentos = crud.listarDepartamentosPorNombre();
                    for (DepartamentosEntityJPA departamento : listaDepartamentos) {
                        System.out.println(departamento);
                    }
                }
                case 4 -> System.out.println(VOLVIENDO_AL_MENU_PRINCIPAL);
                default -> System.out.println(ELIJA_OPCION_VALIDA);
            }
        } while (opcion != 4);
    }

    private static void getVerDatosDeDepartamento(CrudManager crud) {
        DepartamentosEntityJPA departamento = crud.obtenerDepartamento(EntradaTeclado.pedirEntero("Introduzca la ID del departamento que desea consultar: ", 0));
        if (departamento != null) {
            System.out.println(departamento);
        }
    }

    private static void getAnyadirDepartamento(CrudManager crud) {
        DepartamentosEntityJPA nuevoDept = getPedirDatosDepartamento(crud);
        crud.agregarDepartamento(nuevoDept);
    }

    private static DepartamentosEntityJPA getPedirDatosDepartamento(CrudManager crud) {
        DepartamentosEntityJPA departamento = new DepartamentosEntityJPA();
        do {
            departamento.setNombre(EntradaTeclado.pedirCadena("Introduzca el nombre del departamento: "));
        } while (departamento.getNombre().isEmpty() || comprobarSiExisteDepartamento(crud, departamento.getNombre()));
        do {
            departamento.setPresupuesto(EntradaTeclado.pedirDouble("Introduzca el presupuesto: "));
        } while (departamento.getPresupuesto() < 0);
        do {
            departamento.setGastos(EntradaTeclado.pedirDouble("Introduzca los gastos: "));
        } while (departamento.getGastos() < 0);
        return departamento;
    }

    /**
     * Este método devuelve true si ya existe un departamento con el mismo nombre en la base de datos
     *
     * @param crud               el gestor de consultas
     * @param nombreDepartamento la cadena de caracteres que se corresponde con el nombre del departamento
     * @return true si encuentra una coincidencia
     */
    private static boolean comprobarSiExisteDepartamento(CrudManager crud, String nombreDepartamento) {
        List<DepartamentosEntityJPA> listaDepartamentos = crud.listarDepartamentosPorNombre();
        boolean existe = false;
        for (DepartamentosEntityJPA departamento : listaDepartamentos) {
            if (departamento.getNombre().equalsIgnoreCase(nombreDepartamento)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    private static void gestionarEmpleados(CrudManager crud) {
        int opcion;
        String textoMenuEmpleados = "------------ MENÚ EMPLEADOS ------------\n" + ELIJA_OPCION +
                "1. Añadir empleado:\n" +
                "2. Modificar empleado:\n" +
                "3. Borrar empleado:\n" +
                "4. Buscar empleado por id:\n" +
                "5. Listar empleados:\n" +
                "6. Listar empleados por departamento:\n" +
                "7. " + VOLVER_AL_MENU_PRINCIPAL +
                "-> ";
        do {
            opcion = EntradaTeclado.pedirEntero(textoMenuEmpleados, 1, 7);
            switch (opcion) {
                case 1 -> {
                    EmpleadosEntityJPA empleado = pedirDatosEmpleado(crud);
                    if (empleado != null) {
                        crud.agregarEmpleado(empleado);
                    } else {
                        System.out.println("Empleado no introducido");
                    }
                }
                case 2 -> {
                    int idEmpleado = EntradaTeclado.pedirEntero(INTRODUZCA_LA_ID_DEL_EMPLEADO, 0);
                    EmpleadosEntityJPA empleado = crud.obtenerEmpleado(idEmpleado);
                    if (empleado != null) {
                        empleado = pedirDatosEmpleado(crud);
                        empleado.setId(idEmpleado);
                        crud.actualizarEmpleado(empleado);
                    } else {
                        System.out.println(EMPLEADO_NO_ENCONTRADO);
                    }
                }
                case 3 -> {
                    int idEmpleado = EntradaTeclado.pedirEntero(INTRODUZCA_LA_ID_DEL_EMPLEADO, 0);
                    crud.eliminarEmpleado(idEmpleado);
                }
                case 4 -> {
                    EmpleadosEntityJPA empleado = crud.obtenerEmpleado(EntradaTeclado.pedirEntero(INTRODUZCA_LA_ID_DEL_EMPLEADO, 0));
                    if (empleado != null) {
                        System.out.println(empleado);
                    } else {
                        System.out.println(EMPLEADO_NO_ENCONTRADO);
                    }
                }
                case 5 -> {
                    List<EmpleadosEntityJPA> listadoEmpleados = crud.listarEmpleados();
                    for (EmpleadosEntityJPA empleado : listadoEmpleados) {
                        System.out.println(empleado);
                    }
                }
                case 6 -> {
                    int idDepartamento = EntradaTeclado.pedirEntero("Introduzca la ID del departamento: ", 0);
                    List<EmpleadosEntityJPA> listadoEmpleados = crud.obtenerEmpleadosPorDepartamento(idDepartamento);
                    if (!listadoEmpleados.isEmpty()) {
                        for (EmpleadosEntityJPA empleado : listadoEmpleados) {
                            System.out.println(empleado);
                        }
                    }
                }
                case 7 -> System.out.println(VOLVIENDO_AL_MENU_PRINCIPAL);
                default -> System.out.println(ELIJA_OPCION_VALIDA);
            }

        } while (opcion != 7);
    }

    private static EmpleadosEntityJPA pedirDatosEmpleado(CrudManager crud) {
        EmpleadosEntityJPA empleado = new EmpleadosEntityJPA();
        do {
            empleado.setNif(EntradaTeclado.pedirCadena("Introduzca el DNI del empleado: "));
        } while (!ValidadorDNI.validarDNI(empleado.getNif()));
        do {
            empleado.setNombre(EntradaTeclado.pedirCadena("Introduzca el nombre del empleado: "));
        } while (empleado.getNombre().isEmpty());
        do {
            empleado.setApellido1(EntradaTeclado.pedirCadena("Introduzca el primer apellido del empleado: "));
        } while (empleado.getApellido1().isEmpty());
        empleado.setApellido2(EntradaTeclado.pedirCadena("Introduzca el segundo apellido del empleado: "));
        do {
            empleado.setIdDepartamento(EntradaTeclado.pedirEntero("Introduzca el departamento del empleado: "));
        } while (!verificarDepartamento(crud, empleado.getIdDepartamento()));
        return empleado;
    }

    public static boolean verificarDepartamento(CrudManager crud, Integer departamentoId) {
        List<Integer> listaDepartamentos = crud.listarDepartamentosPorID();
        return listaDepartamentos.contains(departamentoId);
    }

    private static void cerrarTodo(Session session, SessionFactory sessionFactory) {
        assert session != null;
        session.close();
        sessionFactory.close();
    }

    private static void gestionarExcepcionesEnMain(Exception e, Transaction transaction) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        Logger.getLogger(SessionFactory.class.getName()).log(Level.SEVERE, "Error al manejar la sesión", e);
    }

    private static void comprobarEstadoSesion(Session session) {
        if (session != null) {
            Logger.getLogger(SessionFactory.class.getName()).log(Level.INFO, "Sesión iniciada");
        } else {
            Logger.getLogger(SessionFactory.class.getName()).log(Level.SEVERE, "Sesión no iniciada");
        }
    }
}
