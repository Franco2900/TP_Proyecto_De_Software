drop schema if exists baseDeDatos_Proyecto;
create schema baseDeDatos_Proyecto;
use baseDeDatos_Proyecto;

create table Documento(
    idDocumento bigint not null auto_increment,
    titulo varchar(255) not null,
    descripcion varchar(500) not null,
    profesor varchar(255) not null,
    materia varchar(255) not null,
    carrera varchar(255) not null,
    universidad varchar(255) not null,
    hashArchivo varchar(255) not null,
    primary key(idDocumento)
);