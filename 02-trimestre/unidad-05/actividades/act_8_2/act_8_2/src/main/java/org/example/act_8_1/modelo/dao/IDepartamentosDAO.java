package org.example.act_8_1.modelo.dao;

import org.example.act_8_1.modelo.entidades.EntidadDepartamentos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartamentosDAO extends CrudRepository<EntidadDepartamentos, Integer> {
    EntidadDepartamentos findByNombreIgnoreCase(String nombre);
    EntidadDepartamentos findByUbicacionIgnoreCase(String ubicacion);
    EntidadDepartamentos findByDepno(int depno);
}
