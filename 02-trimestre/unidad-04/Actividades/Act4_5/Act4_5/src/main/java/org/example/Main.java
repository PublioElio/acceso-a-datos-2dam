package org.example;

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

/**
 * Este es el main del ejercicio
 */
public class Main {
    private static final String TEXTO_DEFAULT = "Elija una opción válida";

    private static final String ELIJA_OPCION = "Elija una opción:\n";

    public static void main(String[] args) {

        String textoMenu = "------------Act 4.5. Hibernate Relaciones JPA------------\n" + ELIJA_OPCION +
                "1. Probar clase User\n" +
                "2. Probar clases Concesionario/Vehículo\n" +
                "3. Probar Comprador/Artículo/Venta\n" +
                "4. Salir de la actividad\n" +
                "-> ";

        String textoMenuUser = "------------ CLASE USER ------------\n" + ELIJA_OPCION +
                "1. Introducir un usuario:\n" +
                "2. Mostrar tabla usuarios:\n" +
                "3. Volver al menú principal\n" +
                "-> ";

        String textoMenuConcesionario = "------------ CLASES CONCESIONARIO/VEHÍCULO ------------\n" + ELIJA_OPCION +
                "1. Introducir concesionario:\n" +
                "2. Introducir uno o varios vehículos en un concesionario:\n" +
                "3. Mostrar relación concesionarios/vehículos:\n" +
                "4. Volver al menú principal\n" +
                "-> ";

        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
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
                    case 1 -> opcion = probarClaseUser(textoMenuUser, session);
                    case 2 -> probarConcesionarioVehiculo(textoMenuConcesionario, session, transaction);
                    case 3 -> mostrarCasesCompradorArtVenta(session);
                    case 4 -> System.out.println("------------ SALIENDO DE LA ACTIVIDAD ------------");
                    default -> System.out.println(TEXTO_DEFAULT);
                }
            } while (opcion != 4);

            transaction.commit();
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

    private static void probarConcesionarioVehiculo(String textoMenuConcesionario, Session session, Transaction transaction) {
        int opcion;
        do {
            opcion = EntradaTeclado.pedirEntero(textoMenuConcesionario, 1, 4);
            switch (opcion) {
                case 1 -> introducirConcesionario(session);
                case 2 -> introducirVehiculos(session);
                case 3 -> listarConcesionariosVehiculos(session);
                case 4 -> System.out.println("Volviendo al menú principal");
                default -> System.out.println(TEXTO_DEFAULT);
            }
        } while (opcion != 4);
    }


    private static void mostrarCasesCompradorArtVenta(Session session) {
        System.out.println("------------ CLASE COMPRADOR/ARTÍCULO/VENTA ------------");
        listarCompradores(session);
        listarArticulos(session);
        listarVentas(session);
    }

    private static int probarClaseUser(String textoMenuUser, Session session) throws ParseException {
        int opcion;
        do {
            opcion = EntradaTeclado.pedirEntero(textoMenuUser, 1, 3);
            switch (opcion) {
                case 1 -> introducirUsuario(session);
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

    private static void introducirUsuario(Session session) throws ParseException {
        User usuario = new User();
        String nombre;
        String fechaCumpleanyosString;
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

    private static void introducirConcesionario(Session session) {
        Concesionario concesionario = new Concesionario();
        String nombreComercial;
        String direccionConcesionario;
        String email;
        String textoCampoVacio = "Este campo no puede estar vacío";
        do {
            nombreComercial = EntradaTeclado.pedirCadena("Introduzca un nombre comercial: ");
            if (nombreComercial.isEmpty()) {
                System.out.println(textoCampoVacio);
            }
        } while (nombreComercial.isEmpty());
        concesionario.setNombreComercial(nombreComercial);
        concesionario.setNombreEmpresarial(EntradaTeclado.pedirCadena("Introduzca un nombre empresarial: "));
        do {
            direccionConcesionario = EntradaTeclado.pedirCadena("Introduzca una dirección: ");
            if (direccionConcesionario.isEmpty()) {
                System.out.println(textoCampoVacio);
            }
        } while (direccionConcesionario.isEmpty());
        concesionario.setDireccionConcesionario(direccionConcesionario);
        do {
            email = EntradaTeclado.pedirCadena("Introduzca un email válido: ");
        } while (!EmailValidator.isValidEmail(email));
        concesionario.setEmail(email);
        concesionario.setNumTrabajadores(EntradaTeclado.pedirEntero("Introduzca el número de trabajadores: ", 0));

        session.persist(concesionario);
        session.flush();
    }

    private static void introducirVehiculos(Session session) {
        int numVehiculos = EntradaTeclado.pedirEntero("¿Cuántos vehículos quiere introducir? ", 0, 50);
        if (numVehiculos > 0) {
            Concesionario concesionario = new Concesionario();
            String fechaMatriculacion;
            concesionario.setNombreComercial(EntradaTeclado.pedirCadena("Introduzca el concesionario al que pertenecen los vehículos: "));
            concesionario = buscarConcesionario(concesionario.getNombreComercial(), session);
            if (concesionario != null) {
                for (int i = 0; i < numVehiculos; i++) {
                    Vehiculo vehiculo = new Vehiculo();
                    vehiculo.setConcesionario(concesionario);
                    do {
                        vehiculo.setMatricula(EntradaTeclado.pedirCadena("Introduzca la matrícula: "));
                    } while (vehiculo.getMatricula().isEmpty());
                    vehiculo.setMarca(EntradaTeclado.pedirCadena("Introduzca la marca: "));
                    vehiculo.setModelo(EntradaTeclado.pedirCadena("Introduzca el modelo: "));
                    vehiculo.setColor(EntradaTeclado.pedirCadena("Introduzca el color: "));
                    do {
                        fechaMatriculacion = EntradaTeclado.pedirCadena("Introduzca la fecha de matriculación válida, formato dd/MM/aa: ");
                    } while (!esFormatoFechaValido(fechaMatriculacion));
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                    try {
                        vehiculo.setFechaMatriculacion(sdf.parse(fechaMatriculacion));
                    } catch (ParseException e) {
                        System.out.println("Error al introducir la fecha de matriculación");
                    }
                    session.persist(vehiculo);
                }
                session.flush();
            }
        }
    }

    private static Concesionario buscarConcesionario(String nombreConcesionario, Session session) {
        Concesionario concesionario = null;
        Query query = session.createQuery("FROM Concesionario WHERE nombreComercial = :nombre");
        query.setParameter("nombre", nombreConcesionario);
        List<Concesionario> resultado = query.list();
        if (!resultado.isEmpty()) {
            System.out.println("Concesionario encontrado: " + resultado.get(0).getNombreComercial());
            concesionario = resultado.get(0);
        } else {
            System.out.println("Concesionario no encontrado.");
        }
        return concesionario;
    }

    private static void listarConcesionariosVehiculos(Session session) {
        String hql = "FROM Concesionario";
        Query query = session.createQuery(hql);
        List<Concesionario> listaConcesionarios = query.list();
        for (Concesionario concesionario : listaConcesionarios) {
            System.out.println(concesionario.toString());
        }
    }

}
