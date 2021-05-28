USE `basededatos_proyecto`;

LOCK TABLES `user_role` WRITE;
insert into user_role VALUES (1,'ROLE_ADMIN');
UNLOCK TABLES;


LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES(1, 'mengano@gmail.com', true, '$2a$10$fsULUPafGxUEmV.RvE4PUun.Tv42TzLsXcfOWgPq2JQlOa/G1cQMm', 'admin', 1, 1);
UNLOCK TABLES;




