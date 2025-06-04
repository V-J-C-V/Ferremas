from django.shortcuts import render

def inicio(request):
    return render(request, "index.html")


def sistemven(request):
    return render(request, 'sistemven.html')

def bodegasistem(request):
    return render(request, 'bodegasistem.html' )

def pago(request):
    return render( request)

