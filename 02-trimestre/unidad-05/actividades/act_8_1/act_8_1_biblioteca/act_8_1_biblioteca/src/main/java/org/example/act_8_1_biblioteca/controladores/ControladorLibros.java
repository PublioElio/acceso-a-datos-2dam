package org.example.act_8_1_biblioteca.controladores;

import org.example.act_8_1_biblioteca.modelo.dao.ILibrosDAO;
import org.example.act_8_1_biblioteca.modelo.entidades.EntidadLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/libros")
public class ControladorLibros {

    @Autowired
    ILibrosDAO librosDAO;

    @GetMapping
    public List<EntidadLibros> buscarLibros(){
        return (List<EntidadLibros>) librosDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadLibros> buscarLibroPorId(@PathVariable(value = "id") int id){
        Optional<EntidadLibros> libro = librosDAO.findById(id);
        return libro.map(entidadLibros -> ResponseEntity.ok().body(entidadLibros)).orElseGet(()
                -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EntidadLibros guardarDepartamento(@Validated @RequestBody EntidadLibros libro){
        return librosDAO.save(libro);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarLibro(@PathVariable(value = "id") int id){
        Optional<EntidadLibros> libro = librosDAO.findById(id);
        if(libro.isPresent()){
            librosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarLibro(@RequestBody EntidadLibros nuevoLibro,
                                                @PathVariable(value = "id") int id){
        Optional<EntidadLibros> libro = librosDAO.findById(id);
        if(libro.isPresent()){
            libro.get().setIsbn(nuevoLibro.getIsbn());
            libro.get().setTitulo(nuevoLibro.getTitulo());
            libro.get().setEditorial(nuevoLibro.getEditorial());
            libro.get().setCopias(nuevoLibro.getCopias());
            librosDAO.save(libro.get());
            return ResponseEntity.ok().body("Updated");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
