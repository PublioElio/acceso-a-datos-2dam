package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.module.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    static Session abrirSession() throws Exception {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session == null) {
            throw new Exception("Error abriendo la sesión");
        } else {
            System.out.println("Conectado.");
        }

        return session;
    }
}
