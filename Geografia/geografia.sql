create database Geografia;
use Geografia

CREATE TABLE Localidades( 
   CodLoc varchar(4) Primary Key,
   Nombre varchar(30) NOT NULL,
   Censo int,
   Habitantes int,
   NomProv varchar(25));
   
insert into Localidades values('005','Alcalá de Henares', 137938, 176434, 'Madrid');
insert into Localidades values('014','Arganda del Rey', 24879, 33432,'Madrid');
insert into Localidades values('078','Cuenca', 37109, 46341,'Cuenca');
insert into Localidades values('130','Guadalajara', 53622, 68248,'Guadalajara');
insert into Localidades values('069','Madrid', 2337580, 2938723,'Madrid');
insert into Localidades values('121','Ocaña', 4862, 6441,'Toledo');
insert into Localidades values('123','Rivas Vaciamadrid', 26260, 35742,'Madrid');
insert into Localidades values('203','Tarancón', 8956, 11786,'Cuenca');
insert into Localidades values('148','Torrejón de Ardoz', 74703, 97887,'Madrid');
insert into Localidades values('175','Aranjuez', 55755, 60441,'Madrid');
insert into Localidades values('305','Talavera de la Reina', 86779, 91236,'Toledo');