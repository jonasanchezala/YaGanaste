# Ejercicio 2

``` sql
CREATE TABLE PROVINCIAS (
    IDPROVINCIA INT PRIMARY KEY,
    DESCRIPCION VARCHAR(50) NOT NULL
);

INSERT INTO PROVINCIAS (IDPROVINCIA, DESCRIPCION) VALUES (1,  'Zaragoza');
INSERT INTO PROVINCIAS (IDPROVINCIA, DESCRIPCION) VALUES (2,  'Huesca');
INSERT INTO PROVINCIAS (IDPROVINCIA, DESCRIPCION) VALUES (3,  'Teruel');

CREATE TABLE PRODUCTOS (
    IDPRODUCTO VARCHAR(1) PRIMARY KEY,
    DESCRIPCION VARCHAR(50) NOT NULL,
    PRECIO FLOAT(50) NOT NULL
);

INSERT INTO PRODUCTOS (IDPRODUCTO, DESCRIPCION, PRECIO) VALUES ('A',  'Playmobil', 5.0);
INSERT INTO PRODUCTOS (IDPRODUCTO, DESCRIPCION, PRECIO) VALUES ('B',  'Puzzle', 10.25);
INSERT INTO PRODUCTOS (IDPRODUCTO, DESCRIPCION, PRECIO) VALUES ('C',  'Peonza', 3.65);

CREATE TABLE CLIENTES (
    IDCLIENTE INT PRIMARY KEY,
    NOMBRE VARCHAR(50) NOT NULL,
    IDPROVINCIA INT NOT NULL,
  FOREIGN KEY (IDPROVINCIA)
        REFERENCES PROVINCIAS(IDPROVINCIA)
);

INSERT INTO CLIENTES (IDCLIENTE, NOMBRE, IDPROVINCIA) VALUES (1,  'Juan Palomo', 1);
INSERT INTO CLIENTES (IDCLIENTE, NOMBRE, IDPROVINCIA) VALUES (2,  'Armando Ruido', 2);
INSERT INTO CLIENTES (IDCLIENTE, NOMBRE, IDPROVINCIA) VALUES (3,  'Carmelo Cotón', 1);
INSERT INTO CLIENTES (IDCLIENTE, NOMBRE, IDPROVINCIA) VALUES (4,  'Dolores Fuertes', 3);
INSERT INTO CLIENTES (IDCLIENTE, NOMBRE, IDPROVINCIA) VALUES (5,  'Alberto Mate', 3);

CREATE TABLE COMPRAS (
    IDCOMPRA INT PRIMARY KEY,
    IDCLIENTE INT NOT NULL,
    IDPRODUCTO VARCHAR(1) NOT NULL,
    FECHA DATE NOT NULL,
  FOREIGN KEY (IDCLIENTE)
        REFERENCES CLIENTES(IDCLIENTE),
  FOREIGN KEY (IDPRODUCTO)
        REFERENCES PRODUCTOS(IDPRODUCTO)
);

INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (1, 1, 'C', '2022-01-01');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (2, 2,'B' , '2022-01-15');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (3, 2, 'C', '2022-01-22');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (4, 4,'A' , '2022-02-03');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (5, 3,'A', '2022-02-05');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (6, 1,'B', '2022-02-16');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (7, 1,'B', '2022-02-21');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (8, 4, 'A', '2022-02-21');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (9, 5, 'C','2022-03-01');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (10, 3,'A' , '2022-03-01');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (11, 3,'C', '2022-03-05');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (12, 2,'B', '2022-03-07');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (13, 2,'B', '2022-03-11');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (14, 1,'A', '2022-03-18');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (15, 1,'C', '2022-03-29');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (16, 5,'B', '2022-04-08');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (17, 5, 'C', '2022-04-09');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (18, 4, 'C', '2022-04-09');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (19, 1, 'A', '2022-04-12');
INSERT INTO COMPRAS (IDCOMPRA, IDCLIENTE, IDPRODUCTO, FECHA) VALUES (20, 2,'A', '2022-04-19');
```

