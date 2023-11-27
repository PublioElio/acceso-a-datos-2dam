package org.example.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "empleados", schema = "public", catalog = "empleados")
public class Empleadosjpa {
    private int id;
    private String nif;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Integer idDepartamento;
    private Departamentosjpa departamentosByIdDepartamento;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nif", nullable = false, length = 9)
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellido1", nullable = false, length = 100)
    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    @Basic
    @Column(name = "apellido2", nullable = true, length = 100)
    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    @Basic
    @Column(name = "id_departamento", nullable = true)
    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empleadosjpa that = (Empleadosjpa) o;

        if (id != that.id) return false;
        if (nif != null ? !nif.equals(that.nif) : that.nif != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellido1 != null ? !apellido1.equals(that.apellido1) : that.apellido1 != null) return false;
        if (apellido2 != null ? !apellido2.equals(that.apellido2) : that.apellido2 != null) return false;
        if (idDepartamento != null ? !idDepartamento.equals(that.idDepartamento) : that.idDepartamento != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nif != null ? nif.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellido1 != null ? apellido1.hashCode() : 0);
        result = 31 * result + (apellido2 != null ? apellido2.hashCode() : 0);
        result = 31 * result + (idDepartamento != null ? idDepartamento.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_departamento", referencedColumnName = "id")
    public Departamentosjpa getDepartamentosByIdDepartamento() {
        return departamentosByIdDepartamento;
    }

    public void setDepartamentosByIdDepartamento(Departamentosjpa departamentosByIdDepartamento) {
        this.departamentosByIdDepartamento = departamentosByIdDepartamento;
    }
}
