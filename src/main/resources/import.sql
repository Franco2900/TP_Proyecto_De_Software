insert into documento(titulo, descripcion, materia, profesor, carrera, departamento, universidad, hashArchivo) values("La primera computadora", "Buen libro", "Historia de los sistemas", "Jose", "Informatica", "Desarrollo Productivo y Tecnológico", "UNLa", "f976a72a75241b8adcfd971daeca69d8");
insert into documento(titulo, descripcion, materia, profesor, carrera, departamento, universidad, hashArchivo) values("Ecuaciones y ecuaciones", "Libro aburrido", "Matematicas avanzadas", "Pepe", "Fisica", "Desarrollo Productivo y Tecnológico", "UNQui", "61eb5c60adf662d188e512c0e067b48b");
insert into documento(titulo, descripcion, materia, profesor, carrera, departamento, universidad, hashArchivo) values("Historia de la literatura", "Libro denso", "Literatura", "Luis", "Historia", "Humanidades y Artes", "UTN", "fb499329311ca42711a574ccd9ac800f");
insert into userRole(role) VALUES ('ROLE_ADMIN');
insert into userRole(role) VALUES ('ROLE_PROFESOR');
insert into userRole(role) VALUES ('ROLE_ALUMNO');
insert into user(email, enabled, password, username, userRole) VALUES('mengano@gmail.com', true, '$2y$12$7p2RevsYeODhvje4Im8laOv0fR26Zcs8vUEck82VkAo6yuvbJ3xle', 'admin', 1);