``` sql
/*1. Todas las compras detalladas con los datos del cliente, de los productos y de cada una de las
ventas (código de cliente, nombre de cliente, nombre de provincia, producto, importe, fecha
de la venta)*/

SELECT CLIENTES.IDCLIENTE AS CODIGO_CLIENTE, CLIENTES.NOMBRE AS NOMBRE_CLIENTE, PROVINCIAS.DESCRIPCION AS NOMBRE_PROVINCIA, 
  PRODUCTOS.DESCRIPCION AS PRODUCTO, PRODUCTOS.PRECIO AS IMPORTE, COMPRAS.FECHA AS FECHA_VENTA FROM COMPRAS 
  LEFT JOIN CLIENTES ON COMPRAS.IDCLIENTE = CLIENTES.IDCLIENTE
  LEFT JOIN PRODUCTOS ON COMPRAS.IDPRODUCTO =PRODUCTOS.IDPRODUCTO
  LEFT JOIN PROVINCIAS ON CLIENTES.IDPROVINCIA =PROVINCIAS.IDPROVINCIA;
```
| CODIGO\_CLIENTE | NOMBRE\_CLIENTE | NOMBRE\_PROVINCIA | PRODUCTO | IMPORTE | FECHA\_VENTA |
|---------------:|:---------------|:-----------------|:---------|--------:|:------------|
| 1 | Juan Palomo | Zaragoza | Peonza | 3.65 | 2022-01-01 |
| 2 | Armando Ruido | Huesca | Puzzle | 10.25 | 2022-01-15 |
| 2 | Armando Ruido | Huesca | Peonza | 3.65 | 2022-01-22 |
| 4 | Dolores Fuertes | Teruel | Playmobil | 5 | 2022-02-03 |
| 3 | Carmelo Cotón | Zaragoza | Playmobil | 5 | 2022-02-05 |
| 1 | Juan Palomo | Zaragoza | Puzzle | 10.25 | 2022-02-16 |
| 1 | Juan Palomo | Zaragoza | Puzzle | 10.25 | 2022-02-21 |
| 4 | Dolores Fuertes | Teruel | Playmobil | 5 | 2022-02-21 |
| 5 | Alberto Mate | Teruel | Peonza | 3.65 | 2022-03-01 |
| 3 | Carmelo Cotón | Zaragoza | Playmobil | 5 | 2022-03-01 |
| 3 | Carmelo Cotón | Zaragoza | Peonza | 3.65 | 2022-03-05 |
| 2 | Armando Ruido | Huesca | Puzzle | 10.25 | 2022-03-07 |
| 2 | Armando Ruido | Huesca | Puzzle | 10.25 | 2022-03-11 |
| 1 | Juan Palomo | Zaragoza | Playmobil | 5 | 2022-03-18 |
| 1 | Juan Palomo | Zaragoza | Peonza | 3.65 | 2022-03-29 |
| 5 | Alberto Mate | Teruel | Puzzle | 10.25 | 2022-04-08 |
| 5 | Alberto Mate | Teruel | Peonza | 3.65 | 2022-04-09 |
| 4 | Dolores Fuertes | Teruel | Peonza | 3.65 | 2022-04-09 |
| 1 | Juan Palomo | Zaragoza | Playmobil | 5 | 2022-04-12 |
| 2 | Armando Ruido | Huesca | Playmobil | 5 | 2022-04-19 |

``` sql
/*2. Las compras detalladas de los clientes de Teruel.*/

SELECT * FROM COMPRAS 
  LEFT JOIN CLIENTES ON COMPRAS.IDCLIENTE = CLIENTES.IDCLIENTE
  LEFT JOIN PRODUCTOS ON COMPRAS.IDPRODUCTO =PRODUCTOS.IDPRODUCTO
  LEFT JOIN PROVINCIAS ON CLIENTES.IDPROVINCIA =PROVINCIAS.IDPROVINCIA
  WHERE PROVINCIAS.DESCRIPCION = 'Teruel';
```
| IDCOMPRA | IDCLIENTE | IDPRODUCTO | FECHA | IDCLIENTE | NOMBRE | IDPROVINCIA | IDPRODUCTO | DESCRIPCION | PRECIO | IDPROVINCIA | DESCRIPCION |
|---------:|----------:|:-----------|:------|----------:|:-------|------------:|:-----------|:------------|-------:|------------:|:------------|
| 4 | 4 | A | 2022-02-03 | 4 | Dolores Fuertes | 3 | A | Playmobil | 5 | 3 | Teruel |
| 8 | 4 | A | 2022-02-21 | 4 | Dolores Fuertes | 3 | A | Playmobil | 5 | 3 | Teruel |
| 18 | 4 | C | 2022-04-09 | 4 | Dolores Fuertes | 3 | C | Peonza | 3.65 | 3 | Teruel |
| 9 | 5 | C | 2022-03-01 | 5 | Alberto Mate | 3 | C | Peonza | 3.65 | 3 | Teruel |
| 16 | 5 | B | 2022-04-08 | 5 | Alberto Mate | 3 | B | Puzzle | 10.25 | 3 | Teruel |
| 17 | 5 | C | 2022-04-09 | 5 | Alberto Mate | 3 | C | Peonza | 3.65 | 3 | Teruel |

