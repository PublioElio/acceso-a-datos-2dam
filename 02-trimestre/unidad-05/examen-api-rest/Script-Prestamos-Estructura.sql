drop table if exists Autores;
drop table if exists Libros;
drop table if exists Usuarios;
drop table if exists Prestamos;


-- Crear la tabla de Autores
CREATE TABLE Autores (
    id_autor SERIAL PRIMARY KEY,
    nombre_autor VARCHAR(255),
    pais VARCHAR(50)
);

-- Crear la tabla de Libros
CREATE TABLE Libros (
    id_libro SERIAL PRIMARY KEY,
    titulo VARCHAR(255),
    id_autor INT REFERENCES Autores(id_autor),
    genero VARCHAR(50),
    anio_publicacion INT
);

-- Crear la tabla de Usuarios
CREATE TABLE Usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nombre_usuario VARCHAR(255),
    email VARCHAR(100)
);

-- Crear la tabla de Prestamos
CREATE TABLE Prestamos (
    id_prestamo SERIAL PRIMARY KEY,
    id_libro INT REFERENCES Libros(id_libro),
    fecha_prestamo DATE,
    fecha_devolucion DATE,
    id_usuario INT REFERENCES Usuarios(id_usuario)
);
