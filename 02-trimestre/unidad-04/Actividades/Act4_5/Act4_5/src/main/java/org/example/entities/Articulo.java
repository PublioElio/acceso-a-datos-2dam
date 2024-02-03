package org.example.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "articulos", schema = "public", catalog = "ExamenDiciembre")
public class Articulo {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idArticulo", nullable = false)
    private int idArticulo;
    @Basic
    @Column(name = "descripcion", length = 100)
    private String descripcion;
    @Basic
    @Column(name = "precioVenta")
    private long precioVenta;

    @ManyToMany(mappedBy = "articulos")
    private Set<Comprador> compradores = new HashSet<>();

    public Articulo() {
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(long precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Set<Comprador> getCompradores() {
        return compradores;
    }

    public void setCompradores(Set<Comprador> compradores) {
        this.compradores = compradores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Articulo articulo = (Articulo) o;
        return idArticulo == articulo.idArticulo && precioVenta == articulo.precioVenta && Objects.equals(descripcion, articulo.descripcion) && Objects.equals(compradores, articulo.compradores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArticulo, descripcion, precioVenta, compradores);
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "idArticulo=" + idArticulo +
                ", descripcion='" + descripcion + '\'' +
                ", precioVenta=" + precioVenta +
                ", compradores=" + compradores +
                '}';
    }
}




