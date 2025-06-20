package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Producto;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/Usuario")
public class UsuarioController {
@Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> ListarUsuario(){
        return usuarioRepository.findAll();
        
    }
    @GetMapping("/{id}")
    public Usuario BuscarUsuarioPorId(@PathVariable Integer id){
        return usuarioRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "No se encontro "
        ));
    }
    @DeleteMapping("/{id}")
public ResponseEntity<Map<String, String>> EliminarUsuario(@PathVariable Integer id){
    if (!usuarioRepository.existsById(id)) {
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "No encontrado"
        );

    }
    usuarioRepository.deleteById(id);
    Map<String, String> respuesta = new HashMap();
    respuesta.put("Mensaje", "Usuario eliminado");
    respuesta.put("Id Usuario", id.toString());
    return ResponseEntity.ok(respuesta);
    
    
}


    @PostMapping
    public ResponseEntity<Usuario> ActualizarUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }


    @PutMapping("/{id}")
public ResponseEntity<Usuario> UsuarioPut(@PathVariable int id, @RequestBody Usuario usuarioActualizar){

    Usuario usuarioExistente = usuarioRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(

    HttpStatus.NOT_FOUND,
    "No se encontro"
    ));


usuarioExistente.setContrasena(usuarioActualizar.getContrasena());
usuarioExistente.setEmail(usuarioActualizar.getEmail());
usuarioExistente.setId_usuario(usuarioActualizar.getId_usuario());
    Usuario usuarioGuardado = usuarioRepository.save(usuarioExistente);
    return ResponseEntity.ok(usuarioGuardado);
}

}
