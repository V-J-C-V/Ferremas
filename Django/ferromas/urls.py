"""
URL configuration for ferromas project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/5.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path

from market import views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', views.login, name="inicio") ,
    path('sistemven/', views.sistemven),
    path('index/', views.inicio, name="inicio") ,
    path('sistemven/', views.sistemven, name="Venta"),
    path('header/', views.header, name="header"),
    path('pago/', views.pago),
    path('agregarUsuario/', views.crearUsuario, name="agregarUsuario"),
    path('agregarProducto/', views.crear, name="agregar"),
    path('index/', views.inicio, name="inicio"), 
     path('bodega/', views.ver_bodega, name="Bodega"),  
    path('catalogo/', views.ver_categoria, name="ver_producto"),
    path('bodega/', views.ver_bodega, name="Bodega"),  
    path('catalogo/', views.ver_categoria, name="catalogo"),
    path('herramientas/', views.ver_productos, name='ver_productos'),
    path('Material/', views.ver_productos_m, name='ver_productosm'),
    path('Piso/', views.ver_productos_p, name='ver_productosp'),
    path("agregar/<str:producto_id>/", views.agregar_producto, name="agregar_producto"),
    path("eliminar/<str:producto_id>/", views.eliminar_producto, name="eliminar_producto"),
    path("decrementar/<str:producto_id>/", views.decrementar_producto, name="decrementar_producto"),
    path("limpiar/", views.limpiar_carrito, name="limpiar_carrito"),
    path("carrito/", views.ver_carrito, name="ver_carrito"),


]
