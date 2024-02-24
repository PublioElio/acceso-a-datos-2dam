package org.example.act_8_1_biblioteca.modelo.dao;

import org.example.act_8_1_biblioteca.modelo.entidades.EntidadPrestamos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface IPrestamosDAO extends CrudRepository<EntidadPrestamos, Integer> {

    EntidadPrestamos findByLibro(String isbn);

    EntidadPrestamos findByUsuario(String usuario);

    EntidadPrestamos findByFechaprestamo(Date fechaPrestamo);
}
