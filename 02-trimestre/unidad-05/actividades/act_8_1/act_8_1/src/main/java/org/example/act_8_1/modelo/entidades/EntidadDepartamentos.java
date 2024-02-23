package org.example.act_8_1.modelo.entidades;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "departamentos", schema = "public", catalog = "empleadosinstituto")
public class EntidadDepartamentos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "depno", nullable = false)
    private int depno;
    @Basic
    @Column(name = "nombre", nullable = true, length = 14)
    private String nombre;
    @Basic
    @Column(name = "ubicacion", nullable = true, length = 13)
    private String ubicacion;
    @OneToMany(mappedBy = "departamentosByDepno")
    private Collection<EntidadEmpleados> empleadosByDepno;

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
        EntidadDepartamentos that = (EntidadDepartamentos) o;
        return depno == that.depno && Objects.equals(nombre, that.nombre) && Objects.equals(ubicacion, that.ubicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depno, nombre, ubicacion);
    }

    public Collection<EntidadEmpleados> getEmpleadosByDepno() {
        return empleadosByDepno;
    }

    public void setEmpleadosByDepno(Collection<EntidadEmpleados> empleadosByDepno) {
        this.empleadosByDepno = empleadosByDepno;
    }
}
