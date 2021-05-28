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

LOCK TABLES `user_role` WRITE;
insert into user_role VALUES (1,'ROLE_ADMIN');
UNLOCK TABLES;

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES(1, 'mengano@gmail.com', true, '$2a$10$fsULUPafGxUEmV.RvE4PUun.Tv42TzLsXcfOWgPq2JQlOa/G1cQMm', 'admin', 1, 1);
UNLOCK TABLES;