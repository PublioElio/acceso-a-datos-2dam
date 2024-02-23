package org.example.act_8_1.modelo.entidades;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "empleados", schema = "public", catalog = "empleados")
public class EmpleadosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nif", nullable = false, length = 9)
    private String nif;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "apellido1", nullable = false, length = 100)
    private String apellido1;
    @Basic
    @Column(name = "apellido2", nullable = true, length = 100)
    private String apellido2;
    @Basic
    @Column(name = "id_departamento", nullable = true)
    private Integer idDepartamento;
    @ManyToOne
    @JoinColumn(name = "id_departamento", referencedColumnName = "id")
    private DepartamentosEntity departamentosByIdDepartamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

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
        EmpleadosEntity that = (EmpleadosEntity) o;
        return id == that.id && Objects.equals(nif, that.nif) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(idDepartamento, that.idDepartamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nif, nombre, apellido1, apellido2, idDepartamento);
    }

    public DepartamentosEntity getDepartamentosByIdDepartamento() {
        return departamentosByIdDepartamento;
    }

    public void setDepartamentosByIdDepartamento(DepartamentosEntity departamentosByIdDepartamento) {
        this.departamentosByIdDepartamento = departamentosByIdDepartamento;
    }
}
