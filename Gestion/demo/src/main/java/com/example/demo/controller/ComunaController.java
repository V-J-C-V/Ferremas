package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Comuna;
import com.example.demo.repository.ComunaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/Comuna")


public class ComunaController {
@Autowired
private ComunaRepository comunaRepository;
@GetMapping
public List<Comuna> ListarComuna(){
    return comunaRepository.findAll();

}
@GetMapping("/{id}")
public Comuna BUcsarComunaPorID(@PathVariable Integer id){
    return comunaRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(
    HttpStatus.NOT_FOUND,
    "No se encontro"

    ));
}

@PostMapping
public ResponseEntity<Comuna> ActualizarComuna(@RequestBody Comuna comuna){
    Comuna NuevaComuna = comunaRepository.save(comuna);
    return ResponseEntity.ok(NuevaComuna);

}


}
