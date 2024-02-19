package com.acdat.apirest.springbootviernes9.dao;

import com.acdat.apirest.springbootviernes9.modelo.EmpleadosEntidad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IEmpleadosDAO extends CrudRepository<EmpleadosEntidad, Integer> {
    EmpleadosEntidad findByPuesto(String puesto);

    EmpleadosEntidad findByDepnoGreaterThanEqual(int depno);

    //@Query("SELECT e FROM EmpleadosEntidad e WHERE e.nombre LIKE %:patron%");
    EmpleadosEntidad findByNombre();

}
