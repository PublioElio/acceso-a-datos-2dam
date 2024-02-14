package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class CuentaDebito extends Cuenta {
    private Double cargoPorDescubierto;

    public Double getCargoPorDescubierto() {
        return cargoPorDescubierto;
    }

    public void setCargoPorDescubierto(Double cargoPorDescubierto) {
        this.cargoPorDescubierto = cargoPorDescubierto;
    }
}
