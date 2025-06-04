package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Detalle;
import com.example.demo.repository.DetalleRepository;
@RestController
@RequestMapping("/api/Detalle")
public class DetalleController {
    @Autowired
    private DetalleRepository detalleRepository;
    @GetMapping
    public List<Detalle> ListarDetalle(){
        
        return detalleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Detalle BuscarDetallePorId(@PathVariable Integer id){
        return detalleRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "No se encuentra"
        ));
    }

}
