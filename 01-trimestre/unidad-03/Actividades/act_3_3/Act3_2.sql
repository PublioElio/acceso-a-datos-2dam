DROP TABLE IF EXISTS departamentos;
DROP TABLE IF EXISTS empleados;

CREATE TABLE departamentos
	(depno INTEGER PRIMARY KEY,
	nombre VARCHAR(14),
	ubicacion VARCHAR(13));

INSERT INTO departamentos VALUES (10, 'Contabilidad', 'Madrid');
INSERT INTO departamentos VALUES (20, 'Marketing', 'Barcelona');
INSERT INTO departamentos VALUES (10, 'Ventas', 'Alicante');
INSERT INTO departamentos VALUES (10, 'Logistica', 'Valencia');

CREATE TABLE empleados
	(empno INTEGER PRIMARY KEY,
	nombre VARCHAR(10),
	puesto VARCHAR(15),
	depno INTEGER,
		CONSTRAINT FK_EMPLOYEE_DPT FOREIGN KEY
		(depno) REFERENCES departamentos(deptno) );

INSERT INTO empleados VALUES (7369, 'Garcia', 'Dependiente', 20);
INSERT INTO empleados VALUES (7499, 'López', 'Vendedor', 30);
INSERT INTO empleados VALUES (7521, 'Pérez', 'Vendedor', 30);
INSERT INTO empleados VALUES (7566, 'Gómez', 'Responsable', 20);
INSERT INTO empleados VALUES (7654, 'Vázquez', 'Vendedor', 30);
INSERT INTO empleados VALUES (7698, 'Martínez', 'Responsable', 30);
INSERT INTO empleados VALUES (7782, 'Sánchez', 'Responsable', 10);
INSERT INTO empleados VALUES (7788, 'Jiménez', 'Analista', 20);
INSERT INTO empleados VALUES (7839, 'Peláez', 'Presidente', 10);

--
INSERT INTO empleados VALUES (7782, 'Sánchez', 'Responsable', 10);
INSERT INTO empleados VALUES (7782, 'Sánchez', 'Responsable', 10);
INSERT INTO empleados VALUES (7782, 'Sánchez', 'Responsable', 10);
INSERT INTO empleados VALUES (7782, 'Sánchez', 'Responsable', 10);
INSERT INTO empleados VALUES (7782, 'Sánchez', 'Responsable', 10);



