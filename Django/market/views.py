from django.contrib import messages
from django.shortcuts import get_object_or_404, redirect, render
import requests
from django.contrib.auth.decorators import login_required
from django.http import Http404
from market.form import ProductoForm
from carrito.cart import Cart
@login_required
def inicio(request):
    return render(request, "index.html")


def sistemven(request):
    return render(request, 'sistemven.html')



def pago(request):
    return render( request)
def obtener_trabajador():
    url = "http://localhost:8088/api/Trabajador"
    try:
        response = requests.get(url)
        data = response.json()
        return data
    except  Exception as e:
        return None
def obtener_usuario():
    url = "http://localhost:8088/api/Usuario"
    try:
        response = requests.get(url)
        data = response.json()
        return data
    except  Exception as e:
        return None
def login(request):
    if request.method == 'POST':
        correo = request.POST.get('email')
        contrasena = request.POST.get('contrasena')

        try:
            response = requests.get('http://localhost:8088/api/Usuario')
            usuarios = response.json()

            for usuario in usuarios:
                if usuario['email'] == correo and usuario['contrasena'] == contrasena:
                    request.session['usuario_id'] = usuario['id_usuario']
                    request.session['usuario_email'] = usuario['email']  # Guardar correo en sesión
                    return redirect('verCategoria')

            messages.error(request, 'Correo o contraseña incorrectos.')
        except Exception as e:
            messages.error(request, f'Error al conectarse con el servidor: {e}')

    return render(request, 'index.html')



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
    


def crear(request):
    if request.method == "POST":
        productoform = ProductoForm(request.POST)
        if productoform.is_valid():
            datos = productoform.cleaned_data

            payload = {
                "id_producto": datos["id_producto"],
                "categoria": {
                    "id_categoria": int(datos["categoria"])  # Aseguramos que sea int
                },
                "nombre": datos["nombre"],
                "imagen": datos["imagen"],
                "precio": datos["precio"],
                "stock": datos["stock"]
            }

            try:
                response = requests.post("http://localhost:8088/api/Producto", json=payload)
                if response.status_code in [200, 201]:
                    return redirect('ver_producto')
                else:
                    productoform.add_error(None, "Error en la API: " + str(response.status_code))
            except Exception as e:
                productoform.add_error(None, f"Error de conexión: {e}")
    else:
        productoform = ProductoForm()

    return render(request, 'agregarProducto.html', {'productoform': productoform})

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





