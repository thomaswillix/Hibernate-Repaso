Drop Database Empresa;
CREATE DATABASE Empresa;
USE Empresa;

CREATE TABLE departamentos(
  dept_no int(2) primary key,
  nombre varchar(15) DEFAULT NULL,
  loc varchar(15) DEFAULT NULL
) ENGINE=InnoDB;

INSERT INTO departamentos VALUES
(10, 'CONTABILIDAD', 'SEVILLA'),
(20, 'INVESTIGACIÓN', 'MADRID'),
(30, 'VENTAS', 'BARCELONA'),
(40, 'PRODUCCIÓN', 'BILBAO');

CREATE TABLE empleados(
  emp_no int(4) primary key,
  apellido varchar(10) DEFAULT NULL,
  oficio varchar(10) DEFAULT NULL,
  dir int(4) DEFAULT NULL,
  fecha_alt date DEFAULT NULL,
  salario float(6,2) DEFAULT NULL,
  comision float(6,2) DEFAULT NULL,
  dept_no int(2) NOT NULL,
  FOREIGN KEY (dept_no) REFERENCES departamentos(dept_no)
) ENGINE=InnoDB;

INSERT INTO empleados VALUES
(7369, 'SÁNCHEZ', 'EMPLEADO', 7902, '1990-12-17', 1040.00, NULL, 20),
(7499, 'ARROYO', 'VENDEDOR', 7698, '1990-02-20', 1500.00, 390.00, 30),
(7521, 'SALA', 'VENDEDOR', 7698, '1991-02-22', 1625.00, 650.00, 30),
(7566, 'JIMÉNEZ', 'DIRECTOR', 7839, '1991-04-02', 2900.00, NULL, 20),
(7654, 'MARTÍN', 'VENDEDOR', 7698, '1991-09-29', 1600.00, 1020.00, 30),
(7698, 'NEGRO', 'DIRECTOR', 7839, '1991-05-01', 3005.00, NULL, 30),
(7782, 'CEREZO', 'DIRECTOR', 7839, '1991-06-09', 2885.00, NULL, 10),
(7788, 'GIL', 'ANALISTA', 7566, '1991-11-09', 3000.00, NULL, 20),
(7839, 'REY', 'PRESIDENTE', NULL, '1991-11-17', 4100.00, NULL, 10),
(7844, 'TOVAR', 'VENDEDOR', 7698, '1991-09-08', 1350.00, 0.00, 30),
(7876, 'ALONSO', 'EMPLEADO', 7788, '1991-09-23', 1430.00, NULL, 20),
(7900, 'JIMENO', 'EMPLEADO', 7698, '1991-12-03', 1335.00, NULL, 30),
(7902, 'FERNÁNDEZ', 'ANALISTA', 7566, '1991-12-03', 3000.00, NULL, 20),
(7934, 'MUÑOZ', 'EMPLEADO', 7782, '1992-01-23', 1690.00, NULL, 10);

ALTER TABLE empleados
  ADD FOREIGN KEY (dir) REFERENCES empleados (emp_no);