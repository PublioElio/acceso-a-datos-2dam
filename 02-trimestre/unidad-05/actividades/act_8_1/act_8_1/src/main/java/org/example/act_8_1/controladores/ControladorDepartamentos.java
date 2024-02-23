package org.example.act_8_1.controladores;

import org.example.act_8_1.modelo.dao.IDepartamentosDAO;
import org.example.act_8_1.modelo.entidades.EntidadDepartamentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/departamentos")
public class ControladorDepartamentos {

    @Autowired
    IDepartamentosDAO departamentosDAO;

    @GetMapping
    public List<EntidadDepartamentos> buscarDepartamentos() {
        return (List<EntidadDepartamentos>) departamentosDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadDepartamentos> buscarDepartamentoPorId(@PathVariable(value = "id") int id){
        Optional<EntidadDepartamentos> departamento = departamentosDAO.findById(id);
        return departamento.map(entidadDepartamentos -> ResponseEntity.ok().body(entidadDepartamentos)).orElseGet(()
                -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EntidadDepartamentos guardarDepartamento(@Validated @RequestBody EntidadDepartamentos departamento){
        return departamentosDAO.save(departamento);
    }
}
