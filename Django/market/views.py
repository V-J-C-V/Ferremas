from django.shortcuts import get_object_or_404, redirect, render
import requests
from django.contrib.auth.decorators import login_required
from django.http import Http404
from carrito.cart import Cart
@login_required
def inicio(request):
    return render(request, "index.html")


def sistemven(request):
    return render(request, 'sistemven.html')



def pago(request):
    return render( request)

def obtener_categoria():
    url = "http://localhost:8088/api/Categoria"
    try:
        response = requests.get(url)
        data = response.json()
        return data
    except  Exception as e:
        return None
    
def obtener_Detalle():
    url = "http://localhost:8088/api/Detalle"
    try:
        response = requests.get(url)
        data = response.json()
        return data
    except  Exception as e:
        return None
    
def obtener_producto_por_id(producto_id):
    productos = obtener_productos()
    for producto in productos:
        if producto["id_producto"] == producto_id:
            return producto
    return None
    
def ver_categoria(request):
    catalogo = obtener_categoria()
    contexto = {"datos":catalogo}
    return render (request, "Ver_catalogo.html", contexto)

def obtener_productos():
    url = "http://localhost:8088/api/Producto"
    try:
        response = requests.get(url)
        data = response.json()
        return data
    except Exception as e:
        return None
    



def ver_productos(request):
    producto = obtener_productos()
    contexto = {"datos":producto}
    return render (request, "Herramientas.html", contexto)

def ver_bodega(request):
    producto = obtener_Detalle()
    contexto = {"datos":producto}
    return render (request, "bodegasistem.html", contexto)

def ver_productos_m(request):
    producto = obtener_productos()
    contexto = {"datos":producto}
    return render (request, "MaterialConstruccion.html", contexto)

def ver_productos_p(request):
    producto = obtener_productos()
    contexto = {"datos":producto}
    return render (request, "piso.html", contexto)


# Aca se encuentran las funciones del carrito

def agregar_producto(request, producto_id):
    carrito = Cart(request)
    producto = obtener_producto_por_id(producto_id)
    if producto is None:
        from django.http import Http404
        raise Http404("Producto no encontrado")
    carrito.add(producto)
    return redirect("ver_carrito")




def eliminar_producto(request, producto_id):
    carrito = Cart(request)
    producto = obtener_producto_por_id(producto_id)
    if producto is None:
        raise Http404("Producto no encontrado")
    carrito.remove(producto)
    return redirect("ver_carrito")
def decrementar_producto(request, producto_id):
    cart = Cart(request)
    producto = obtener_producto_por_id(producto_id)  # Aquí obtienes el producto
    if producto is None:
      
        raise Http404("Producto no encontrado")
    cart.decrement(producto)
    return redirect("ver_carrito")

def limpiar_carrito(request):
    cart = Cart(request)
    cart.clear()
    return redirect("ver_carrito")

def ver_carrito(request):
    cart = Cart(request)
    return render(request, "carrito.html", {"cart": cart.cart})





