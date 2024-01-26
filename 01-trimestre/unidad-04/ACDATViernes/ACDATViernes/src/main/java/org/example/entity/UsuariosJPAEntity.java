package org.example.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "usuarios", schema = "public", catalog = "ACDATJPA")
public class UsuariosJPAEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUsuario", nullable = false)
    private long idUsuario;
    @Basic
    @Column(name = "nombreUsusario", nullable = true, length = 48)
    private String nombreUsuario;

    @Basic
    @Column(name = "fechaCumpleanyos", nullable = true, length = 100)
    private Date fechaCumpleanyos;

    public long getidUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idContinente) {
        this.idUsuario = idContinente;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuariosJPAEntity that = (UsuariosJPAEntity) o;

        if (idUsuario != that.idUsuario) return false;
        if (nombreUsuario != null ? !nombreUsuario.equals(that.nombreUsuario) : that.nombreUsuario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) idUsuario;
        result = 31 * result + (nombreUsuario != null ? nombreUsuario.hashCode() : 0);
        return result;
    }
}
