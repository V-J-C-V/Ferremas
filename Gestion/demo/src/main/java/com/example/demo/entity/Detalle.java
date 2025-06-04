package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Detalle {
@Id
private int id_boleta;
@ManyToOne
@JoinColumn(name = "id_producto")
private Producto producto;
@ManyToOne
@JoinColumn(name = "id_documento")  
private Venta venta;
private int cantidad;
private String metodoPago;
public Detalle() {
}
public int getId_boleta() {
    return id_boleta;
}
public void setId_boleta(int id_boleta) {
    this.id_boleta = id_boleta;
}
public Producto getProducto() {
    return producto;
}
public void setProducto(Producto producto) {
    this.producto = producto;
}
public Venta getVenta() {
    return venta;
}
public void setVenta(Venta venta) {
    this.venta = venta;
}
public int getCantidad() {
    return cantidad;
}
public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
}
public String getMetodoPago() {
    return metodoPago;
}
public void setMetodoPago(String metodoPago) {
    this.metodoPago = metodoPago;
}

}
