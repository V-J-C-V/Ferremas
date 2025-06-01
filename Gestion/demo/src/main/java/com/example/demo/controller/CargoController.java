package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cargo;
import com.example.demo.repository.CargoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/Cargo")
public class CargoController {
    @Autowired
    private CargoRepository cargoRepository;
    @GetMapping
    public List<Cargo> ListarCargo(){
        return cargoRepository.findAll();

    }


}
