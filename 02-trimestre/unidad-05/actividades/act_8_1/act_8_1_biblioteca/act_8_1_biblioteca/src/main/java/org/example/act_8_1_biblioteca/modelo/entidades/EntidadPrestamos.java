package org.example.act_8_1_biblioteca.modelo.entidades;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "prestamos", schema = "public", catalog = "biblioteca")
public class EntidadPrestamos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "fechaprestamo", nullable = false)
    private Date fechaprestamo;
    @Basic
    @Column(name = "fechadevolucion", nullable = true)
    private Date fechadevolucion;
    @Basic
    @Column(name = "libro", nullable = false, length = 13)
    private String libro;
    @Basic
    @Column(name = "usuario", nullable = false, length = 13)
    private String usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(Date fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntidadPrestamos that = (EntidadPrestamos) o;

        if (id != that.id) return false;
        if (fechaprestamo != null ? !fechaprestamo.equals(that.fechaprestamo) : that.fechaprestamo != null)
            return false;
        if (fechadevolucion != null ? !fechadevolucion.equals(that.fechadevolucion) : that.fechadevolucion != null)
            return false;
        if (libro != null ? !libro.equals(that.libro) : that.libro != null) return false;
        if (usuario != null ? !usuario.equals(that.usuario) : that.usuario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fechaprestamo != null ? fechaprestamo.hashCode() : 0);
        result = 31 * result + (fechadevolucion != null ? fechadevolucion.hashCode() : 0);
        result = 31 * result + (libro != null ? libro.hashCode() : 0);
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        return result;
    }
}
