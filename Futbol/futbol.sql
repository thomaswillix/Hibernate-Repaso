create database Futbol;
use Futbol;

CREATE TABLE Equipos(
    CodEquipo   varchar(4) Primary Key,
    Nombre      varchar(30) not null,
    Localidad   varchar(15));

CREATE TABLE Partidos(
   CodPartido	      varchar(4) Primary Key,
   CodLocal	          varchar(4),
   CodVisitante	      varchar(4),
   Jornada	          int,
   Gol_Local	      int,
   Gol_Visitante	  int,
   Foreign key (CodLocal) references Equipos(CodEquipo)
   on delete cascade,
   Foreign key (CodVisitante) references Equipos(CodEquipo)
   on delete cascade);
   
CREATE TABLE Estadisticas(
   CodEquipo	varchar(4) Primary Key,
   PGanados     int,
   PPerdidos    int,
   PEmpatados   int,
   GolFav       int,
   GolCont      int,
   Puntos       int);   

INSERT INTO EQUIPOS VALUES ('01', 'Equipo 1', 'Bilbao');  
INSERT INTO EQUIPOS VALUES ('02', 'Equipo 2', 'Madrid');  
INSERT INTO EQUIPOS VALUES ('03', 'Equipo 3', 'Pamplona');
INSERT INTO EQUIPOS VALUES ('04', 'Equipo 4', 'Barcelona');  
INSERT INTO EQUIPOS VALUES ('05', 'Equipo 5', 'Getafe');
INSERT INTO EQUIPOS VALUES ('06', 'Equipo 6', 'Granada');
INSERT INTO EQUIPOS VALUES ('07', 'Equipo 7', 'Valencia');
INSERT INTO EQUIPOS VALUES ('08', 'Equipo 8', 'Málaga');
INSERT INTO EQUIPOS VALUES ('09', 'Equipo 9', 'Madrid');
INSERT INTO EQUIPOS VALUES ('10', 'Equipo 10', 'Madrid');    

INSERT INTO Partidos VALUES ('01','01','02',1,1,2);
INSERT INTO Partidos VALUES ('02','03','04',1,3,4);
INSERT INTO Partidos VALUES ('03','05','06',1,2,2);
INSERT INTO Partidos VALUES ('04','07','08',1,0,0);
INSERT INTO Partidos VALUES ('05','09','10',1,3,2);
INSERT INTO Partidos VALUES ('06','10','01',2,4,2);
INSERT INTO Partidos VALUES ('07','08','03',2,4,0);
INSERT INTO Partidos VALUES ('08','06','07',2,1,1);
INSERT INTO Partidos VALUES ('09','04','05',2,6,2);
INSERT INTO Partidos VALUES ('10','02','09',2,5,0);
INSERT INTO Partidos VALUES ('11','07','02',3,0,0);
INSERT INTO Partidos VALUES ('12','09','04',3,3,2);
INSERT INTO Partidos VALUES ('13','01','06',3,4,2);
INSERT INTO Partidos VALUES ('14','05','08',3,5,2);
INSERT INTO Partidos VALUES ('15','03','10',3,1,1);
INSERT INTO Partidos VALUES ('16','02','03',4,6,2);
INSERT INTO Partidos VALUES ('17','10','07',4,5,0);
INSERT INTO Partidos VALUES ('18','04','01',4,3,2);
INSERT INTO Partidos VALUES ('19','06','05',4,0,0);
INSERT INTO Partidos VALUES ('20','08','09',4,5,1);
INSERT INTO Partidos VALUES ('21','03','02',5,2,4);
INSERT INTO Partidos VALUES ('22','07','10',5,1,0);
INSERT INTO Partidos VALUES ('23','01','04',5,2,3);
INSERT INTO Partidos VALUES ('24','05','06',5,2,2);
INSERT INTO Partidos VALUES ('25','09','08',5,1,1);
INSERT INTO Partidos VALUES ('26','02','01',6,1,2);
INSERT INTO Partidos VALUES ('27','04','03',6,3,4);
INSERT INTO Partidos VALUES ('28','06','09',6,2,2);
INSERT INTO Partidos VALUES ('29','08','07',6,0,0);
INSERT INTO Partidos VALUES ('30','10','05',6,3,2);



   

	 
