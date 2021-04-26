
Create table cuenta (
idcuenta integer AUTO_INCREMENT primary key,
numerocuenta varchar(20),
saldo float 
);

Create table persona (
dni varchar(12) primary key,
apellidos varchar(30),
nombre varchar(30),
idcuenta integer,
foreign key(idcuenta) references cuenta(idcuenta) on update cascade on delete cascade
);

insert into cuenta(numerocuenta,saldo) values ('12345647890',1200);
insert into cuenta(numerocuenta,saldo) values ('11111111111',-50);
insert into cuenta(numerocuenta,saldo) values ('22222222222',-500);
insert into cuenta(numerocuenta,saldo) values ('33333333333',2200);
insert into cuenta(numerocuenta,saldo) values ('44444444444',12000);

insert into persona values ('11222333', 'PÉREZ ALVAREZ', 'ANDRES', 1);
insert into persona values ('11222444', 'ALVAREZ ALVAREZ', 'LOLA', 2);
insert into persona values ('11222555', 'GARCÍA ALVAREZ', 'PEPE', 3);
insert into persona values ('11222666', 'MEDINA ALVAREZ', 'LAURA', 4);
insert into persona values ('11222777', 'PUERTO ALVAREZ', 'ROSA',5);
insert into persona(dni,apellidos,nombre) values ('11222888', 'ROMERO ALVAREZ', 'PEDRO');
insert into persona(dni,apellidos,nombre) values ('11222999', 'SUAREZ ALVAREZ', 'JUAN');

