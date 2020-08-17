create database tareads;
use tareads;

create table productos(
CodProd varchar(2) primary key,
NomProd varchar(50) not null,
DetaProd varchar(50) not null,
CatProd varchar(20) not null,
StocProd int(2) not null,
PrecProd float
);
select * from productos;

insert into productos values("S1","Shampoo","De Coco","Cabello",30,25);

drop table productos;