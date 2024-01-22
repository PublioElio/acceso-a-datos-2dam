package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "continentes", schema = "public", catalog = "ACDATJPA")
public class ContinentesJPAEntity {
    private int idContinente;
    private String nombreContinente;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idContinente", nullable = false)
    public int getIdContinente() {
        return idContinente;
    }

    public void setIdContiente(int idContiente) {
        this.idContinente = idContiente;
    }

    @Basic
    @Column(name = "nombreContinente", nullable = true, length = 100)
    public String getNombreContinente() {
        return nombreContinente;
    }

    public void setNombreContiente(String nombreContiente) {
        this.nombreContinente = nombreContiente;
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
