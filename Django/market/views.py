from django.shortcuts import render
import requests

def inicio(request):
    return render(request, "index.html")


def sistemven(request):
    return render(request, 'sistemven.html')

def bodegasistem(request):
    return render(request, 'bodegasistem.html' )

def pago(request):
    return render( request)

def obtener_categoria():
    url = "http://127.0.0.1:8088/api/Categoria/"
    try:
        response = requests.json(url)
        data = response.json()
        return data
    except  Exception as e:
        return None

def ver_categoria(request):
    catalogo = obtener_categoria()
    contexto = {"datos":catalogo}
    return render (request, "Ver_catalogo.html", contexto)
