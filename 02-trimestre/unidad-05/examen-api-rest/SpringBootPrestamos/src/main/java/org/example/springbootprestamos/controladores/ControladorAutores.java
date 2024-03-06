package org.example.springbootprestamos.controladores;

import org.example.springbootprestamos.modelo.dao.IAutoresDAO;
import org.example.springbootprestamos.modelo.entidades.EntidadAutores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/autores")
public class ControladorAutores {

    @Autowired
    IAutoresDAO autoresDAO;

    @GetMapping
    public List<EntidadAutores> buscarAutores() {
        return (List<EntidadAutores>) autoresDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadAutores> buscarAutorPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadAutores> autor = autoresDAO.findById(id);
        return autor.map(entidadAutores -> ResponseEntity.ok().body(entidadAutores)).orElseGet(()
                -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EntidadAutores guardarAutor(@Validated @RequestBody EntidadAutores autor) {
        return autoresDAO.save(autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarAutor(@PathVariable(value = "id") int id) {
        Optional<EntidadAutores> autor = autoresDAO.findById(id);
        if (autor.isPresent()) {
            autoresDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAutor(@RequestBody EntidadAutores nuevoAutor,
                                                @PathVariable(value = "id") int id) {
        Optional<EntidadAutores> autor = autoresDAO.findById(id);
        if (autor.isPresent()) {
            autor.get().setIdAutor(nuevoAutor.getIdAutor());
            autor.get().setNombreAutor(nuevoAutor.getNombreAutor());
            autor.get().setPais(nuevoAutor.getPais());
            return ResponseEntity.ok().body("Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
