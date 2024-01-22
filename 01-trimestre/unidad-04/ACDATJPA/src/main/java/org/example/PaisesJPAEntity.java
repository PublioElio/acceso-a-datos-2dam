package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "paises", schema = "public", catalog = "ACDATJPA")
public class PaisesJPAEntity {
    private int idPais;
    private String nombrePais;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPais", nullable = false)
    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    @Basic
    @Column(name = "nombrePais", nullable = true, length = 100)
    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaisesJPAEntity that = (PaisesJPAEntity) o;

        if (idPais != that.idPais) return false;
        if (nombrePais != null ? !nombrePais.equals(that.nombrePais) : that.nombrePais != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPais;
        result = 31 * result + (nombrePais != null ? nombrePais.hashCode() : 0);
        return result;
    }
}
