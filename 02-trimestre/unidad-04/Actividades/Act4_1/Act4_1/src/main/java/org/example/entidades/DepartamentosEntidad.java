package org.example.entidades;

import java.util.Collection;
import java.util.Objects;

public class DepartamentosEntidad {
    private int id;
    private String nombre;
    private double presupuesto;
    private double gastos;
    private Collection<EmpleadosEntidad> empleadosById;

    public DepartamentosEntidad(int id, String nombre, double presupuesto, double gastos) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.gastos = gastos;
    }

    public DepartamentosEntidad() {
    }

    public DepartamentosEntidad(String nombre, double presupuesto, double gastos) {
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.gastos = gastos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartamentosEntidad that = (DepartamentosEntidad) o;
        return id == that.id && Double.compare(presupuesto, that.presupuesto) == 0 && Double.compare(gastos, that.gastos) == 0 && Objects.equals(nombre, that.nombre);
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() +
                "; Departamento: " + this.getNombre() +
                "; Gastos: " + this.getGastos() +
                "; Presupuesto: " + this.getPresupuesto();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, presupuesto, gastos);
    }

    public Collection<EmpleadosEntidad> getEmpleadosById() {
        return empleadosById;
    }

    public void setEmpleadosById(Collection<EmpleadosEntidad> empleadosById) {
        this.empleadosById = empleadosById;
    }
}
