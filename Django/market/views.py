from django.shortcuts import redirect, render
import requests
from django.contrib.auth.decorators import login_required
@login_required
def inicio(request):
    return render(request, "index.html")


def sistemven(request):
    return render(request, 'sistemven.html')

def bodegasistem(request):
    return render(request, 'bodegasistem.html' )

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

def ver_categoria(request):
    catalogo = obtener_categoria()
    contexto = {"datos":catalogo}
    return render (request, "Ver_catalogo.html", contexto)

# aca se supone que va la wea pa redirecionar


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

def ver_productos_m(request):
    producto = obtener_productos()
    contexto = {"datos":producto}
    return render (request, "MaterialConstruccion.html", contexto)

def ver_productos_p(request):
    producto = obtener_productos()
    contexto = {"datos":producto}
    return render (request, "piso.html", contexto)





