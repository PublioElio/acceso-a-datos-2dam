package org.example;
import org.hibernate.SessionFactory;

import javax.security.auth.login.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();



    }

}
