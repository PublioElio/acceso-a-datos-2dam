package org.example.act_8_1.modelo.dao;

import org.example.act_8_1.modelo.entidades.EntidadEmpleados;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadosDAO extends CrudRepository<EntidadEmpleados, Integer> {

    EntidadEmpleados findByNombreStartingWith(String nombre, String prefijo);

    EntidadEmpleados findByPuestoContaining(String subcadena);
}
