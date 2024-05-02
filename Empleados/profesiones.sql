
create database Profesiones;
use Profesiones
create table profesion(
    id int primary key,
    nombre varchar(15));
	
insert into Profesion values(1,'Bailarina'),
(2,'Astronauta'),
(3,'Dependiente'),
(4,'Odontologo');	

create table empleado(
    id int primary key,
    nombre varchar(20),
    fecha varchar(10),
    profesion varchar(15));