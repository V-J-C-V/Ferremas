from django.shortcuts import render
import requests

def inicio(request):
    return render(request, "index.html")
def obtener_catalogo():
    url = "http://127.0.0.1:8088/api/Trabajador/"
    try:
        response = requests.json(url)
        data = response.json()
        return data
    except  Exception as e:
        return None