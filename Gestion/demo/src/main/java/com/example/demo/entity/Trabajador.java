package com.example.demo.entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Trabajador {
    @Id
    private int id_trabajador;
    private String rut;
    private String nombre;
    private String apellido;
    @ManyToOne
    @JoinColumn(name = "id_comuna")
    private Comuna comuna;
    @ManyToOne
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    private Usuario usuario;

    public Trabajador() {
    }

    public Trabajador(int id_trabajador, String rut, String nombre, String apellido, Comuna comuna, Usuario usuario) {
        this.id_trabajador = id_trabajador;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.comuna = comuna;
        this.usuario = usuario;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
