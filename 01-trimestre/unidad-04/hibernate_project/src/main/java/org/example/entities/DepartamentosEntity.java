package org.example.entities;

public class DepartamentosEntity {
    private int depno;
    private String nombre;
    private String ubicacion;

    public int getDepno() {
        return depno;
    }

    public void setDepno(int depno) {
        this.depno = depno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartamentosEntity that = (DepartamentosEntity) o;

        if (depno != that.depno) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (ubicacion != null ? !ubicacion.equals(that.ubicacion) : that.ubicacion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = depno;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (ubicacion != null ? ubicacion.hashCode() : 0);
        return result;
    }
}
