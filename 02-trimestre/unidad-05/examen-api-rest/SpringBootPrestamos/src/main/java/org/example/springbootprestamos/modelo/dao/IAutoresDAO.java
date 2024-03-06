package org.example.springbootprestamos.modelo.dao;

import org.example.springbootprestamos.modelo.entidades.EntidadAutores;
import org.springframework.data.repository.CrudRepository;

public interface IAutoresDAO extends CrudRepository<EntidadAutores, Integer> {
    EntidadAutores findByIdAutor(int idAutor);
    EntidadAutores findByNombreAutor(String nombre);
    EntidadAutores findByPais(String pais);
}
