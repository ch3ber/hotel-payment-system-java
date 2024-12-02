-- Creación de la base de datos
CREATE DATABASE HotelPaymentSystemJava;
USE HotelPaymentSystemJava;

-- Tabla para Hoteles
CREATE TABLE Hotel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nivel_estrellas INT NOT NULL CHECK (nivel_estrellas IN (3, 4, 5)),
    costo_base_sencilla DECIMAL(10, 2) NOT NULL,
    costo_base_doble DECIMAL(10, 2) NOT NULL,
    costo_base_penhouse DECIMAL(10, 2) NOT NULL
);

-- Tabla para Habitaciones
CREATE TABLE Habitacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hotel_id INT,
    tipo ENUM('sencilla', 'doble', 'penhouse') NOT NULL,
    costo_por_dia DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES Hotel(id)
);

-- Tabla para Empleados
CREATE TABLE Empleado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo ENUM('Gerente', 'Recamarera', 'Vendedor') NOT NULL,
    salario_base DECIMAL(10, 2) NOT NULL,
    hotel_id INT,
    FOREIGN KEY (hotel_id) REFERENCES Hotel(id)
);

-- Tabla para Gerentes (hereda de Empleado)
CREATE TABLE Gerente (
    empleado_id INT PRIMARY KEY,
    bono DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (empleado_id) REFERENCES Empleado(id)
);

-- Tabla para Recamareras (hereda de Empleado)
CREATE TABLE Recamarera (
    empleado_id INT PRIMARY KEY,
    nivel_experiencia ENUM('auxiliar', 'principiante', 'experimentada', 'ama de llaves') NOT NULL,
    total_habitaciones INT DEFAULT 0,
    FOREIGN KEY (empleado_id) REFERENCES Empleado(id)
);

-- Tabla para Vendedores (hereda de Empleado)
CREATE TABLE Vendedor (
    empleado_id INT PRIMARY KEY,
    comision DECIMAL(10, 2) DEFAULT 0.00,
    FOREIGN KEY (empleado_id) REFERENCES Empleado(id)
);

-- Tabla para Ventas
CREATE TABLE Venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hotel_id INT,
    habitacion_id INT,
    empleado_id INT,
    fecha DATE NOT NULL,
    cantidad_personas INT NOT NULL,
    monto_total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES Hotel(id),
    FOREIGN KEY (habitacion_id) REFERENCES Habitacion(id),
    FOREIGN KEY (empleado_id) REFERENCES Empleado(id)
);

-- Tabla para Comisiones
CREATE TABLE Comision (
    id INT AUTO_INCREMENT PRIMARY KEY,
    empleado_id INT,
    fecha DATE NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (empleado_id) REFERENCES Empleado(id)
);

-- Tabla para Pagos a Empleados
CREATE TABLE Pago (
    id INT AUTO_INCREMENT PRIMARY KEY,
    empleado_id INT,
    periodo ENUM('semanal', 'quincenal', 'mensual') NOT NULL,
    fecha_pago DATE NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (empleado_id) REFERENCES Empleado(id)
);

-- Datos iniciales para los Hoteles
INSERT INTO Hotel (nombre, nivel_estrellas, costo_base_sencilla, costo_base_doble, costo_base_penhouse)
VALUES 
('Hotel Estrella 3', 3, 300.00, 700.00, 1200.00),
('Hotel Estrella 4', 4, 360.00, 910.00, 1680.00),
('Hotel Estrella 5', 5, 480.00, 1260.00, 2400.00);

-- Datos iniciales para los Empleados
INSERT INTO Empleado (nombre, tipo, salario_base, hotel_id)
VALUES 
('Juan Pérez', 'Gerente', 18000.00, 1),
('Ana López', 'Recamarera', 1500.00, 1),
('Carlos Gómez', 'Vendedor', 4500.00, 2);

-- Datos adicionales para cada tipo de empleado
INSERT INTO Gerente (empleado_id, bono) VALUES (1, 5000.00);
INSERT INTO Recamarera (empleado_id, nivel_experiencia, total_habitaciones) VALUES (2, 'ama de llaves', 0);
INSERT INTO Vendedor (empleado_id, comision) VALUES (3, 0.00);
