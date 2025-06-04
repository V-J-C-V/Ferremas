def usuario_email(request):
    return {
        'usuario_email': request.session.get('usuario_email', None)
    }