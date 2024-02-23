DROP TABLE IF EXISTS prestamos;

DROP TABLE IF EXISTS libros;

DROP TABLE IF EXISTS usuarios;

CREATE TABLE libros (
    isbn varchar(13) PRIMARY KEY,
    titulo varchar(90) NOT NULL,
    copias INTEGER default 1,
    editorial varchar (60)
);

INSERT INTO
    libros (isbn, titulo, copias, editorial)
VALUES
    (
        '0141189207445',
        'El amor en tiempos del colera',
        1,
        'Penguin libros'
    ),
    (
        '0192834177425',
        'Macbeth',
        5,
        'Oxford University Press'
    ),
    (
        '0521316928985',
        'Cien años de soledad',
        6,
        'Cambridge University Press'
    ),
    (
        '9780060722302',
        'Nixon y Kissinger',
        1,
        'HarperCollins editorials Inc'
    ),
    (
        '9780199535897',
        'Romeo y Julieta',
        3,
        'Oxford University Press'
    ),
    (
        '9780415141444',
        'Diccionario Español-Inglés',
        2,
        'Santillana'
    );

CREATE TABLE usuarios(
    codigo varchar(8) PRIMARY KEY,
    nombre varchar(25) NOT NULL,
    apellidos varchar(25) NOT NULL,
    fechanacimiento date
);

INSERT INTO
    usuarios (codigo, nombre, apellidos, fechanacimiento)
VALUES
    ('A786543', 'Jose', 'García', '1983-11-14'),
    ('B329087', 'Juan', 'López', '1984-04-10'),
    ('E433982', 'Ana', 'Ramírez', '1965-08-28'),
    ('E722654', 'Pedro', 'Sánchez', '1994-09-05'),
    ('K893417', 'Antonia', 'Font', '1984-09-18');

CREATE TABLE prestamos (
    id SERIAL PRIMARY KEY,
    fechaprestamo date NOT NULL,
    fechadevolucion date default NULL,
    libro varchar(13) NOT NULL,
    usuario varchar(13) NOT NULL
);

INSERT INTO
    prestamos (fechaprestamo, fechadevolucion, libro, usuario)
VALUES
    (
        '2008-11-28',
        '2008-12-17',
        '9780415141444',
        'A786543'
    ),
    (
        '2009-03-15',
        '2009-03-29',
        '9780060722302',
        'B329087'
    ),
    (
        '2010-05-20',
        '2010-06-10',
        '0521316928985',
        'E433982'
    ),
    (
        '2011-07-11',
        '2011-08-01',
        '9780199535897',
        'E722654'
    ),
    (
        '2012-09-05',
        '2012-09-20',
        '9780199535897',
        'K893417'
    ),
    (
        '2013-10-28',
        '2013-11-10',
        '0192834177425',
        'A786543'
    ),
    (
        '2014-12-02',
        '2014-12-17',
        '0521316928985',
        'B329087'
    ),
    (
        '2015-01-15',
        '2015-02-05',
        '9780060722302',
        'E433982'
    ),
    (
        '2016-03-20',
        '2016-04-05',
        '9780415141444',
        'E722654'
    ),
    (
        '2017-05-10',
        '2017-05-25',
        '0141189207445',
        'K893417'
    ),
    (
        '2018-07-22',
        '2018-08-07',
        '9780199535897',
        'A786543'
    ),
    (
        '2019-09-11',
        '2019-09-26',
        '9780415141444',
        'B329087'
    );

ALTER TABLE
    prestamos
ADD
    CONSTRAINT prestamos_ibfk_1 FOREIGN KEY (libro) REFERENCES libros(isbn) ON DELETE CASCADE ON UPDATE CASCADE,
ADD
    CONSTRAINT prestamos_ibfk_2 FOREIGN KEY (usuario) REFERENCES usuarios(codigo) ON DELETE CASCADE ON UPDATE CASCADE;