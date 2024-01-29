package org.example;

import org.example.entity.PersonaJPAEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App
{
    public static void main( String[] args )
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session!=null){
            System.out.println("Se ha inciado la sesión");
        }else {
            System.out.println("Error abriendo la sesión");
        }
    }
}
