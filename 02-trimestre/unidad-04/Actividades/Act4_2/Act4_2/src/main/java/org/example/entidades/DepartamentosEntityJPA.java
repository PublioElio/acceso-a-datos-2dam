package org.example.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "departamentos", schema = "public", catalog = "empleados")
public class DepartamentosEntityJPA {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "presupuesto", nullable = false, precision = 0)
    private double presupuesto;
    @Basic
    @Column(name = "gastos", nullable = false, precision = 0)
    private double gastos;

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

        DepartamentosEntityJPA that = (DepartamentosEntityJPA) o;

        if (id != that.id) return false;
        if (Double.compare(presupuesto, that.presupuesto) != 0) return false;
        if (Double.compare(gastos, that.gastos) != 0) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        temp = Double.doubleToLongBits(presupuesto);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(gastos);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
