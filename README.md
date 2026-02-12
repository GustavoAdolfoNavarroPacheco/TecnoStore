
---

# TecnoStore

Sistema de gestión empresarial para una tienda de tecnología desarrollado en **Java (POO)** con conexión a **MySQL** mediante POM.

El proyecto implementa una arquitectura por capas (Model – View – Controler) y permite gestionar celulares, clientes, ventas y generar reportes y análisis comerciales.

---

## Descripción del Proyecto

TecnoStore es una aplicación de consola orientada a la administración de una tienda de celulares. Permite:

* Gestión de marcas
* Gestión de celulares
* Gestión de clientes
* Registro de ventas
* Registro de detalles de venta
* Generación de reportes
* Análisis de datos comerciales

Incluye consultas analíticas como:

* Celulares con stock bajo
* Top 3 celulares más vendidos
* Ventas totales por mes

El sistema utiliza relaciones entre tablas (Foreign Keys) y consultas SQL con `JOIN`, `GROUP BY`, `SUM`, `ORDER BY` y `LIMIT`.

---

## Arquitectura del Proyecto

El proyecto está organizado en tres capas principales:

### 📁 Controler

Contiene la lógica de negocio y acceso a datos.

* `Conexion.java` → Manejo de conexión JDBC a MySQL
* `GestionCliente.java` / `GestionCliente_Implement.java`
* `GestionCelular.java` / `GestionCelular_Implement.java`
* `GestionVenta.java` / `GestionVenta_Implement.java`
* `GestionDetalleVenta.java` / `GestionDetalleVenta_Implement.java`
* `GestionReporte.java`
* `GestionAnalisis.java` / `GestionarAnalisis_Implement.java`
* `VentaReportes.java`
* `Validaciones.java`

### 📁 Model

Contiene las entidades del sistema:

* `Celular.java`
* `Cliente.java`
* `Marca.java`
* `Venta.java`
* `DetalleVenta.java`
* `Gama.java` (enum: Baja, Media, Alta)

### 📁 View

Contiene los menús y la interacción con el usuario:

* `Main.java`
* `MenuCelular.java`
* `MenuCliente.java`
* `MenuVentas.java`
* `MenuGenerarReporte.java`
* `MenuReportesyAnalisis.java`

### 📁 com.mycompany.tecnostore

* `TecnoStore.java` (Clase principal con método `main`)

---

## Modelo de Base de Datos

Base de datos: `tecnostore_db`

### Tablas

```sql
SHOW TABLES;
```

```text
celular
cliente
detalle_venta
marca
venta
```

---

## Estructura de Tablas (DDL)

### Tabla: marca

```sql
CREATE TABLE marca (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
```

---

### Tabla: celular

```sql
CREATE TABLE celular (
    id INT NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(100) NOT NULL,
    sistema_operativo VARCHAR(50) NOT NULL,
    gama ENUM('Baja','Media','Alta') NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    id_marca INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_marca) REFERENCES marca(id)
);
```

---

### Tabla: cliente

```sql
CREATE TABLE cliente (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    identificacion VARCHAR(30) NOT NULL,
    correo VARCHAR(200) NOT NULL,
    celular VARCHAR(60) NOT NULL,
    PRIMARY KEY (id)
);
```

---

### Tabla: venta

```sql
CREATE TABLE venta (
    id INT NOT NULL AUTO_INCREMENT,
    fecha DATE NOT NULL,
    total DOUBLE NOT NULL,
    id_cliente INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);
```

---

### Tabla: detalle_venta

```sql
CREATE TABLE detalle_venta (
    id INT NOT NULL AUTO_INCREMENT,
    cantidad INT NOT NULL DEFAULT 0,
    subtotal DOUBLE NOT NULL,
    id_celular INT NOT NULL,
    id_venta INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_celular) REFERENCES celular(id),
    FOREIGN KEY (id_venta) REFERENCES venta(id)
);
```

---

## Ejemplo de Ejecución

Al ejecutar la aplicación:

```text
========================================
=              TECNOSTORE              = 
========================================
= [1] Gestionar Celulares              =
= [2] Gestionar Clientes.              =
= [3] Gestionar Ventas.                =
= [4] Reportes y Analisis.             =
= [0] Salir.                           =
========================================
Seleccione una opcion:
```

Ejemplo – Análisis:

```text
========================================
=         ANALISIS / CONSULTAS         = 
========================================
= [1] Celulares con Stock Bajo.        =
= [2] Celulares mas Vendidos.          =
= [3] Ventas por mes.                  =
= [4] Regresar.                        =
========================================
```

Salida ejemplo:

```text
==== CELULARES CON STOCK BAJO ====
ID: 4
Modelo: iPhone SE
Stock: 4
----------------------------------
```

---

## 🔌 Conexión a MySQL

El proyecto se conecta a MySQL utilizando **JDBC** mediante la clase `DriverManager`.

La conexión está implementada de la siguiente manera:

```java
public Connection Conexion() {
    Connection c = null;
    try {
        c = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/tecnostore_db",
            "root",
            "password"
        );
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return c;
}
```

### Requisitos previos

1. Tener MySQL instalado y en ejecución.
2. Crear la base de datos:

   ```sql
   CREATE DATABASE tecnostore_db;
   ```
3. Configurar correctamente:

   * Usuario (ej: `root`)
   * Contraseña (reemplazar `"password"` por la real)
   * Puerto (por defecto `3306`)

### Dependencia necesaria

Asegúrate de tener el **MySQL Connector/J** agregado al proyecto:

* Si usas Maven:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.0.33</version>
</dependency>
```

---

## Tecnologías Utilizadas

* Java (POO)
* JDBC
* MySQL
* SQL (JOIN, GROUP BY, SUM, ORDER BY, LIMIT)
* Arquitectura MVC simplificada

---

## Características Técnicas

* Uso de `PreparedStatement`
* Manejo de `ResultSet`
* Uso de `try-with-resources`
* Relaciones con claves foráneas
* Uso de `ENUM` en base de datos
* Implementación de interfaces para desacoplamiento
* Separación por capas

---

## Autor

Proyecto académico desarrollado como sistema de gestión empresarial para práctica de:

* Programación Orientada a Objetos
* Bases de Datos Relacionales
* Integración Java – MySQL
* Consultas analíticas SQL

---
