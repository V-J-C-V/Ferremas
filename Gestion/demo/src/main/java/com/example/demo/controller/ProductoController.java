package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

@DeleteMapping("/{id}")
public ResponseEntity<Map<String, String>> eliminarProducto(@PathVariable String id){
    if (!productoRepository.existsById(id)) {
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "No encontrado"
        ); 
        
        
    }
    productoRepository.deleteById(id);
    Map<String, String> respuesta = new HashMap();
    respuesta.put("Mensaje", "Producto eliminado");
    respuesta.put("Id producto", id.toString());
    return ResponseEntity.ok(respuesta);
    
    
}
      

@PutMapping("/{id}")
public ResponseEntity<Producto> ProductoPut(@PathVariable String id, @RequestBody Producto productoactualizar){

    Producto productoExistente = productoRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(

    HttpStatus.NOT_FOUND,
    "No se encontro"
    ));


  productoExistente.setCategoria(productoactualizar.getCategoria());
  productoExistente.setId_producto(productoactualizar.getId_producto());
  productoExistente.setImagen(productoactualizar.getImagen());
  productoExistente.setMarca(productoactualizar.getMarca());
  productoExistente.setNombre(productoactualizar.getNombre());
  productoExistente.setPrecio(productoactualizar.getPrecio());
  productoExistente.setStock(productoactualizar.getStock());

    Producto productoGuardado = productoRepository.save(productoExistente);
    return ResponseEntity.ok(productoGuardado);
}




      
    }



