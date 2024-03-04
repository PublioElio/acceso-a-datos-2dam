



-- Insertar datos en la tabla de Autores
INSERT INTO Autores (nombre_autor, pais) VALUES
('Autor A', 'USA'),
('Autor B', 'UK'),
('Autor C', 'ESP');



-- Insertar datos en la tabla de Libros
INSERT INTO Libros (titulo, id_autor, genero, anio_publicacion) VALUES
('Libro 1', 1, 'Ficción', 2010),
('Libro 2', 2, 'No-Ficción', 2015),
('Libro 3', 1, 'Aventura', 2008),
('Libro 4', 3, 'Ciencia', 2012);



-- Insertar datos en la tabla de Usuarios
INSERT INTO Usuarios (nombre_usuario, email) VALUES
('Usuario A', 'usuarioA@email.com'),
('Usuario B', 'usuarioB@email.com'),
('Usuario C', 'usuarioC@email.com');



-- Insertar datos en la tabla de Prestamos
INSERT INTO Prestamos (id_libro, fecha_prestamo, fecha_devolucion, id_usuario) VALUES
(1, '2024-02-01', '2024-02-15', 1),
(2, '2024-02-02', '2024-02-16', 2),
(3, '2024-02-03', '2024-02-18', 1),
(4, '2024-02-04', '2024-02-20', 3);
