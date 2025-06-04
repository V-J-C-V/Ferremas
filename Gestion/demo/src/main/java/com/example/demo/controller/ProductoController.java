package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/{id}")
    public Producto BuscarProductoPorId(@PathVariable String id){
      return productoRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(
      HttpStatus.NOT_FOUND,
      "No se encontro"

      ));}
      @PostMapping
      public ResponseEntity<Producto> ActualizarProducto(@RequestBody Producto producto){
        Producto NuevoProducto = productoRepository.save(producto);
        return ResponseEntity.ok(NuevoProducto);

      }

      
      
      
    }



