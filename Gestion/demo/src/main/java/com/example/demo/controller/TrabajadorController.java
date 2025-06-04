package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Trabajador;
import com.example.demo.repository.TrabajadorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

@GetMapping("/{id}")
public Trabajador BuscarTrabajadorPorId(@PathVariable Integer id){

    return trabajadorRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "No se encontro"
    ));
}
@PostMapping
public ResponseEntity<Trabajador> ActualizarTrabajador(@RequestBody Trabajador trabajador){
    Trabajador TrabajadorGuardado = trabajadorRepository.save(trabajador);
    return ResponseEntity.ok(TrabajadorGuardado);

}

}
