package org.example.act_8_1_biblioteca.modelo.dao;

import org.example.act_8_1_biblioteca.modelo.entidades.EntidadUsuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuariosDAO extends CrudRepository<EntidadUsuarios, Integer> {

    EntidadUsuarios findByCodigo(String codigo);
}
