package org.example.act_8_1.modelo.dao;

import org.example.act_8_1.modelo.entidades.EmpleadosEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadosDAO extends CrudRepository<EmpleadosEntity, Integer> {

    EmpleadosEntity findByNif(String nif);

    EmpleadosEntity findByIdDepartamentoGreaterThanEqual(Integer idDepartamento);
}
