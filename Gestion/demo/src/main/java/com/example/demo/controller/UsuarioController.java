package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
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
}
