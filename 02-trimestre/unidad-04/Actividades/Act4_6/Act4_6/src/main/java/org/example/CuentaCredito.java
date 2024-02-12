package org.example;

import jakarta.persistence.Entity;

@Entity
public class CuentaCredito extends CuentaBancaria {
    private double limiteCredito;

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
}
