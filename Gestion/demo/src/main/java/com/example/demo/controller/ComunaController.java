package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Comuna;
import com.example.demo.repository.ComunaRepository;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/Comuna")


public class ComunaController {
@Autowired
private ComunaRepository comunaRepository;
@GetMapping
public List<Comuna> ListarComuna(){
    return comunaRepository.findAll();

}


}
