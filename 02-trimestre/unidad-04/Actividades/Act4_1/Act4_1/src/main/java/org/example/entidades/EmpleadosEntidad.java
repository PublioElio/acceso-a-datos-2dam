package org.example.entidades;

import java.util.Objects;

public class EmpleadosEntidad {
    private int id;
    private String nif;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Integer idDepartamento;
    private DepartamentosEntidad departamentosByIdDepartamento;

    public EmpleadosEntidad(int id, String nombre, String apellido1, String apellido2, String nif, int idDepartamento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nif = nif;
        this.idDepartamento = idDepartamento;
    }

    public EmpleadosEntidad(String nombre, String apellido1, String apellido2, String nif, int idDepartamento) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nif = nif;
        this.idDepartamento = idDepartamento;
    }

    public EmpleadosEntidad() {
    }

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
        EmpleadosEntidad that = (EmpleadosEntidad) o;
        return id == that.id && Objects.equals(nif, that.nif) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(idDepartamento, that.idDepartamento);
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() +
                "; Empleado: " + this.getNombre() + " " +
                this.getApellido1() + " " + this.getApellido2() +
                "; DNI: " + this.getNif() +
                "; ID departamento: " + this.getIdDepartamento();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nif, nombre, apellido1, apellido2, idDepartamento);
    }

    public DepartamentosEntidad getDepartamentosByIdDepartamento() {
        return departamentosByIdDepartamento;
    }

    public void setDepartamentosByIdDepartamento(DepartamentosEntidad departamentosByIdDepartamento) {
        this.departamentosByIdDepartamento = departamentosByIdDepartamento;
    }
}
