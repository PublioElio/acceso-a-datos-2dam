package org.acdat;

import org.acdat.jdbc.MiJDBC;
import org.acdat.vista.VistaCliente;
import org.acdat.vista.VistaVuelo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.nio.charset.MalformedInputException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
        VistaCliente vistaCliente = new VistaCliente();
        //vistaCliente.crudCliente();
        VistaVuelo vistaVuelo = new VistaVuelo();
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            Transaction transaction = null;
            vistaVuelo.crudVuelo();
            try {
                transaction = session.beginTransaction();
                comprobarEstadoSesion(session);

                transaction.commit();
            } catch (Exception e) {
                gestionarExcepcionesEnMain(e, transaction);
            } finally {
                cerrarTodo(session, sessionFactory);
            }
        }
    }

    private static void comprobarEstadoSesion(Session session) {
        if (session != null) {
            Logger.getLogger(SessionFactory.class.getName()).log(Level.INFO, "Sesión iniciada");
        } else {
            Logger.getLogger(SessionFactory.class.getName()).log(Level.SEVERE, "Sesión no iniciada");
        }
    }

    private static void cerrarTodo(Session session, SessionFactory sessionFactory) {
        assert session != null;
        session.close();
        sessionFactory.close();
    }

    private static void gestionarExcepcionesEnMain(Exception e, Transaction transaction) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        Logger.getLogger(SessionFactory.class.getName()).log(Level.SEVERE, "Error al manejar la sesión", e);
    }

}
