package org.example;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.example.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Este es el main del ejercicio
 */
public class Main {
    private static String TEXTO_DEFAULT = "Elija una opción válida";

    public static void main(String[] args) {

        String textoMenu = "------------Act 4.5. Hibernate Relaciones JPA------------\n" +
                "Elija una opción:\n" +
                "1. Probar clase User\n" +
                "2. Probar clases Concesionario/Vehículo\n" +
                "3. Probar Comprador/Artículo/Venta\n" +
                "4. Salir de la actividad\n" +
                "-> ";

        String textoMenuUser = "------------ CLASE USER ------------\n" +
                "Elija una opción:\n" +
                "1. Introducir un usuario:\n" +
                "2. Mostrar tabla usuarios:\n" +
                "3. Volver al menú principal\n" +
                "-> ";

        String textoMenuConcesionario = "------------ CLASES CONCESIONARIO/VEHÍCULO ------------\n" +
                "Elija una opción:\n" +
                "1. Introducir concesionario:\n" +
                "2. Introducir vehículo:\n" +
                "3. Mostrar relación concesionarios/vehículos:\n" +
                "4. Volver al menú principal\n" +
                "-> ";

        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            if (session != null) {
                System.out.println("Se ha iniciado la sesión");
            } else {
                System.out.println("Se ha iniciado la sesión");
            }


            int opcion;
            do {
                opcion = EntradaTeclado.pedirEntero(textoMenu, 1, 4);
                switch (opcion) {
                    case 1 -> opcion = probarClaseUser(textoMenuUser, session, transaction);
                    case 2 -> {
                        System.out.println("------------ CLASES CONCESIONARIO/VEHÍCULO ------------");
                        do {
                            opcion = EntradaTeclado.pedirEntero(textoMenuConcesionario, 1, 4);
                            switch (opcion) {
                                case 1 -> introducirConcesionario(Session session, Transaction transaction);
                                case 2 ->
                                case 3 ->
                                case 4 ->
                                default -> System.out.println(TEXTO_DEFAULT);

                            }

                        } while (opcion != 4);
                        opcion = 2;
                    }
                    case 3 -> mostrarCasesCompradorArtVenta(session);
                    case 4 -> System.out.println("------------ SALIENDO DE LA ACTIVIDAD ------------");
                    default -> System.out.println(TEXTO_DEFAULT);
                }
            } while (opcion != 4);


        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            Logger.getLogger(SessionFactory.class.getName()).log(Level.SEVERE, "Error al manejar la sesión", e);
        } finally {
            assert session != null;
            session.close();
            sessionFactory.close();
        }
    }

    private static void mostrarCasesCompradorArtVenta(Session session) {
        System.out.println("------------ CLASE COMPRADOR/ARTÍCULO/VENTA ------------");
        listarCompradores(session);
        listarArticulos(session);
        listarVentas(session);
    }

    private static int probarClaseUser(String textoMenuUser, Session session, Transaction transaction) throws ParseException {
        int opcion;
        do {
            opcion = EntradaTeclado.pedirEntero(textoMenuUser, 1, 3);
            switch (opcion) {
                case 1 -> introducirUsuario(session, transaction);
                case 2 -> consultarUsuarios(session);
                case 3 -> System.out.println("Saliendo de clase User");
                default -> System.out.println(TEXTO_DEFAULT);
            }
        } while (opcion != 3);
        opcion = 1;
        return opcion;
    }

    private static void consultarUsuarios(Session session) {
        String hql = "FROM User";
        Query query = session.createQuery(hql);
        List<User> listaUsuarios = query.list();
        for (User usuario : listaUsuarios) {
            System.out.println(usuario.toString());
        }
    }

    private static void introducirUsuario(Session session, Transaction transaction) throws ParseException {
        User usuario = new User();
        String nombre = "", fechaCumpleanyosString = "";
        do {
            nombre = EntradaTeclado.pedirCadena("Introduzca el nombre del usuario: ");
        } while (nombre.isEmpty() && !esTextoValido(nombre));
        do {
            fechaCumpleanyosString = EntradaTeclado.pedirCadena("Introduzca la fecha de nacimiento, formato dd/MM/aa: ");
        } while (!esFormatoFechaValido(fechaCumpleanyosString));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Date fechaCumpleanyos = sdf.parse(fechaCumpleanyosString);
        usuario.setName(nombre);
        usuario.setBirthDate(fechaCumpleanyos);
        session.persist(usuario);
        session.flush();
        transaction.commit();
    }

    private static boolean esTextoValido(String texto) {
        return texto.matches("^[a-zA-Z\\sáéíóúÁÉÍÓÚüÜñÑ]+$");
    }

    public static boolean esFormatoFechaValido(String fecha) {
        boolean esFechaValida = true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(fecha);
        } catch (ParseException e) {
            System.out.println("Formato de fecha incorrecto. Inténtelo de nuevo.");
            esFechaValida = false;
        }
        return esFechaValida;
    }

    private static void listarArticulos(Session session) {
        String hql = "FROM Articulo ";
        Query query = session.createQuery(hql);
        List<Articulo> listaArticulos = query.list();
        for (Articulo articulo : listaArticulos) {
            System.out.println(articulo.toString());
        }
    }

    private static void listarCompradores(Session session) {
        String hql = "FROM Comprador ";
        Query query = session.createQuery(hql);
        List<Comprador> listaCompradores = query.list();
        for (Comprador comprador : listaCompradores) {
            System.out.println(comprador.toString());
        }
    }

    private static void listarVentas(Session session) {
        String hql = "FROM Venta ";
        Query query = session.createQuery(hql);
        List<Venta> listaVentas = query.list();
        for (Venta venta : listaVentas) {
            System.out.println(venta.toString());
        }
    }

    private static void introducirConcesionario(Session session, Transaction transaction) {
        Concesionario concesionario = new Concesionario();
        String nombreComercial, nombreEmpresarial, direccionConcesionario, email,
                textoCampoVacio = "Este campo no puede estar vacío";
        int numTrabajadores;
        List<Vehiculo> vehiculos;

        do {
            nombreComercial = EntradaTeclado.pedirCadena("Introduzca un nombre comercial: ");
            if (nombreComercial.isEmpty()) {
                System.out.println(textoCampoVacio);
            }
        } while (nombreComercial.isEmpty());
        nombreEmpresarial = EntradaTeclado.pedirCadena("Introduzca un nombre empresarial: ");
        do {
            direccionConcesionario = EntradaTeclado.pedirCadena("Introduzca una dirección: ");
            if (direccionConcesionario.isEmpty()) {
                System.out.println(textoCampoVacio);
            }
        } while (direccionConcesionario.isEmpty());
        do {
            email = EntradaTeclado.pedirCadena("Introduzca un email válido: ");
        } while (!EmailValidator.isValidEmail(email));

        do {
            fechaCumpleanyosString = EntradaTeclado.pedirCadena("Introduzca la fecha de nacimiento, formato dd/MM/aa: ");
        } while (!esFormatoFechaValido(fechaCumpleanyosString));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Date fechaCumpleanyos = sdf.parse(fechaCumpleanyosString);
        usuario.setName(nombre);
        usuario.setBirthDate(fechaCumpleanyos);
        session.persist(usuario);
        session.flush();
        transaction.commit();

    }

}
