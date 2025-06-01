package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {

}
