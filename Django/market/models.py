from django.db import models

# Create your models here.

class Usuario(models.Model):
    email       = models.CharField(max_length=100, unique=True)
    contrasena   = models.CharField(max_length=50)
    
    def __str__(self):
        return self.email + " " + self.contrasena
   