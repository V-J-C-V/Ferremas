from django import forms

class ProductoForm(forms.Form):
    id_producto = forms.CharField(
        label='ID Producto',
        max_length=10,
        widget=forms.TextInput(attrs={'class': 'form-control'})
    )
    nombre = forms.CharField(
        label='Nombre',
        max_length=100,
        widget=forms.TextInput(attrs={'class': 'form-control'})
    )
    precio = forms.IntegerField(
        label='Precio',
        widget=forms.NumberInput(attrs={'class': 'form-control', 'min': 0})
    )
    stock = forms.IntegerField(
        label='Stock',
        widget=forms.NumberInput(attrs={'class': 'form-control', 'min': 0})
    )
    imagen = forms.URLField(
        label='URL de la imagen',
        widget=forms.URLInput(attrs={'class': 'form-control'})
        )

        
        
    categoria = forms.ChoiceField(
        label='Categoría',
        choices=[
            (1, 'Herramientas'),
            (2, 'Material de construcción'),
            (3, 'Piso')
        ],
        widget=forms.Select(attrs={'class': 'form-select'})
    )


class Usuariform(forms.Form):
        email = forms.CharField(         
        label='email',
         max_length=20,
         widget=forms.TextInput(attrs={'class': 'form-control'}))

    
        

        contrasena = forms.CharField(
            label='contrasena',
            max_length=100,
            widget=forms.TextInput(attrs={'class': 'form-control'})
        )