``` sql
/*3. Las compras detalladas de los clientes de Huesca y Zaragoza en el primer trimestre de 2015*/

SELECT * FROM COMPRAS 
  LEFT JOIN CLIENTES ON COMPRAS.IDCLIENTE = CLIENTES.IDCLIENTE
  LEFT JOIN PRODUCTOS ON COMPRAS.IDPRODUCTO =PRODUCTOS.IDPRODUCTO
  LEFT JOIN PROVINCIAS ON CLIENTES.IDPROVINCIA =PROVINCIAS.IDPROVINCIA
  WHERE (PROVINCIAS.DESCRIPCION = 'Teruel' OR  PROVINCIAS.DESCRIPCION = 'Zaragoza')
  AND COMPRAS.FECHA <= '2015-03-31';
```
| IDCOMPRA | IDCLIENTE | IDPRODUCTO | FECHA | IDCLIENTE | NOMBRE | IDPROVINCIA | IDPRODUCTO | DESCRIPCION | PRECIO | IDPROVINCIA | DESCRIPCION |
|---------:|----------:|:-----------|:------|----------:|:-------|------------:|:-----------|:------------|-------:|------------:|:------------|

``` sql
/*4. Las compras agrupadas por producto de todos los clientes mostrando el número de compras y el
importe total para cada producto por cada uno de los clientes (código de cliente, provincia,
producto, número de ventas, importe total)*/

SELECT CLIENTES.IDCLIENTE AS CODIGO_CLIENTE, PROVINCIAS.DESCRIPCION AS PROVINCIA, 
  PRODUCTOS.DESCRIPCION AS PRODUCTO, COUNT(COMPRAS.IDCOMPRA) AS NUMERO_VENTAS, 
  SUM(PRODUCTOS.PRECIO) AS IMPORTE_TOTAL FROM COMPRAS 
  LEFT JOIN CLIENTES ON COMPRAS.IDCLIENTE = CLIENTES.IDCLIENTE
  LEFT JOIN PRODUCTOS ON COMPRAS.IDPRODUCTO = PRODUCTOS.IDPRODUCTO
  LEFT JOIN PROVINCIAS ON CLIENTES.IDPROVINCIA =PROVINCIAS.IDPROVINCIA
  GROUP BY PRODUCTOS.IDPRODUCTO, CLIENTES.IDCLIENTE, PROVINCIAS.IDPROVINCIA;
```
| CODIGO\_CLIENTE | PROVINCIA | PRODUCTO | NUMERO\_VENTAS | IMPORTE\_TOTAL |
|---------------:|:----------|:---------|--------------:|--------------:|
| 1 | Zaragoza | Peonza | 2 | 7.3 |
| 2 | Huesca | Puzzle | 3 | 30.75 |
| 2 | Huesca | Peonza | 1 | 3.65 |
| 4 | Teruel | Playmobil | 2 | 10 |
| 3 | Zaragoza | Playmobil | 2 | 10 |
| 1 | Zaragoza | Puzzle | 2 | 20.5 |
| 5 | Teruel | Peonza | 2 | 7.3 |
| 3 | Zaragoza | Peonza | 1 | 3.65 |
| 1 | Zaragoza | Playmobil | 2 | 10 |
| 5 | Teruel | Puzzle | 1 | 10.25 |
| 4 | Teruel | Peonza | 1 | 3.65 |
| 2 | Huesca | Playmobil | 1 | 5 |

``` sql
/*5. Número de peonzas totales que se han comprado en el mes de marzo por los clientes de
Zaragoza (número de peonzas e importe total)*/

SELECT COUNT(COMPRAS.IDCOMPRA) AS NUMERO_PEONZAS, 
  SUM(PRODUCTOS.PRECIO) AS IMPORTE_TOTAL FROM COMPRAS 
  LEFT JOIN CLIENTES ON COMPRAS.IDCLIENTE = CLIENTES.IDCLIENTE
  LEFT JOIN PRODUCTOS ON COMPRAS.IDPRODUCTO =PRODUCTOS.IDPRODUCTO
  LEFT JOIN PROVINCIAS ON CLIENTES.IDPROVINCIA =PROVINCIAS.IDPROVINCIA
  WHERE  PRODUCTOS.DESCRIPCION = 'Peonza'
  AND MONTH(COMPRAS.FECHA) = 3 AND PROVINCIAS.DESCRIPCION = 'Zaragoza';

```
| NUMERO\_PEONZAS | IMPORTE\_TOTAL |
|---------------:|--------------:|
| 2 | 7.3 |

