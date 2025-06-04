-- Crear base de datos
CREATE DATABASE bodega;
USE bodega;

-- Tabla 1: Usuario (base de Email)
CREATE TABLE Usuario ( 
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  Email VARCHAR(100) NOT NULL UNIQUE,
  Contrasena VARCHAR(255) NOT NULL
);

-- Tabla 2: Comuna
CREATE TABLE Comuna (
  id_comuna INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL
);

select * from Trabajador;
-- Tabla 3: Trabajador
CREATE TABLE Trabajador (
  id_trabajador INT AUTO_INCREMENT PRIMARY KEY,
  Rut VARCHAR(12) NOT NULL,
  nombre VARCHAR(30) NOT NULL,
  Apellido VARCHAR(30) NOT NULL,
  id_comuna INT NOT NULL,
  Email VARCHAR(100) NOT NULL,
  CONSTRAINT fk_trabajador_comuna FOREIGN KEY (id_comuna) REFERENCES Comuna(id_comuna),
  CONSTRAINT fk_trabajador_usuario FOREIGN KEY (Email) REFERENCES Usuario(Email)
);

-- Tabla 4: Cargo
CREATE TABLE Cargo (
  id_cargo INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(30) NOT NULL,
  id_trabajador INT NOT NULL,
  CONSTRAINT fk_cargo_trabajador FOREIGN KEY (id_trabajador) REFERENCES Trabajador(id_trabajador)
);

-- Tabla 5: Cliente
CREATE TABLE Cliente (
  rut VARCHAR(12) PRIMARY KEY,
  nombre VARCHAR(30) NOT NULL,
  telefono VARCHAR(15) NOT NULL,
  Email VARCHAR(100) NOT NULL,
  id_comuna INT NOT NULL,
  UNIQUE (Email),
  CONSTRAINT fk_cliente_comuna FOREIGN KEY (id_comuna) REFERENCES Comuna(id_comuna),
  CONSTRAINT fk_cliente_usuario FOREIGN KEY (Email) REFERENCES Usuario(Email)
);
select * from Categoria;
describe categoria;
CREATE TABLE Categoria(
  id_categoria int PRIMARY KEY,
  nombre VARCHAR(30) NOT NULL

);

