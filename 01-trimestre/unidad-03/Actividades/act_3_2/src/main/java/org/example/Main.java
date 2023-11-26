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
        /*
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
        */

        // Estas líneas de abajo es para probar que funciona el método para borrar tablas
        //System.out.println("Se ha borrado la tabla 'departamentos': " + db.deleteTable("departamentos"));
        //System.out.println("Se ha borrado la tabla 'empleados': " + db.deleteTable("empleados"));

        // Insertar elementos en las tablas **ATENCIÓN**: los elementos tipo double son declarados como tales
        // para evitar problemas en la conversión de tipos en el PreparedStatement
        /*
        Object[][] nuevosDept = {
                {"Desarrollo", 120000.0, 6000.0},
                {"Sistemas", 150000.0, 21000.0},
                {"Recursos Humanos", 280000.0, 25000.0},
                {"Contabilidad", 110000.0, 3000.0},
                {"I+D", 375000.0, 380000.0},
                {"Proyectos", 0.0, 0.0},
                {"Publicidad", 0.0, 1000.0}
        };
        System.out.println("Total de departamentos insertados: " + db.insertDept(nuevosDept));
        */

        /*
        Object[][] nuevosEmp = {
                {"32481596F", "Aarón", "Rivero", "Gómez", 1},
                {"Y5575632D", "Adela", "Salas", "Díaz", 2},
                {"R6970642B", "Adolfo", "Rubio", "Flores", 3},
                {"77705545E", "Adrián", "Suárez", null, 4},
                {"17087203C", "Marcos", "Loyola", "Méndez", 5},
                {"38382980M", "María", "Santana", "Moreno", 1},
                {"80576669X", "Pilar", "Ruiz", null, 2},
                {"71651431Z", "Pepe", "Ruiz", "Santana", 3},
                {"56399183D", "Juan", "Gómez", "López", 2},
                {"46384486H", "Diego", "Flores", "Salas", 5},
                {"67389283A", "Marta", "Herrera", "Gil", 1},
                {"41234836R", "Irene", "Salas", "Flores", null},
                {"82635162B", "Juan Antonio", "Sáez", "Guerrero", null}
        };
        System.out.println("Total de empleados insertados: " + db.insertEmp(nuevosEmp));
        */

        // 1 - Lista el primer apellido de todos los empleados.
        System.out.println("Primer apellido de todos los empleados:\n"
                + db.singleColumQuery("apellido1", "empleados"));
        System.out.println("----------------------------------------------------------------------------");

        // 2 - Lista el primer apellido de los empleados eliminando los apellidos que estén repetidos.
        System.out.println("Primer apellido de todos los empleados, sin repetir:\n"
                + db.singleColumDistinct("apellido1", "empleados"));
        System.out.println("----------------------------------------------------------------------------");

        // 3 - Devuelve una lista con el nombre y el gasto, de los 2 departamentos que tienen menor gasto.
        db.deptMenorGasto();
        System.out.println("----------------------------------------------------------------------------");

        // 4 - Devuelve una lista con el nombre de los departamentos y el presupuesto, de aquellos que tienen un presupuesto mayor o igual a 150000 euros.
        db.deptPrepMayor();
        System.out.println("----------------------------------------------------------------------------");

        // 5 - Devuelve un listado con los empleados y los datos de los departamentos donde trabaja cada uno.
        db.empDept();
        System.out.println("----------------------------------------------------------------------------");

        // 6 - Devuelve un listado con los empleados y los datos de los departamentos donde trabaja cada uno. Ordena el resultado, en primer lugar por el nombre del departamento (en orden alfabético) y en segundo lugar por los apellidos y el nombre de los empleados.
        db.listarEmpDep();
        System.out.println("----------------------------------------------------------------------------");

        // 7 - Devuelve un listado con el identificador y el nombre del departamento, solamente de aquellos departamentos que tienen empleados.
        db.depIdConEmpleados();
        System.out.println("----------------------------------------------------------------------------");

        // 8 - Devuelve el nombre del departamento donde trabaja el empleado que tiene el nif 38382980M.
        db.buscarDeptPorNifEmp();
        System.out.println("----------------------------------------------------------------------------");

        // 9 - Calcula la suma del presupuesto de todos los departamentos.
        db.sumarPresupuesto();

        System.out.println("Conexión terminada: " + db.closeConnection());
    }
}