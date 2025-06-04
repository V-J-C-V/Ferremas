from market.views import obtener_Cargo, obtener_productos, obtener_trabajador, obtener_usuario


def usuario_email(request):
    return {
        'usuario_email': request.session.get('usuario_email', None),
           
        'us': obtener_usuario(),
        'trab': obtener_trabajador(),
        'car': obtener_Cargo(),
        'datos': obtener_productos()
    
    }