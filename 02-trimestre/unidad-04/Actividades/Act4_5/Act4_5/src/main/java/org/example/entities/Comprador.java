    package org.example.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "compradores", schema = "public", catalog = "ExamenDiciembre")
public class Comprador {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idComprador", nullable = false)
    private int idComprador;
    @Basic
    @Column(name = "Nombre", length = 48)
    private String nombre;
    @Basic
    @Column(name = "Telefono")
    private String telefono;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "venta",
            joinColumns = @JoinColumn(name = "id_comprador"),
            inverseJoinColumns = @JoinColumn(name = "id_articulo"))
    private Set<Articulo> articulos = new HashSet<>();

    public Comprador() {
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(Set<Articulo> articulos) {
        this.articulos = articulos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comprador comprador = (Comprador) o;
        return idComprador == comprador.idComprador && Objects.equals(nombre, comprador.nombre) && Objects.equals(telefono, comprador.telefono) && Objects.equals(articulos, comprador.articulos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComprador, nombre, telefono, articulos);
    }

    @Override
    public String toString() {
        return "Comprador{" +
                "idComprador=" + idComprador +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", articulos=" + articulos +
                '}';
    }
}
