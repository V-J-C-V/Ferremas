package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @PostMapping
    public ResponseEntity<Usuario> ActualizarUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }
}
