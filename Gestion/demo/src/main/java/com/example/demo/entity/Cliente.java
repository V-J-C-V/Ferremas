package com.example.demo.entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Table(name = "Cliente")
@Entity
public class Cliente {
    @Id
    private String rut;
    private String nombre;
    private String  telefono;
    @ManyToOne
    @JoinColumn(name = "id_comuna")
    private Comuna comuna;
    @ManyToOne
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    private Usuario usuario;
    public Cliente() {
    }
    public Cliente(String rut, String nombre, String telefono, Comuna comuna, Usuario usuario) {
        this.rut = rut;
        this.nombre = nombre;
        this.telefono = telefono;
        this.comuna = comuna;
        this.usuario = usuario;
    }
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Comuna getComuna() {
        return comuna;
    }
    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    

}
