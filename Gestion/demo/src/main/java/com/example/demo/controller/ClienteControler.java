package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/Cliente")
public class ClienteControler {
    @Autowired
private ClienteRepository clienteRepository;

@GetMapping
public List<Cliente> listarCliente(){
    return clienteRepository.findAll();
}

@GetMapping("/{id}")
public Cliente BuscarClientePorId(@PathVariable String id){
    return clienteRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "No se enconro el cliente"
    ));
}



}






