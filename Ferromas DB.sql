CREATE TABLE `Comuna` (
  `idComuna` integer PRIMARY KEY,
  `nombre` varchar2(15) NOT NULL
);

CREATE TABLE `Cliente` (
  `rut` varchar2(12) PRIMARY KEY,
  `nombre` varchar2(30) NOT NULL,
  `telefono` varchar2(15) NOT NULL,
  `Email` Varchar2(30) NOT NULL,
  `idComuna` integer NOT NULL
);

CREATE TABLE `Producto` (
  `idProducto` varchar2(30) PRIMARY KEY,
  `nombre` varchar2(30) NOT NULL,
  `precio` integer NOT NULL,
  `stock` integer NOT NULL
);

CREATE TABLE `Venta` (
  `idDocumento` integer PRIMARY KEY,
  `fecha` date NOT NULL,
  `rutCliente` varchar2(12) NOT NULL,
  `tipoDoc` varchar2(2) NOT NULL,
  `idTrabajador` integer(10) NOT NULL,
  `estado` Varchar(10) NOT NULL
);

CREATE TABLE `Detalle` (
  `idProducto` varchar2(30) NOT NULL,
  `idDocumento` integer NOT NULL,
  `cantidad` integer NOT NULL,
  `metodoPago` varchar2(30) NOT NULL
);

CREATE TABLE `Trabajador` (
  `idTrabajador` integer(10),
  `Rut` Varchar2(12),
  `Nombre` Varchar2(30) NOT NULL,
  `Apellido` Varchar2(30) NOT NULL,
  `idComuna` Integer(10) NOT NULL,
  `Email` Varchar2(30) NOT NULL,
  PRIMARY KEY (`idTrabajador`, `Rut`)
);

CREATE TABLE `Cargo` (
  `idCargo` integer(10) PRIMARY KEY,
  `Nombre` Varchar2(30) NOT NULL,
  `idTrabajador` Integer(10) NOT NULL
);

CREATE TABLE `Usuario` (
  `idUsuario` Interger(10) PRIMARY KEY,
  `Email` Varchar2(30) NOT NULL,
  `Contraseña` varchar2(30) NOT NULL
);

ALTER TABLE `Cliente` ADD CONSTRAINT `Cliente` FOREIGN KEY (`idComuna`) REFERENCES `Comuna` (`idComuna`);

ALTER TABLE `Venta` ADD CONSTRAINT `Cliente` FOREIGN KEY (`rutCliente`) REFERENCES `Cliente` (`rut`);

ALTER TABLE `Detalle` ADD CONSTRAINT `Venta` FOREIGN KEY (`idDocumento`) REFERENCES `Venta` (`idDocumento`);

ALTER TABLE `Detalle` ADD CONSTRAINT `Producto` FOREIGN KEY (`idProducto`) REFERENCES `Producto` (`idProducto`);

ALTER TABLE `Cargo` ADD CONSTRAINT `Trabajador` FOREIGN KEY (`idTrabajador`) REFERENCES `Trabajador` (`idTrabajador`);

ALTER TABLE `Trabajador` ADD CONSTRAINT `Comuna` FOREIGN KEY (`idComuna`) REFERENCES `Comuna` (`idComuna`);

ALTER TABLE `Venta` ADD CONSTRAINT `Trabajador` FOREIGN KEY (`idTrabajador`) REFERENCES `Trabajador` (`idTrabajador`);

ALTER TABLE `Cliente` ADD CONSTRAINT `Usuario` FOREIGN KEY (`Email`) REFERENCES `Usuario` (`Email`);

ALTER TABLE `Trabajador` ADD CONSTRAINT `Usuario` FOREIGN KEY (`Email`) REFERENCES `Usuario` (`Email`);
