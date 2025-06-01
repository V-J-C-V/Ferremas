package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/Producto")
public class ProductoController {
    @Autowired
  private ProductoRepository productoRepository;
    @GetMapping 
    public List<Producto> ListarProductos(){

       return productoRepository.findAll();
    }


}
