package org.example;

public class Main {
    public static void main(String[] args) {
        DBManager db = new DBManager("jdbc:postgresql://localhost:5432/empleados",
                "postgres",
                "A123456a",
                "org.postgresql.Driver");
        System.out.println("Conexión realizada: " + db.connect());

        // Las dos siguientes líneas de código están comentadas porque son para crear y borrar una base de datos, respectivamente
        //System.out.println("Crear base de datos empleados: " + db.createDatabase("empleados"));
        //System.out.println("Borrar base de datos empleados: " + db.deleteDatabase("empleados"));

        // Crear las tablas:
        String tablaDepartamentos = "CREATE TABLE departamentos ("
                + "id SERIAL PRIMARY KEY,"
                + "nombre VARCHAR(100) NOT NULL,"
                + "presupuesto DOUBLE PRECISION NOT NULL,"
                + "gastos DOUBLE PRECISION NOT NULL"
                + ")";
        String tablaEmpleados = "CREATE TABLE empleados ("
                + "id SERIAL PRIMARY KEY,"
                + "nif VARCHAR(9) NOT NULL UNIQUE,"
                + "nombre VARCHAR(100) NOT NULL,"
                + "apellido1 VARCHAR(100) NOT NULL,"
                + "apellido2 VARCHAR(100),"
                + "id_departamento INT,"
                + "FOREIGN KEY (id_departamento) REFERENCES departamentos(id)"
                + ")";
        System.out.println("Se ha creado la tabla 'departamentos': " + db.createTable(tablaDepartamentos));
        System.out.println("Se ha creado la tabla 'empleados': " + db.createTable(tablaEmpleados));

        // Este línea de abajo es para probar que, efectivamente, funciona el método para borrar tablas
        //System.out.println("Se ha borrado la tabla 'departamentos': " + db.deleteTable("departamentos"));

        System.out.println("Conexión terminada: " + db.closeConnection());
    }
}