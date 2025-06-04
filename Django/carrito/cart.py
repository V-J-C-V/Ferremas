class Cart:
    def __init__(self, request):
        self.request = request
        self.session = request.session
        cart = self.session.get("cart")
        if not cart:
            cart = self.session["cart"] = {}
        self.cart = cart

    def add(self, producto):
        producto_id = producto["id_producto"]

        if producto_id not in self.cart.keys():
            self.cart[producto_id] = {
                "producto_id": producto["id_producto"],
                "nombre": producto["nombre"],
                "cantidad": 1,
                "precio": str(producto["precio"]),
                "imagen": producto["imagen"]  # si es una URL completa
            }
        else:
            self.cart[producto_id]["cantidad"] += 1

        self.save()

    def save(self):
        self.session["cart"] = self.cart
        self.session.modified = True

    def remove(self, producto):
        producto_id = producto["id_producto"]
        if producto_id in self.cart:
            del self.cart[producto_id]
            self.save()

    def decrement(self, producto):
        producto_id = producto["id_producto"]
        if producto_id in self.cart:
            self.cart[producto_id]["cantidad"] -= 1
            if self.cart[producto_id]["cantidad"] < 1:
                self.remove(producto)
            else:
                self.save()

    def clear(self):
        self.session["cart"] = {}
        self.session.modified = True
