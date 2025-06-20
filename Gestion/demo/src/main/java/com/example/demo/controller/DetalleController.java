package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Detalle;
import com.example.demo.repository.DetalleRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    @PostMapping
    public ResponseEntity<Detalle> ActualizarDetalle(@RequestBody Detalle detalle){
        Detalle nuevoDetalle = detalleRepository.save(detalle);
        return ResponseEntity.ok(nuevoDetalle);
    }  


    @PutMapping("/{id}")
public ResponseEntity<Detalle> DetallePut(@PathVariable int id, @RequestBody Detalle detalleactualizar){

    Detalle detalleExistente = detalleRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(

    HttpStatus.NOT_FOUND,
    "No se encontro"
    ));


    detalleExistente.setCantidad(detalleactualizar.getCantidad());
    detalleExistente.setId_boleta(detalleactualizar.getId_boleta());
    detalleExistente.setMetodoPago(detalleactualizar.getMetodoPago());
    detalleExistente.setProducto(detalleactualizar.getProducto());
    detalleExistente.setTrabajador(detalleactualizar.getTrabajador());
    detalleExistente.setVenta(detalleactualizar.getVenta());
    

    Detalle detalleGuardado = detalleRepository.save(detalleExistente);
    return ResponseEntity.ok(detalleGuardado);
}

}
