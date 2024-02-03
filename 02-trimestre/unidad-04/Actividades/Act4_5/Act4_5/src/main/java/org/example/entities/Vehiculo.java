package org.example.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "vehiculo", schema = "public", catalog = "ExamenDiciembre")
public class Vehiculo {

    // La anotación @Id señala que este atributo es la clave primaria
    @Id
    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "color", nullable = false)
    private String color;

    @Id
    @Column(name = "matricula", nullable = false)
    private String matricula;

    @Column(name = "fechaMatriculacion", nullable = false)
    private Date fechaMatriculacion;

    @ManyToOne
    @JoinColumn(name = "nombreComercial", foreignKey = @ForeignKey(name="NOMBRE_COMERCIAL_FK"))
    private Concesionario concesionario;

    public Vehiculo() {
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(Date fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(marca, vehiculo.marca) && Objects.equals(modelo, vehiculo.modelo) && Objects.equals(color, vehiculo.color) && Objects.equals(matricula, vehiculo.matricula) && Objects.equals(fechaMatriculacion, vehiculo.fechaMatriculacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marca, modelo, color, matricula, fechaMatriculacion);
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", matricula='" + matricula + '\'' +
                ", fechaMatriculacion=" + fechaMatriculacion +
                '}';
    }
}
