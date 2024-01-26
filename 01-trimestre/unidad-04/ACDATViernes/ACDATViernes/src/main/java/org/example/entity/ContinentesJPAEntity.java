package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "continentes", schema = "public", catalog = "ACDATJPA")
public class ContinentesJPAEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idContinente", nullable = false)
    private int idContinente;
    @Basic
    @Column(name = "nombreContinente", nullable = true, length = 100)
    private String nombreContinente;

    public int getidContinente() {
        return idContinente;
    }

    public void setidContinente(int idContinente) {
        this.idContinente = idContinente;
    }

    public String getNombreContinente() {
        return nombreContinente;
    }

    public void setNombreContinente(String nombreContinente) {
        this.nombreContinente = nombreContinente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContinentesJPAEntity that = (ContinentesJPAEntity) o;

        if (idContinente != that.idContinente) return false;
        if (nombreContinente != null ? !nombreContinente.equals(that.nombreContinente) : that.nombreContinente != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idContinente;
        result = 31 * result + (nombreContinente != null ? nombreContinente.hashCode() : 0);
        return result;
    }
}
