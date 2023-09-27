# control_stock
Proyecto usando Java 17+ en Spring Boot para crear una API. Se trata de un control de stock
# Inicio
Este es el inicio basico para empezar.
## Modelo ER
Este es el modelo que estariamos utilizando
![alt text](https://raw.githubusercontent.com/progmatt1/control_stock/main/er-model.png?token=GHSAT0AAAAAACGO5MURR57A4EOBQ5KV6GWEZIRSXHQ)
## Crea estas tablas en MySql
La logica aqui es crear distintas tablas en orden y configurar sus respectivas relaciones. Despues en el codigo del proyecto en Java
se da la logica.
### productos
```
CREATE TABLE `productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` text NOT NULL,
  `descripcion` text NOT NULL,
  `precio` float NOT NULL,
  `cantidad` text NOT NULL,
  `fecha_creacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_actualizacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
### movimientos_tipo
```
CREATE TABLE `control_stock`.`movimientos_tipo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` TEXT NOT NULL,
  `descripcion` TEXT NOT NULL,
  PRIMARY KEY (`id`));
```
### depositos
```
CREATE TABLE `control_stock`.`depositos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` TEXT NOT NULL,
  PRIMARY KEY (`id`));
```
### movimientos
```
CREATE TABLE `control_stock`.`movimientos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cantidad` INT NOT NULL,
  `fecha` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `id_movimiento_tipo` INT NOT NULL,
  `id_producto` INT NOT NULL,
  `id_deposito` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_movimiento_tipo_idx` (`id_movimiento_tipo` ASC) VISIBLE,
  INDEX `id_producto_idx` (`id_producto` ASC) VISIBLE,
  INDEX `id_deposito_idx` (`id_deposito` ASC) VISIBLE,
  CONSTRAINT `id_movimiento_tipo`
    FOREIGN KEY (`id_movimiento_tipo`)
    REFERENCES `control_stock`.`movimientos_tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_producto`
    FOREIGN KEY (`id_producto`)
    REFERENCES `control_stock`.`productos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_deposito`
    FOREIGN KEY (`id_deposito`)
    REFERENCES `control_stock`.`depositos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
```

### productos_deposito
```
CREATE TABLE `productos_deposito` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_deposito` int NOT NULL,
  `id_producto` int NOT NULL,
  `cantidad` int NOT NULL,
  `fecha_actualizacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id_deposito_idx` (`id_deposito`),
  KEY `id_producto_idx` (`id_producto`),
  CONSTRAINT `id_deposito_foreign` FOREIGN KEY (`id_deposito`) REFERENCES `depositos` (`id`),
  CONSTRAINT `id_producto_foreign` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
## Inserta estos datos de ejemplo
Estos datos nos va a servir de ejemplo para empezar a tener algo y empezar a trabajar con sus respectivas relaciones y un pequeño bug en productos_deposito para mayor entendimiento (cantidad: -6)

```
--  Inserta datos en la tabla deposito
INSERT INTO depositos(nombre) VALUES ('Deposito A');
INSERT INTO depositos(nombre) VALUES ('Deposito B');
INSERT INTO depositos(nombre) VALUES ('Deposito C');
INSERT INTO depositos(nombre) VALUES ('Deposito D');

--  Inserta datos en la tabla productos. La cantidad es la cantidad general que hay en todos los depositos
INSERT INTO productos(nombre, descripcion, precio, cantidad) VALUES (
'Producto A',
'Esta es la descripcion para el Producto A',
999.99,
15
);
INSERT INTO productos(nombre, descripcion, precio, cantidad) VALUES (
'Producto B',
'Esta es la descripcion para el Producto B',
999.99,
15
);
INSERT INTO productos(nombre, descripcion, precio, cantidad) VALUES (
'Producto C',
'Esta es la descripcion para el Producto B',
999.99,
15
);
INSERT INTO productos(nombre, descripcion, precio, cantidad) VALUES (
'Producto D',
'Esta es la descripcion para el Producto B',
999.99,
15
);

-- Los tipos de movimiento que se pueden realizar. Despues en el proyecto de Java segun el tipo de movimiento se crea la logica
INSERT INTO movimientos_tipo(nombre, descripcion) VALUES(
'Baja',
'Esta es la descripcion de Baja.'
);

INSERT INTO movimientos_tipo(nombre, descripcion) VALUES(
'Subida',
'Esta es la descripcion de Subida.'
);

-- Insertamos algunos datos en la tabla movimientos

-- En el primer ejemplo estamos añadiendo 10 productos 'Producto A' en el deposito 'Deposito B'
INSERT INTO movimientos(cantidad, id_movimiento_tipo, id_producto, id_deposito) VALUES (
10,
2,
1,
2
);
-- En el primer ejemplo estamos añadiendo 14 productos 'Producto A' en el deposito 'Deposito A'
INSERT INTO movimientos(cantidad, id_movimiento_tipo, id_producto, id_deposito) VALUES (
14,
2,
1,
1
);
-- En el primer ejemplo estamos añadiendo 12 productos 'Producto C' en el deposito 'Deposito C'
INSERT INTO movimientos(cantidad, id_movimiento_tipo, id_producto, id_deposito) VALUES (
12,
2,
3,
3
);
-- En el primer ejemplo estamos quitando 6 productos 'Producto D' en el deposito 'Deposito A'
INSERT INTO movimientos(cantidad, id_movimiento_tipo, id_producto, id_deposito) VALUES (
6,
1,
4,
1
);

-- Aqui insertamos de modo de ejemplo como quedaria la cantidad de un producto en un deposito
-- Aqui se añade la logica despues en el codigo de Java
INSERT INTO productos_deposito(id_deposito, id_producto, cantidad) VALUES (
2,
1,
10
);
INSERT INTO productos_deposito(id_deposito, id_producto, cantidad) VALUES (
1,
1,
14
);
INSERT INTO productos_deposito(id_deposito, id_producto, cantidad) VALUES (
3,
3,
12
);
INSERT INTO productos_deposito(id_deposito, id_producto, cantidad) VALUES (
1,
4,
-6
);
```
