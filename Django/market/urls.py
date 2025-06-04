from django.contrib import admin
from django.urls import path
from . import views


urlpatterns = [
    path('index/', views.inicio, name="inicio"),
    path('sistemven/', views.sistemven),
    path('bodegasistem/', views.bodegasistem),
    path('pago/', views.pago),
]
    