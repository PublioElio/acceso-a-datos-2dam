package org.example.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String titular;
    private Double balance;

    private Double tipoInteres;

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTipoInteres() {
        return tipoInteres;
    }

    public void setTipoInteres(Double tipoInteres) {
        this.tipoInteres = tipoInteres;
    }

}

