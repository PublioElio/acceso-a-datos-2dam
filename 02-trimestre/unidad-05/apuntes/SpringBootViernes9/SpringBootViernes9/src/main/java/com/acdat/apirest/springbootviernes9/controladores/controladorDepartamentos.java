package com.acdat.apirest.springbootviernes9.controladores;

import com.acdat.apirest.springbootviernes9.dao.IDepartamentosDAO;
import com.acdat.apirest.springbootviernes9.modelo.DepartamentosEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api-rest/departamentos")
public class controladorDepartamentos {

    @Autowired
    IDepartamentosDAO departamentosDAO;

    @GetMapping
    public List<DepartamentosEntidad> buscarDepartamentos() {
        return (List<DepartamentosEntidad>) departamentosDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentosEntidad> buscarDepartamentosPorId(@PathVariable(value = "id") int id) {
        Optional<DepartamentosEntidad> departamento = departamentosDAO.findById(id);
        return departamento.map(DepartamentosEntidad -> ResponseEntity.ok().body(DepartamentosEntidad)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DepartamentosEntidad guardarDepartamento(@Validated @RequestBody DepartamentosEntidad departamento) {
        return departamentosDAO.save(departamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarDepartamento(@PathVariable(value = "id") int id) {
        Optional<DepartamentosEntidad> departamento = departamentosDAO.findById(id);

        if (departamento.isPresent()) {
            departamentosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDepartamento(@RequestBody DepartamentosEntidad nuevoDepartamento, @PathVariable(value = "id") int id) {
        Optional<DepartamentosEntidad> departamento = departamentosDAO.findById(id);
        if (departamento.isPresent()) {
            departamento.get().setNombre(nuevoDepartamento.getNombre());
            departamento.get().setGastos(nuevoDepartamento.getGastos());
            departamento.get().setPresupuesto(nuevoDepartamento.getPresupuesto());

            departamentosDAO.save(departamento.get());
            return ResponseEntity.ok().body("Update");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
