package org.example.crud;

import org.example.entidades.DepartamentosEntidad;
import org.example.entidades.EmpleadosEntidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CrudDepartamento {

    private CrudDepartamento() {
    }

    public static DepartamentosEntidad buscarDepartamento(Session session, int idDepartamento){
        return session.get(DepartamentosEntidad.class, idDepartamento);
    }

    public static List<DepartamentosEntidad> listarDepartamentos(Session session) {
        Query<DepartamentosEntidad> miQuery = session.createQuery("from DepartamentosEntidad ",
                DepartamentosEntidad.class);
        return miQuery.list();
    }

    public static boolean actualizarDepartamento(Session session,
                                                int idDepartamento,
                                                String nombre,
                                                String presupuesto,
                                                String apellido2,
                                                String dni) {
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

}