-- Tabla 6: Producto (cambiado a VARCHAR si vas a usar 'P001', etc.)
CREATE TABLE Producto (
  id_producto VARCHAR(10) PRIMARY KEY,
  id_categoria int NOT NULL,
  nombre VARCHAR(30) NOT NULL,
  imagen VARCHAR(2083	) NOT NULL,
  comprado boolean not null,
  
  precio INT NOT NULL,
  stock INT NOT NULL,
  CONSTRAINT fk_categoria_venta FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

-- Tabla 7: Venta

select * from venta;
CREATE TABLE Venta (
  id_documento INT AUTO_INCREMENT PRIMARY KEY,
  fecha DATE NOT NULL,
  rut VARCHAR(12) NOT NULL,
  tipo_doc VARCHAR(2) NOT NULL,
  id_trabajador INT NOT NULL,
  estado VARCHAR(10) NOT NULL,
  CONSTRAINT fk_venta_cliente FOREIGN KEY (rut) REFERENCES Cliente(rut),
  CONSTRAINT fk_venta_trabajador FOREIGN KEY (id_trabajador) REFERENCES Trabajador(id_trabajador)
);

-- Tabla 8: Detalle (no debe tener AUTO_INCREMENT aquí, porque depende de claves externas)
CREATE TABLE Detalle (	
  id_boleta INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  id_producto VARCHAR(10) NOT NULL,
  id_documento INT NOT NULL,
  cantidad INT NOT NULL,
  metodoPago VARCHAR(30) NOT NULL,
  CONSTRAINT fk_detalle_producto FOREIGN KEY (id_producto) REFERENCES Producto(id_producto),
  CONSTRAINT fk_detalle_venta FOREIGN KEY (id_documento) REFERENCES Venta(id_documento)
);

-- Insertar las comunas relacionadas a Santiago de Chile en la tabla Comuna
INSERT INTO `Comuna` (`id_comuna`, `nombre`) VALUES
(1, 'Santiago Centro'),
(2, 'Providencia'),
(3, 'Las Condes'),
(4, 'Ñuñoa'),
(5, 'La Florida');

-- 2. Insertar usuarios
INSERT INTO `Usuario` ( `Email`, `Contrasena`) VALUES
( 'juan.perez@email.com', 'password123'),
( 'ana.gonzalez@email.com', 'password123'),
( 'carlos.ramirez@email.com', 'password123'),
( 'cliente1@email.com', 'password123'),
('cliente2@email.com', 'password123'),
('alberto.sepulveda@email.com', 'password123'),
( 'cliente3@email.com', 'password123');



-- 3. Insertar trabajadores
INSERT INTO `Trabajador` (`id_trabajador`, `Rut`, `nombre`, `Apellido`, `id_comuna`, `Email`) VALUES
(1, '12345678-9', 'Juan', 'Pérez', 1, 'juan.perez@email.com'),
(2, '98765432-1', 'Ana', 'González', 2, 'ana.gonzalez@email.com'),
(3, '45678901-2', 'Carlos', 'Ramírez', 3, 'carlos.ramirez@email.com'),
(4, '15555666-7', 'Alberto', 'Sepulveda', 2, 'alberto.sepulveda@email.com');


-- 4. Insertar clientes
INSERT INTO `Cliente` (`rut`, `nombre`, `telefono`, `Email`, `id_comuna`) VALUES
('11111111-1', 'Pedro López', '987654321', 'cliente1@email.com', 1),
('22222222-2', 'María Fernández', '912345678', 'cliente2@email.com', 2),
('33333333-3', 'José Martínez', '923456789', 'cliente3@email.com', 3);

-- 5. Insertar cargos
INSERT INTO `Cargo` (`id_cargo`, `nombre`, `id_trabajador`) VALUES
(1, 'Bodeguero', 1),
(2, 'Vendedor', 2),
(3, 'Contador', 3);

-- 6_5. Insertar categoria
INSERT INTO `Categoria` (`id_categoria`,`nombre`) VALUES
(1, 'Herramientas'),
(2, 'Material de construccion'),
(3, 'Piso');


-- 6. Insertar productos
INSERT INTO `Producto` (`id_producto`, `id_categoria` ,`nombre`,`imagen`, `precio`, `stock`) VALUES
('P001', 1 ,'Martillo','https://rgm.vtexassets.com/arquivos/ids/156235/Martillo.png?v=638554617786370000', 15000, 100),
('P002', 1 ,'Destornillador','https://res.cloudinary.com/rsc/image/upload/b_rgb:FFFFFF,c_pad,dpr_1.0,f_auto,q_auto,w_700/c_pad,w_700/Y1829689-01', 3000, 200),
('P003', 1 ,'Sierra de Mano','https://media.witglobal.net/source/eshop/stmedia/0100/images/std.lang.all/resolutions/category/576px/528474491.jpg', 8000, 50),
('P004', 1 ,'Taladro Eléctrico','https://media.falabella.com/sodimacCL/7404727_01/w=800,h=800,fit=pad', 45000, 30),
('P005', 2 ,'Pintura Interior','https://casaiberia.vtexassets.com/arquivos/ids/219021-800-800?v=638544612156200000&width=800&height=800&aspect=true', 12000, 80),
('P006', 2, 'Cemento','https://www.polpaico.cl/wp-content/uploads/2023/05/img_cementoAR_HD.png', 7000, 100),
('P007', 2, 'Arena Fina','https://construmartcl.vtexassets.com/arquivos/ids/220262-800-auto?v=638729736729430000&width=800&height=auto&aspect=true', 3500, 150),
('P008', 2, 'Gravilla','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHQJNfyJikBk3axtfZ069zQOd6I23Maofjaw&s', 4000, 120),
('P009', 3, 'Cerámica Beige','https://www.limatco.cl/wp-content/uploads/2022/06/2063Ma.jpg', 11000, 90),
('P010', 3, 'Porcelanato Blanco','https://dojiw2m9tvv09.cloudfront.net/11345/product/milan-4-palmetas9187.jpg', 18000, 70);


select * from Producto;

-- 7. Insertar ventas
INSERT INTO `Venta` (`id_documento`, `fecha`, `rut`, `tipo_doc`, `id_trabajador`, `estado`) VALUES
(1, '2025-05-08', '11111111-1', 'FV', 1, 'Activa'),
(2, '2025-05-08', '22222222-2', 'FV', 2, 'Cerrada'),
(3, '2025-05-08', '33333333-3', 'NV', 3, 'Activa');

-- 8. Insertar detalles de ventas
INSERT INTO `Detalle` (`id_producto`, `id_documento`, `cantidad`, `metodoPago`) VALUES
('P001', 1, 2, 'Efectivo'),
('P002', 1, 1, 'Tarjeta'),
('P003', 2, 3, 'Efectivo'),
('P004', 2, 1, 'Tarjeta'),
('P005', 3, 5, 'Efectivo');


-- Crear el usuario (reemplaza 'Usuario' y '123' por lo que necesites)
CREATE USER 'Usuario'@'localhost' IDENTIFIED BY '123';

-- Otorgar todos los privilegios sobre todas las bases de datos
GRANT ALL PRIVILEGES ON *.* TO 'Usuario'@'localhost' WITH GRANT OPTION;

-- Aplicar los cambios
FLUSH PRIVILEGES;