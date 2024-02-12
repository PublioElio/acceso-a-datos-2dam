package org.example.entidades;

import jakarta.persistence.Entity;

@Entity
public class CuentaDebito extends CuentaBancaria {
    private double cargoPorDescubierto;

    public double getCargoPorDescubierto() {
        return cargoPorDescubierto;
    }

    public void setCargoPorDescubierto(double cargoPorDescubierto) {
        this.cargoPorDescubierto = cargoPorDescubierto;
    }
}
