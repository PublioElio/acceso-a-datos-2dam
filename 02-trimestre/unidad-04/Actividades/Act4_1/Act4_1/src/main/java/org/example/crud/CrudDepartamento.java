package org.example.crud;

import org.example.entidades.DepartamentosEntidad;
import org.example.entidades.EmpleadosEntidad;
import org.hibernate.Session;
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
}
