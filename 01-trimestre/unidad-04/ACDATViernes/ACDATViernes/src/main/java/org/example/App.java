package org.example;

import org.example.entity.PersonaJPAEntity;
import org.example.entity.TelefonoJPAEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            if (session != null) {
                System.out.println("Se ha inciado la sesión");
            } else {
                System.out.println("Error abriendo la sesión");
            }

            PersonaJPAEntity persona = new PersonaJPAEntity("Pepe");
            session.persist(persona);

            TelefonoJPAEntity telefono = new TelefonoJPAEntity("111555666");
            telefono.setPersona(persona);
            session.persist(telefono);
            session.flush();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }
}