``` sql
/*6. Las compras realizadas por todos los clientes agrupadas por mes (código de cliente, nombre,
provincia, mes, número de compras, importe total)*/

SELECT CLIENTES.IDCLIENTE AS CODIGO_CLIENTE, CLIENTES.NOMBRE AS NOMBRE_CLIENTE, PROVINCIAS.DESCRIPCION AS PROVINCIA, 
  MONTH(COMPRAS.FECHA) AS MES, COUNT(COMPRAS.IDCOMPRA) AS NUMERO_VENTAS, 
  SUM(PRODUCTOS.PRECIO) AS IMPORTE_TOTAL FROM COMPRAS 
  LEFT JOIN CLIENTES ON COMPRAS.IDCLIENTE = CLIENTES.IDCLIENTE
  LEFT JOIN PRODUCTOS ON COMPRAS.IDPRODUCTO = PRODUCTOS.IDPRODUCTO
  LEFT JOIN PROVINCIAS ON CLIENTES.IDPROVINCIA =PROVINCIAS.IDPROVINCIA
  GROUP BY MONTH(COMPRAS.FECHA), CLIENTES.IDCLIENTE, PROVINCIAS.IDPROVINCIA;
```
| CODIGO\_CLIENTE | NOMBRE\_CLIENTE | PROVINCIA | MES | NUMERO\_VENTAS | IMPORTE\_TOTAL |
|---------------:|:---------------|:----------|----:|--------------:|--------------:|
| 1 | Juan Palomo | Zaragoza | 1 | 1 | 3.65 |
| 2 | Armando Ruido | Huesca | 1 | 2 | 13.9 |
| 4 | Dolores Fuertes | Teruel | 2 | 2 | 10 |
| 3 | Carmelo Cotón | Zaragoza | 2 | 1 | 5 |
| 1 | Juan Palomo | Zaragoza | 2 | 2 | 20.5 |
| 5 | Alberto Mate | Teruel | 3 | 1 | 3.65 |
| 3 | Carmelo Cotón | Zaragoza | 3 | 2 | 8.65 |
| 2 | Armando Ruido | Huesca | 3 | 2 | 20.5 |
| 1 | Juan Palomo | Zaragoza | 3 | 2 | 8.65 |
| 5 | Alberto Mate | Teruel | 4 | 2 | 13.9 |
| 4 | Dolores Fuertes | Teruel | 4 | 1 | 3.65 |
| 1 | Juan Palomo | Zaragoza | 4 | 1 | 5 |
| 2 | Armando Ruido | Huesca | 4 | 1 | 5 |

``` sql
/*7. Detalle de todas las ventas agrupadas por día del mes (día del mes, producto, número de compras,
importe total). Por ejemplo, los días 1 de enero, febrero, marzo y abril se agruparán como día del mes 1*/

SELECT DAY(COMPRAS.FECHA) AS DIA_MES, PRODUCTOS.DESCRIPCION AS PRODUCTO, COUNT(COMPRAS.IDCOMPRA) AS NUMERO_VENTAS, 
  SUM(PRODUCTOS.PRECIO) AS IMPORTE_TOTAL FROM COMPRAS 
  LEFT JOIN CLIENTES ON COMPRAS.IDCLIENTE = CLIENTES.IDCLIENTE
  LEFT JOIN PRODUCTOS ON COMPRAS.IDPRODUCTO = PRODUCTOS.IDPRODUCTO
  GROUP BY DAY(COMPRAS.FECHA), PRODUCTOS.IDPRODUCTO
  ORDER BY DAY(COMPRAS.FECHA);

```
| DIA\_MES | PRODUCTO | NUMERO\_VENTAS | IMPORTE\_TOTAL |
|--------:|:---------|--------------:|--------------:|
| 1 | Playmobil | 1 | 5 |
| 1 | Peonza | 2 | 7.3 |
| 3 | Playmobil | 1 | 5 |
| 5 | Playmobil | 1 | 5 |
| 5 | Peonza | 1 | 3.65 |
| 7 | Puzzle | 1 | 10.25 |
| 8 | Puzzle | 1 | 10.25 |
| 9 | Peonza | 2 | 7.3 |
| 11 | Puzzle | 1 | 10.25 |
| 12 | Playmobil | 1 | 5 |
| 15 | Puzzle | 1 | 10.25 |
| 16 | Puzzle | 1 | 10.25 |
| 18 | Playmobil | 1 | 5 |
| 19 | Playmobil | 1 | 5 |
| 21 | Playmobil | 1 | 5 |
| 21 | Puzzle | 1 | 10.25 |
| 22 | Peonza | 1 | 3.65 |
| 29 | Peonza | 1 | 3.65 |

[fiddle](https://dbfiddle.uk/4jCCH8QO)
