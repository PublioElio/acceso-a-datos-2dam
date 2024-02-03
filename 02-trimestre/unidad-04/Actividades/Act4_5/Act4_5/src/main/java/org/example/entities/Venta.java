package org.example.entities;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "venta", schema = "public", catalog = "ExamenDiciembre")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @ManyToOne
    @JoinColumn(name = "id_comprador")
    private Comprador comprador;

    @ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    @Column(name = "unidades_vendidas", nullable = false, columnDefinition = "int default 0")
    private int unidadesVendidas;

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(int unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return unidadesVendidas == venta.unidadesVendidas && Objects.equals(idVenta, venta.idVenta) && Objects.equals(comprador, venta.comprador) && Objects.equals(articulo, venta.articulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenta, comprador, articulo, unidadesVendidas);
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", comprador=" + comprador +
                ", articulo=" + articulo +
                ", unidadesVendidas=" + unidadesVendidas +
                '}';
    }
}
