package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Comuna")
public class Comuna {
@Id
private int id_comuna;
private String nombre;

public Comuna(){

}


public Comuna(int id_comuna, String nombre) {
    this.id_comuna = id_comuna;
    this.nombre = nombre;
}
public int getId_comuna() {
    return id_comuna;
}
public void setId_comuna(int id_comuna) {
    this.id_comuna = id_comuna;
}
public String getNombre() {
    return nombre;
}
public void setNombre(String nombre) {
    this.nombre = nombre;
}


}
