package org.example.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "departamentos", schema = "public", catalog = "empleados")
public class Departamentosjpa {
    private int id;
    private String nombre;
    private double presupuesto;
    private double gastos;

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
    @Column(name = "nombre", nullable = false, length = 100)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "presupuesto", nullable = false, precision = 0)
    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    @Basic
    @Column(name = "gastos", nullable = false, precision = 0)
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

        Departamentosjpa that = (Departamentosjpa) o;

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
