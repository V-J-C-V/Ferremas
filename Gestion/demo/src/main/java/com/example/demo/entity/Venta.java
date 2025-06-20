package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Venta")
public class Venta {
    @Id
private int id_documento;
private LocalDate fecha;
@ManyToOne
@JoinColumn(name = "rut")
private Cliente cliente;


private String tipo_doc;
private int total;


@ManyToOne
@JoinColumn(name= "id_trabajador")
private Trabajador trabajador;
private String estado;
public Venta() {
}
public Venta(int id_documento, LocalDate fecha, Cliente cliente, String tipo_doc, int total, Trabajador trabajador,
        String estado) {
    this.id_documento = id_documento;
    this.fecha = fecha;
    this.cliente = cliente;
    this.tipo_doc = tipo_doc;
    this.total = total;
    this.trabajador = trabajador;
    this.estado = estado;
}
public int getId_documento() {
    return id_documento;
}
public void setId_documento(int id_documento) {
    this.id_documento = id_documento;
}
public LocalDate getFecha() {
    return fecha;
}
public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
}
public Cliente getCliente() {
    return cliente;
}
public void setCliente(Cliente cliente) {
    this.cliente = cliente;
}
public String getTipo_doc() {
    return tipo_doc;
}
public void setTipo_doc(String tipo_doc) {
    this.tipo_doc = tipo_doc;
}
public int getTotal() {
    return total;
}
public void setTotal(int total) {
    this.total = total;
}
public Trabajador getTrabajador() {
    return trabajador;
}
public void setTrabajador(Trabajador trabajador) {
    this.trabajador = trabajador;
}
public String getEstado() {
    return estado;
}
public void setEstado(String estado) {
    this.estado = estado;
}


}