package com.acdat.apirest.springbootviernes9.dao;

import com.acdat.apirest.springbootviernes9.modelo.EmpleadosEntidad;
import org.springframework.data.repository.CrudRepository;

public interface IEmpleadosDAO extends CrudRepository<EmpleadosEntidad, Integer> {


}
