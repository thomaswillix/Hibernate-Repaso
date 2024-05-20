create database Supermercado;
use Supermercado;

CREATE TABLE Productos (
 Codprod  int PRIMARY KEY,
 Nombre varchar(20),
 Precio   DECIMAL(4,1)
)Engine=InnoDB;

INSERT INTO productos VALUES (10,'producto 1',2.50);
INSERT INTO productos VALUES (20,'producto 2',20);
INSERT INTO productos VALUES (30,'producto 3',150);
INSERT INTO productos VALUES (40,'producto 4',12.5);
INSERT INTO productos VALUES (50,'producto 5',240);
INSERT INTO productos VALUES (60,'producto 6',25.75);
INSERT INTO productos VALUES (70,'producto 7',36.1);
INSERT INTO productos VALUES (80,'producto 8',10.45);
INSERT INTO productos VALUES (90,'producto 9',6);

CREATE TABLE Ventas (
 id int primary key,
 CodVend varchar(10),
 Codprod  int,
 Unidades  int,
 Ganancia DECIMAL(5,1),
 foreign key (codprod) references Productos(codprod)
 on delete cascade on update cascade
 )Engine=InnoDB;


INSERT INTO Ventas VALUES (1,'c08',80,10,104.5);
INSERT INTO Ventas VALUES (2,'f22',10,20,50);
INSERT INTO Ventas VALUES (3,'d18',30,2,300);
INSERT INTO Ventas VALUES (4,'a01',40,8,100);
INSERT INTO Ventas VALUES (5,'p05',50,1,240);
INSERT INTO Ventas VALUES (6,'s01',60,3,77.25);
INSERT INTO Ventas VALUES (7,'b04',70,5,180.5);
INSERT INTO Ventas VALUES (8,'a01',40,20,250);
INSERT INTO Ventas VALUES (9,'b14',90,6,36);
