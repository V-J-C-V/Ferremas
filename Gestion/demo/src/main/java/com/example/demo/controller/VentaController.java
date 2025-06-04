package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Venta;
import com.example.demo.repository.VentaRepository;
@RestController
@RequestMapping("/api/Venta")
public class VentaController {
    @Autowired
    private VentaRepository ventaRepository;
    @GetMapping
    public List<Venta> ListarVentas(){
        return ventaRepository.findAll();
    }
    @GetMapping("/{id}")
    public Venta BuscarVentaPorId(@PathVariable Integer id){
        return ventaRepository.findById(id)
        .orElseThrow(()-> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "No encontrado"
        ));
    }
    @PostMapping
    public ResponseEntity<Venta> ActualizarVenta(@RequestBody Venta venta){
        Venta NuevaVenta = ventaRepository.save(venta);
        return ResponseEntity.ok(NuevaVenta);
    }
@DeleteMapping("/{id}")
public ResponseEntity<Map<String, String>> EliminarVenta(@PathVariable Integer id){
    if (!ventaRepository.existsById(id)) {
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "No encontrado"
        );

    }
    ventaRepository.deleteById(id);
    Map<String, String> respuesta = new HashMap();
    respuesta.put("Mensaje", "Cliente eliminado");
    respuesta.put("Id venta", id.toString());
    return ResponseEntity.ok(respuesta);
    
    
}

}
