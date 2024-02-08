package org.example.crud;

import org.example.entidades.EmpleadosEntidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CrudEmpleado {

    public static void listarEmpleados(Session session) {
        Query<EmpleadosEntidad> miQuery = session.createQuery("from EmpleadosEntidad");
        List<EmpleadosEntidad> listaEmpleados = miQuery.list();
        for (Object obj : listaEmpleados) {
            EmpleadosEntidad empleado = (EmpleadosEntidad) obj;
            System.out.println("Empleado: " + empleado.getNombre() + ", número: " + empleado.getId());
        }
    }

    public static boolean actualizarEmpleado(Session session,
                                             int id,
                                             String nombre,
                                             String apellido1,
                                             String apellido2,
                                             String nif,
                                             int idDepartamento) {
        boolean empleadoActualizado = false;
        Query<EmpleadosEntidad> miQuery = session.createQuery("from EmpleadosEntidad where id = :id");
        miQuery.setParameter("id", id);

        List<EmpleadosEntidad> listaEmpleados = miQuery.list();

        if (!listaEmpleados.isEmpty()) {
            EmpleadosEntidad empleado = listaEmpleados.get(0);
            empleado.setNombre(nombre);
            empleado.setApellido1(apellido1);
            empleado.setApellido2(apellido2);
            empleado.setNif(nif);
            empleado.setIdDepartamento(idDepartamento);

            Transaction transaction = session.beginTransaction();
            session.update(empleado);
            transaction.commit();
            empleadoActualizado = true;
        }
        return empleadoActualizado;
    }

}
