package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Cargo;
import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



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

   @PostMapping
    public ResponseEntity<Cliente> AgregarCliente(@RequestBody Cliente cliente){
        Cliente  clienteGuardado = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteGuardado);
    }

@PutMapping("/{id}")
public ResponseEntity<Cliente> ActualizarCliente(@PathVariable String id, @RequestBody Cliente clienteactualizar){

    Cliente clientexistente = clienteRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(

    HttpStatus.NOT_FOUND,
    "No se encontro"
    ));


    clientexistente.setNombre(clienteactualizar.getNombre());
    clientexistente.setComuna(clienteactualizar.getComuna());
    clientexistente.setRut(clienteactualizar.getRut());
    clientexistente.setTelefono(clienteactualizar.getTelefono());
    clientexistente.setUsuario(clienteactualizar.getUsuario());
    Cliente clienteGuardado = clienteRepository.save(clientexistente);
    return ResponseEntity.ok(clienteGuardado);
}



}






