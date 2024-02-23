package org.example.act_8_1.modelo.dao;

import org.example.act_8_1.modelo.entidades.DepartamentosEntity;
import org.example.act_8_1.modelo.entidades.EmpleadosEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentosDAO extends CrudRepository<DepartamentosEntity, Integer> {

    DepartamentosEntity findByNombre(String nombre);

    
}
