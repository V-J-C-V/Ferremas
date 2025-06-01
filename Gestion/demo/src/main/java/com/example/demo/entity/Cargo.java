package com.example.demo.entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cargo")
public class Cargo {
    @Id
private int id_cargo;
private String nombre;
@ManyToOne
@JoinColumn(name = "id_trabajador")
private Trabajador trabajador;
public Cargo() {
}
public Cargo(int id_cargo, String nombre, Trabajador trabajador) {
    this.id_cargo = id_cargo;
    this.nombre = nombre;
    this.trabajador = trabajador;
}
public int getId_cargo() {
    return id_cargo;
}
public void setId_cargo(int id_cargo) {
    this.id_cargo = id_cargo;
}
public String getNombre() {
    return nombre;
}
public void setNombre(String nombre) {
    this.nombre = nombre;
}
public Trabajador getTrabajador() {
    return trabajador;
}
public void setTrabajador(Trabajador trabajador) {
    this.trabajador = trabajador;
}


}
