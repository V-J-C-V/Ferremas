package com.example.demo.controller;

import java.util.List;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Cargo;
import com.example.demo.repository.CargoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/Cargo")
public class CargoController {

    private final ClienteRepository clienteRepository;
    @Autowired
    private CargoRepository cargoRepository;

    CargoController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @GetMapping
    public List<Cargo> ListarCargo(){
        return cargoRepository.findAll();

    }

    @GetMapping("/{id}")
    public Cargo BuscarCargoPorId(@PathVariable Integer id){
        return cargoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(

        HttpStatus.NOT_FOUND,
        "No se encontro"

        ));


    }

    @PostMapping
    public ResponseEntity<Cargo> ActualizarCargo(@RequestBody Cargo cargo){
        Cargo  cargoGuardado = cargoRepository.save(cargo);
        return ResponseEntity.ok(cargoGuardado);
    }

}
