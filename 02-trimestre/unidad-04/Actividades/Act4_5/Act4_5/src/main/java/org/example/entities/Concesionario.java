package org.example.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "concesionario", schema = "public", catalog = "ExamenDiciembre")
public class Concesionario {

    // La anotación @Id señala que este atributo es la clave primaria
    @Id
    @Column(name = "nombreComercial", nullable = false)
    private String nombreComercial;

    @Column(name = "nombreEmpresarial")
    private String nombreEmpresarial;

    @Column(name = "direccionConcesionario", nullable = false)
    private String direccionConcesionario;

    @Column(name = "numTrabajadores")
    private int numTrabajadores;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "concesionario", cascade = CascadeType.ALL)
    private List<Vehiculo> vehiculos;

    public Concesionario() {
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombreEmpresarial() {
        return nombreEmpresarial;
    }

    public void setNombreEmpresarial(String nombreEmpresarial) {
        this.nombreEmpresarial = nombreEmpresarial;
    }

    public String getDireccionConcesionario() {
        return direccionConcesionario;
    }

    public void setDireccionConcesionario(String direccionConcesionario) {
        this.direccionConcesionario = direccionConcesionario;
    }

    public int getNumTrabajadores() {
        return numTrabajadores;
    }

    public void setNumTrabajadores(int numTrabajadores) {
        this.numTrabajadores = numTrabajadores;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concesionario that = (Concesionario) o;
        return numTrabajadores == that.numTrabajadores && Objects.equals(nombreComercial, that.nombreComercial) && Objects.equals(nombreEmpresarial, that.nombreEmpresarial) && Objects.equals(direccionConcesionario, that.direccionConcesionario) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreComercial, nombreEmpresarial, direccionConcesionario, numTrabajadores, email);
    }

    @Override
    public String toString() {
        return "Concesionario{" +
                "nombreComercial='" + nombreComercial + '\'' +
                ", nombreEmpresarial='" + nombreEmpresarial + '\'' +
                ", direccionConcesionario='" + direccionConcesionario + '\'' +
                ", numTrabajadores=" + numTrabajadores +
                ", email='" + email + '\'' +
                '}';
    }
}
