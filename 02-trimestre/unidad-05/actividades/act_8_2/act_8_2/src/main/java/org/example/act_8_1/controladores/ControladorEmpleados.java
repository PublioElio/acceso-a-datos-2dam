package org.example.act_8_1.controladores;

import org.example.act_8_1.dto.EmpleadosDTO;
import org.example.act_8_1.modelo.dao.IEmpleadosDAO;
import org.example.act_8_1.modelo.entidades.EntidadDepartamentos;
import org.example.act_8_1.modelo.entidades.EntidadEmpleados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/empleados")
public class ControladorEmpleados {

    @Autowired
    IEmpleadosDAO empleadosDAO;

    @Autowired
    IEmpleadosDAO departamentosDAO;

    @GetMapping
    public List<EntidadEmpleados> buscarEmpleados() {
        return (List<EntidadEmpleados>) empleadosDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadEmpleados> buscarEmpleadoPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadEmpleados> empleado = empleadosDAO.findById(id);
        return empleado.map(entidadEmpleados -> ResponseEntity.ok().body(entidadEmpleados)).orElseGet(()
                -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EntidadEmpleados guardarEmpleado(@Validated @RequestBody EntidadEmpleados empleado) {
        return empleadosDAO.save(empleado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable(value = "id") int id) {
        Optional<EntidadEmpleados> empleado = empleadosDAO.findById(id);
        if (empleado.isPresent()) {
            empleadosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody EntidadEmpleados nuevoEmpleado,
                                                @PathVariable(value = "id") int id) {
        Optional<EntidadEmpleados> empleado = empleadosDAO.findById(id);
        if (empleado.isPresent()) {
            empleado.get().setNombre(nuevoEmpleado.getNombre());
            empleado.get().setPuesto(nuevoEmpleado.getPuesto());
            empleado.get().setDepno(nuevoEmpleado.getDepno());
            empleadosDAO.save(empleado.get());
            return ResponseEntity.ok().body("Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<EmpleadosDTO> buscarEmpleadoDTOPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadEmpleados> empleadoOptional = empleadosDAO.findById(id);

        if (empleadoOptional.isPresent()) {
            EntidadEmpleados empleado = empleadoOptional.get();
            EntidadDepartamentos departamento = empleado.getDepartamentosByDepno();
            EmpleadosDTO empleadosDTO = new EmpleadosDTO();
            empleadosDTO.setEmpno(empleado.getEmpno());
            empleadosDTO.setNombre(empleado.getNombre());
            empleadosDTO.setPuesto(empleado.getPuesto());
            empleadosDTO.setDepno(empleado.getDepno());
            empleadosDTO.setDepartamentoNombre(departamento.getNombre());
            empleadosDTO.setDepartamentoUbicacion(departamento.getUbicacion());
            return ResponseEntity.ok().body(empleadosDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
