package org.example.crud;

import org.example.entidades.DepartamentosEntidad;
import org.hibernate.Session;

public class CrudDepartamento {

    private CrudDepartamento() {
    }

    public static DepartamentosEntidad buscarDepartamento(Session session, int idDepartamento){
        return session.get(DepartamentosEntidad.class, idDepartamento);
    }
}
