package org.example.act_8_1_biblioteca.modelo.dao;

import org.example.act_8_1_biblioteca.modelo.entidades.EntidadLibros;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILibrosDAO extends CrudRepository<EntidadLibros, Integer> {

    EntidadLibros findByTituloContaining(String subcadena);

    EntidadLibros findByIsbn(String isbn);

    EntidadLibros findByEditorialIgnoreCase(String editorial);

}
