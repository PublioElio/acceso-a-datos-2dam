package org.example.act_8_1_biblioteca.modelo.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "libros", schema = "public", catalog = "biblioteca")
public class EntidadLibros {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "isbn", nullable = false, length = 13)
    private String isbn;
    @Basic
    @Column(name = "titulo", nullable = false, length = 90)
    private String titulo;
    @Basic
    @Column(name = "copias", nullable = true)
    private Integer copias;
    @Basic
    @Column(name = "editorial", nullable = true, length = 60)
    private String editorial;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCopias() {
        return copias;
    }

    public void setCopias(Integer copias) {
        this.copias = copias;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntidadLibros that = (EntidadLibros) o;

        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (titulo != null ? !titulo.equals(that.titulo) : that.titulo != null) return false;
        if (copias != null ? !copias.equals(that.copias) : that.copias != null) return false;
        if (editorial != null ? !editorial.equals(that.editorial) : that.editorial != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (copias != null ? copias.hashCode() : 0);
        result = 31 * result + (editorial != null ? editorial.hashCode() : 0);
        return result;
    }
}
