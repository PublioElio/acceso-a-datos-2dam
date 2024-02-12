package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entidades.CuentaCredito;
import org.example.entidades.CuentaDebito;

/**
 * Este es el main para probar el proyecto
 */
public class Main
{
    public static void main( String[] args ) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            CuentaCredito cuentaCredito = new CuentaCredito();
            cuentaCredito.setTitular("Luismi");
            cuentaCredito.setBalance(500.0);
            cuentaCredito.setTipoInteres(0.20);
            cuentaCredito.setLimiteCredito(600.0);

            CuentaDebito cuentaDebito = new CuentaDebito();
            cuentaDebito.setTitular("Luismi");
            cuentaDebito.setBalance(200.0);
            cuentaCredito.setTipoInteres(0.10);
            cuentaDebito.setCargoPorDescubierto(6.5);

            entityManager.persist(cuentaCredito);
            entityManager.persist(cuentaDebito);

            transaction.commit();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }
}
