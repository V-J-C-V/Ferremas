from django.contrib import messages
from django.shortcuts import get_object_or_404, redirect, render
import requests
from django.contrib.auth.decorators import login_required
from django.http import Http404
from market.form import ProductoForm, Usuariform
from carrito.cart import Cart
@login_required
def inicio(request):
    return render(request, "index.html")


def sistemven(request):
    return render(request, 'sistemven.html')



def crearUsuario(request):
    if request.method == "POST":
        usuarioform = Usuariform(request.POST)
        if usuarioform.is_valid():
            datos = usuarioform.cleaned_data

            payload = {
                "email": datos["email"],
                "contrasena": datos["contrasena"],
            }

            try:
                response = requests.post(
                    "http://localhost:8088/api/Usuario",
                    json=payload
                )
                if response.status_code in [200, 201]:
                    data = response.json()
                    if data.get("id_usuario", 0) > 0:
                        return redirect("inicio")  # Redirige si todo salió bien
                    else:
                        error = "La API respondió pero el usuario no fue creado correctamente (ID inválido)."
                else:
                    error = f"Error en la API: {response.status_code} - {response.text}"
            except requests.exceptions.RequestException as e:
                error = f"Error de conexión: {e}"

            return render(request, "agregarUsuario.html", {"form": usuarioform, "error": error})
        else:
            return render(request, "agregarUsuario.html", {"form": usuarioform})
    else:
        usuarioform = Usuariform()
        return render(request, "agregarUsuario.html", {"form": usuarioform})




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
                    return redirect('ver_producto')

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
def obtener_Cargo():
    url = "http://localhost:8088/api/Cargo"
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
    usuario = obtener_usuario()
    trabajador = obtener_trabajador()
    producto = obtener_productos()
    cargo = obtener_Cargo()
    contexto = {"datos":producto,"us":usuario,"trab":trabajador,"car":cargo}    
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
                    "id_categoria": int(datos["categoria"])
                },
                "nombre": datos["nombre"],
                "imagen": datos["imagen"],
                "precio": datos["precio"],
                "stock": datos["stock"]
            }

            try:
                response = requests.post(
                    "http://localhost:8088/api/Producto",
                    json=payload
                )
                if response.status_code == 201:  # o 200 según tu API
                    return redirect("nombre_de_la_vista_exito")  # redirige si todo salió bien
                else:
                    error = f"Error en la API: {response.status_code} - {response.text}"
            except requests.exceptions.RequestException as e:
                error = f"Error de conexión: {e}"

            return render(request, "agregarProducto.html", {"form": productoform, "error": error})
    else:
        productoform = ProductoForm()



    return render(request, 'agregarProducto.html', {'productoform': productoform})

def ver_productos(request):
    usuario = obtener_usuario()
    trabajador = obtener_trabajador()
    producto = obtener_productos()
    contexto = {"datos":producto,"us":usuario,"trab":trabajador}

    return render (request, "Herramientas.html", contexto)

def ver_bodega(request):
    usuario = obtener_usuario()
    trabajador = obtener_trabajador()
    producto = obtener_productos()
    cargo = obtener_Cargo()
    contexto = {"datos":producto,"us":usuario,"trab":trabajador,"car":cargo}    
    return render (request, "bodegasistem.html", contexto)

def ver_productos_m(request):
    usuario = obtener_usuario()
    trabajador = obtener_trabajador()
    producto = obtener_productos()
    cargo = obtener_Cargo()
    contexto = {"datos":producto,"us":usuario,"trab":trabajador,"car":cargo}    
    return render (request, "MaterialConstruccion.html", contexto)

def ver_productos_p(request):
    usuario = obtener_usuario()
    trabajador = obtener_trabajador()
    producto = obtener_productos()
    cargo = obtener_Cargo()
    contexto = {"datos":producto,"us":usuario,"trab":trabajador,"car":cargo}    
    return render (request, "piso.html", contexto)
def header(request):
    usuario = obtener_usuario()
    trabajador = obtener_trabajador()
    producto = obtener_productos()
    cargo = obtener_Cargo()
    contexto = {"datos":producto,"us":usuario,"trab":trabajador,"car":cargo}    
    return render (request, "header.html", contexto)

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





