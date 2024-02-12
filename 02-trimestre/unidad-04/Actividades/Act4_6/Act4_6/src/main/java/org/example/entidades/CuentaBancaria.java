package org.example.entidades;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CuentaBancaria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titular;
    private double balance;
    private double tipoInteres;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getTipoInteres() {
        return tipoInteres;
    }
    public void setTipoInteres(double tipoInteres) {
        this.tipoInteres = tipoInteres;
    }
}

