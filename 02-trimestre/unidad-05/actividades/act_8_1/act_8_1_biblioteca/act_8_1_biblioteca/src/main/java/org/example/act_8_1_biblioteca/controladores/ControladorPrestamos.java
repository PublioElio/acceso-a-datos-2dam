package org.example.act_8_1_biblioteca.controladores;

import org.example.act_8_1_biblioteca.modelo.dao.IPrestamosDAO;
import org.example.act_8_1_biblioteca.modelo.entidades.EntidadPrestamos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/prestamos")
public class ControladorPrestamos {

    @Autowired
    IPrestamosDAO prestamosDAO;

    @GetMapping
    public List<EntidadPrestamos> buscarPrestamos(){
        return (List<EntidadPrestamos>) prestamosDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadPrestamos> buscarPrestamoPorId(@PathVariable(value = "id") int id){
        Optional<EntidadPrestamos> prestamo = prestamosDAO.findById(id);
        return prestamo.map(entidadPrestamos -> ResponseEntity.ok().body(entidadPrestamos)).orElseGet(()
                -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EntidadPrestamos guardarPrestamo(@Validated @RequestBody EntidadPrestamos prestamo){
        return prestamosDAO.save(prestamo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPrestamo(@PathVariable(value = "id") int id){
        Optional<EntidadPrestamos> prestamo = prestamosDAO.findById(id);
        if(prestamo.isPresent()){
            prestamosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPrestamo(@RequestBody EntidadPrestamos nuevoPrestamo,
                                                @PathVariable(value = "id") int id){
        Optional<EntidadPrestamos> prestamo = prestamosDAO.findById(id);
        if(prestamo.isPresent()){
            prestamo.get().setFechaprestamo(nuevoPrestamo.getFechaprestamo());
            prestamo.get().setFechadevolucion(nuevoPrestamo.getFechadevolucion());
            prestamo.get().setLibro(nuevoPrestamo.getLibro());
            prestamo.get().setUsuario(nuevoPrestamo.getUsuario());
            prestamosDAO.save(prestamo.get());
            return ResponseEntity.ok().body("Updated");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
