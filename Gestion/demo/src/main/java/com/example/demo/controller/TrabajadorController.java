package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Trabajador;
import com.example.demo.repository.TrabajadorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/Trabajador")
public class TrabajadorController {
@Autowired
private TrabajadorRepository trabajadorRepository;
@GetMapping 

public List<Trabajador> ListarTrabajador(){
    return trabajadorRepository.findAll();
}



}
