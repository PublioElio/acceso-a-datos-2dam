package com.acdat.apirest.springbootviernes9.controladores;

import com.acdat.apirest.springbootviernes9.dao.IDepartamentosDAO;
import com.acdat.apirest.springbootviernes9.dao.IEmpleadosDAO;
import com.acdat.apirest.springbootviernes9.modelo.EmpleadosEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/empleados")
public class controladorEmpleados {

    @Autowired
    IEmpleadosDAO empleadosDAO;
    @Autowired
    IDepartamentosDAO departamentosDAO;

    @GetMapping
    public List<EmpleadosEntidad> buscarEmpleados() {
        return (List<EmpleadosEntidad>) empleadosDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadosEntidad> buscarEmpleadosPorId(@PathVariable(value = "id") int id) {
        Optional<EmpleadosEntidad> empleado = empleadosDAO.findById(id);
        return empleado.map(empleadosEntidad -> ResponseEntity.ok().body(empleadosEntidad)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmpleadosEntidad guardarEmpleado(@Validated @RequestBody EmpleadosEntidad empleado) {
        return empleadosDAO.save(empleado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable(value = "id") int id) {
        Optional<EmpleadosEntidad> empleado = empleadosDAO.findById(id);

        if (empleado.isPresent()) {
            empleadosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody EmpleadosEntidad nuevoEmpleado, @PathVariable(value = "id") int id) {
        Optional<EmpleadosEntidad> empleado = empleadosDAO.findById(id);
        if (empleado.isPresent()) {
            empleado.get().setNombre(nuevoEmpleado.getNombre());
            empleado.get().setApellido1(nuevoEmpleado.getApellido1());
            empleado.get().setApellido2(nuevoEmpleado.getApellido2());
            empleado.get().setNif(nuevoEmpleado.getNif());
            empleado.get().setDepartamentosByIdDepartamento(nuevoEmpleado.getDepartamentosByIdDepartamento());
            empleadosDAO.save(empleado.get());
            return ResponseEntity.ok().body("Update");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
