package org.example.crud;

import org.example.EntradaTeclado;
import org.example.entidades.EmpleadosEntidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CrudEmpleado {

    private CrudEmpleado() {
    }

    public static List<EmpleadosEntidad> listarEmpleados(Session session) {
        Query<EmpleadosEntidad> miQuery = session.createQuery("from EmpleadosEntidad", EmpleadosEntidad.class);
        return miQuery.list();
    }

    public static boolean actualizarEmpleado(Session session,
                                             int idEmpleado,
                                             String nombre,
                                             String apellido1,
                                             String apellido2,
                                             String dni,
                                             int idDepartamento) {
        boolean empleadoActualizado = false;
        Query<EmpleadosEntidad> miQuery = session.createQuery("from EmpleadosEntidad where id = :id",
                EmpleadosEntidad.class);
        miQuery.setParameter("id", idEmpleado);

        List<EmpleadosEntidad> listaEmpleados = miQuery.list();

        if (!listaEmpleados.isEmpty()) {
            EmpleadosEntidad empleado = listaEmpleados.get(0);
            cumplimentarDatosEmpleado(idEmpleado, nombre, apellido1, apellido2, dni, idDepartamento, empleado);
            Transaction transaction = session.beginTransaction();
            session.merge(empleado);
            transaction.commit();
            empleadoActualizado = true;
        }
        return empleadoActualizado;
    }

    public static boolean insertarEmpleado(Session session,
                                           String nombre,
                                           String apellido1,
                                           String apellido2,
                                           String nif,
                                           int idDepartamento) {
        Query<EmpleadosEntidad> miQuery = session.createQuery("from EmpleadosEntidad where nif = :nif",
                EmpleadosEntidad.class);
        miQuery.setParameter("nif", nif);
        List<EmpleadosEntidad> listaEmpleados = miQuery.list();
        boolean empleadoInsertado = listaEmpleados.isEmpty();
        if (empleadoInsertado) {
            EmpleadosEntidad empleado = new EmpleadosEntidad(nombre, apellido1, apellido2, nif, idDepartamento);
            cumplimentarDatosNuevoEmpleado(nombre, apellido1, apellido2, nif, idDepartamento, empleado);
            empleado.setDepartamentosByIdDepartamento(CrudDepartamento.buscarDepartamento(session, idDepartamento));
            int id = generarNuevaId(session);
            empleado.setId(id);
            Transaction transaction = session.beginTransaction();
            session.persist(empleado);
            transaction.commit();
        }
        return empleadoInsertado;
    }

    private static void cumplimentarDatosEmpleado(int idEmpleado, String nombre, String apellido1, String apellido2, String dni, int idDepartamento, EmpleadosEntidad empleado) {
        empleado.setId(idEmpleado);
        empleado.setNombre(nombre);
        empleado.setApellido1(apellido1);
        empleado.setApellido2(apellido2);
        empleado.setNif(dni);
        empleado.setIdDepartamento(idDepartamento);
    }

    private static void cumplimentarDatosNuevoEmpleado(String nombre, String apellido1, String apellido2, String dni, int idDepartamento, EmpleadosEntidad empleado) {
        empleado.setNombre(nombre);
        empleado.setApellido1(apellido1);
        empleado.setApellido2(apellido2);
        empleado.setNif(dni);
        empleado.setIdDepartamento(idDepartamento);
    }

    public static boolean borrarEmpleado(Session session, int idEmpleado) {

        boolean empleadoEliminado = session.get(EmpleadosEntidad.class, idEmpleado) != null;
        if (empleadoEliminado) {
            Query<EmpleadosEntidad> miQuery = session.createQuery("from EmpleadosEntidad where id = :id",
                    EmpleadosEntidad.class);
            miQuery.setParameter("id", idEmpleado);
            List<EmpleadosEntidad> listaEmpleados = miQuery.list();
            EmpleadosEntidad empleado = listaEmpleados.get(0);
            if ("s".equalsIgnoreCase(confirmarBorrado(empleado))) {
                Transaction transaction = session.beginTransaction();
                session.remove(empleado);
                transaction.commit();
            }
        }
        return empleadoEliminado;
    }

    private static String confirmarBorrado(EmpleadosEntidad empleado) {
        String respuesta;
        do{
        respuesta = EntradaTeclado.pedirCadena("Los datos que se eliminaran son: " + empleado.toString() +
                "\nEsta seguro (S)i/(N)o");
        }while(!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));
        return respuesta;
    }

    public static int obtenerMaximaId(Session session) {
        Query<Integer> query = session.createQuery("SELECT MAX(e.id) FROM EmpleadosEntidad e", Integer.class);
        Integer maxId = query.uniqueResult();
        return maxId != null ? maxId : 0;
    }

    public static int generarNuevaId(Session session) {
        int maxId = obtenerMaximaId(session);
        return maxId + 1;
    }
}
