package com.example.demo.controller;

import java.net.http.HttpClient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Categoria;
import com.example.demo.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/Categoria")
public class CategoriaController {
@Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> ListarCategoria(){
        return categoriaRepository.findAll();
        
    }
    @GetMapping("/{id}")
    public Categoria BuscarCategoriaPorId(@PathVariable Integer id){
        return categoriaRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "No se encontro mamawebo"
        ));


    }
    





}
