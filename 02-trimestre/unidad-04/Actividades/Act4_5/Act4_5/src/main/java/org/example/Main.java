package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Este es el main del ejercicio
 */
public class Main
{
    public static void main( String[] args )
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            if (session != null) {
                System.out.println("Se ha inciado la sesión");
            } else {
                System.out.println("Error abriendo la sesión");
            }
        } catch (Exception e) {
            Logger.getLogger(SessionFactory.class.getName()).log(Level.SEVERE, "Error al manejar la sesión", e);
        } finally {
            assert session != null;
            session.close();
            sessionFactory.close();
        }

    }
}
