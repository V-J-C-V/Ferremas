package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Categoria;
import com.example.demo.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.GetMapping;
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
    





}
