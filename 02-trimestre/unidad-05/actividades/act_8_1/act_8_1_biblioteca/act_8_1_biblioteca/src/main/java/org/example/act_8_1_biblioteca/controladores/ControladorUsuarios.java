package org.example.act_8_1_biblioteca.controladores;

import org.example.act_8_1_biblioteca.modelo.dao.IUsuariosDAO;
import org.example.act_8_1_biblioteca.modelo.entidades.EntidadUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api-rest/usuarios")
public class ControladorUsuarios {

    @Autowired
    IUsuariosDAO usuariosDAO;

    @GetMapping
    public List<EntidadUsuarios> buscarUsuarios(){
        return (List<EntidadUsuarios>) usuariosDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadUsuarios> buscarUsuarioPorId(@PathVariable(value = "id") int id){
        Optional<EntidadUsuarios> usuario = usuariosDAO.findById(id);
        return usuario.map(entidadUsuarios -> ResponseEntity.ok().body(entidadUsuarios)).orElseGet(()
                -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EntidadUsuarios guardarUsuario(@Validated @RequestBody EntidadUsuarios usuario){
        return usuariosDAO.save(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable(value = "id") int id){
        Optional<EntidadUsuarios> usuario = usuariosDAO.findById(id);
        if(usuario.isPresent()){
            usuariosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@RequestBody EntidadUsuarios nuevoUsuario,
                                                @PathVariable(value = "id") int id){
        Optional<EntidadUsuarios> usuario = usuariosDAO.findById(id);
        if(usuario.isPresent()){
            usuario.get().setNombre(nuevoUsuario.getNombre());
            usuario.get().setApellidos(nuevoUsuario.getApellidos());
            usuario.get().setCodigo(nuevoUsuario.getCodigo());
            usuario.get().setFechanacimiento(nuevoUsuario.getFechanacimiento());
            usuariosDAO.save(usuario.get());
            return ResponseEntity.ok().body("Updated");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
}
