package org.example;

public class Main {
    public static void main(String[] args) {
        MyDB db = new MyDB("jdbc:postgresql://localhost:5432/InstitutoFP", "postgres", "A123456a", "org.postgresql.Driver");
        System.out.println("¿Me he CONECTADO a la base de datos? " + db.connect());
        System.out.println("¿Me he DESCONECTADO de la base de datos? " + db.closeConnection());
    }
}