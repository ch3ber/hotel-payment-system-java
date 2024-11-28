# Requirements

### Sistema de Gestión de pagos para empleados de cadena de hoteles UR
Desarrolla un sistema para gestionar los pagos de empleados de una empresa.
El sistema debera considerar lo siguiente a continuacion para obtener el sueldo de los empleados y las comisiones segun las ventas hechas. La cadena de hoteles tienen diferentes niveles de 3, 4 y 5 estrellas, el cual esta administrado por 2 gerentes. Cada hotel tiene 40 habitaciones; 20 sencillas, 15 dobles y 5 penhose.

## Aplicando herencia en clase empleado
 Clase base Empleado:
  - Atributos: nombre, salario.
  - Método abstracto calcularSalario(): Este método debe ser implementado por las clases hijas.
 Clases derivadas:
  - Gerente:
    - Atributo adicional: bono.
    - Sobrescribir calcularSalario() para devolver salarioBase + bono.
  - Recamarera:
    - Atributo adicional: totalhabitaciones, comisionXtipohabitacion
    - Sobrescribir calcularSalario() para devolver salarioBase + (totalhabitaciones * comisionXtipohabitacion).
  - Vendedor:
    - Atributo adicional: comisión.
    - Sobrescribir calcularSalario() para devolver salarioBase + comisionVenta

## Funcionalidades
  1. Catalogo de empleados: en esta ocación son Gerentes, Recamarera y Vendedores.
  2. Catalogo de habitaciones segun el hotel y el tipo de recamara. considerar el atributo costo por dia parte del registro de la habitacion
  3. Catalogo de hoteles segun su nivel de estrellas. Considerar el atributo del costo por dia segun el nivel del hotel.
  4. Catalogo de comisiones: en esta ocacion son para la Recamarera y Vendedor, considerar el nivel del hotel al pertenece el emeplado
  5. Registrar las ventas diarias segun las rentas de las habitaciones por hotel, habitacion y cantidad de personas.
  6. Registrar por dia las comisiones de cada empleado segun la venta registrada.
  7. Mostrar el sueldo mensual, quincenal o semanal de un empleado en particular.
  8. Mostrar las comisiones generadas por dia de un hotel en particular.
  9. Mostrar un reporte general de todas las comisiones generadas filtrandolas por un rango de fechas.
  10. Mostrar un reporte general de todas las ventas hechas filtrandolas por un rango de fechas.
  11. Mostrar reporte de los bonos generados mensuales por gerente.

## Especificaciones
  1. La cadena de hoteles tienen caracteristicas muy partioculares segun si el hotel es 3, 4 o 5 estrellas.
  2. Cada hotel maneja segun su estrella diferentes tipos de habitaciones entre la sencilla, doble o penhouse
  3. Cada Recamarera tiene asignada el tipo de habitacion que puede asear segun su  nivel de experiencia entre auxiliar, principiante, experimentada y ama de llavez.
  4. El ama de llavez es la unica que puede intervenir a asear cualquier tipo de habitacion.
  5. Las Recamareras auxiliares y principiantes solo pueden atender habitaciones sencillas y dobles
  6. Las Recamareras experimentadas solo pueden atender habitaciones de dobles y penhouse
  7. La Recamarera solo puede estar en un hotel.
  8. El Gerente puede estar en no mas de 2 hoteles sin importar que nivel del hotel sea.
  9. Comisiones para las recamarera en funcion de la cantidad de habitaciones hechas.
      - sencilla en un hotel 3 estrellas su comision son $30.00
      - doble en un hotel 3 estrellas su comision son $40.00
      - penhouse en un hotel 3 estrellas su comision son $60.00
      - sencilla en un hotel 4 estrellas su comision son $50.00
      - doble en un hotel 4 estrellas su comision son $60.00
      - penhouse en un hotel 4 estrellas su comision son $80.00
      - sencilla en un hotel 5 estrellas su comision son $70.00
      - doble en un hotel 5 estrellas su comision son $80.00
      - penhouse en un hotel 5 estrellas su comision son $100.00
 10. Comisiones para los vendedores en funcion de la cantidad de personas hospedadas y tipo de habitacion.
      - sencilla en un hotel 3 estrellas su comision son $30.00 + $10.00 por persona
      - doble en un hotel 3 estrellas su comision son $40.00 + $15.00 por persona
      - penhouse en un hotel 3 estrellas su comision son $60.00 + $20.00 por persona
      - sencilla en un hotel 4 estrellas su comision son $50.00 + $25.00 por persona
      - doble en un hotel 4 estrellas su comision son $60.00 + $30.00 por persona
      - penhouse en un hotel 4 estrellas su comision son $80.00 + $35.00 por persona
      - sencilla en un hotel 5 estrellas su comision son $70.00 + $40.00 por persona
      - doble en un hotel 5 estrellas su comision son $80.00 + $45.00 por persona
      - penhouse en un hotel 5 estrellas su comision son $100.00 + $50.00 por persona
 11. Las comisiones se deben registrar por dia
 12. El costo base de la renta de habitación segun el nivel del hotel es:
      - Sencilla $300.00 en el hotel 3 estrellas
      - Doble $700.00 en el hotel 3 estrellas
      - Penhouse $1,200.00 en el hotel 3 estrellas
      - Sencilla es el costo del hotel 3 + %20 del mismo
      - Doble es el costo del hotel 3 + %30 del mismo
      - Penhouse es el costo del hotel 3 + %40 del mismo
      - Sencilla es el costo del hotel 3 + %60 del mismo
      - Doble es el costo del hotel 3 + %80 del mismo
      - Penhouse es el costo del hotel 3 + %100 del mismo
 13. El salario base de un Gerente es de $18,000.00 mensuales, el de un vendedor es $4,500.00 quincenales y el de la Recamarera es de $1,500.00 semanales
 14. El Catalogo consta de las funciones Alta, Baja, Modificacion y Consulta.
 15. El gerente tiene un Bono de:
      - $5,000 si las ventas al mes del hotel 3 estrellas llegan al menos a $80,000.00
      - $8,000 si las ventas al mes del hotel 4 estrellas llegan al menos a $100,000.00
      - $12,000 si las ventas al mes del hotel 5 estrellas llegan al menos a $150,000.00
 16. El sistema debe tener ya informacion cargada, exceptuando el registro de la operacion diaria de las ventas, es decir debe haber ya registros de empleados, hoteles, habitaciones y comisiones.

## Usar Tecnologías
 - [ ] Java Swing.
 - [ ] Herencia y polimorfismo.
 - [ ] Clases abstractas.
 - [ ] Uso de arreglos o listas
 - [ ] Archivos en disco o JDBC
