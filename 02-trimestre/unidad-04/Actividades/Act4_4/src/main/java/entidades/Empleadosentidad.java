package entidades;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "empleados", schema = "empleados")
public class Empleadosentidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "empno", nullable = false)
    private int empno;
    @Basic
    @Column(name = "nombre", nullable = true, length = 10)
    private String nombre;
    @Basic
    @Column(name = "puesto", nullable = true, length = 15)
    private String puesto;
    @Basic
    @Column(name = "depno", nullable = true)
    private Integer depno;

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Integer getDepno() {
        return depno;
    }

    public void setDepno(Integer depno) {
        this.depno = depno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleadosentidad that = (Empleadosentidad) o;
        return empno == that.empno && Objects.equals(nombre, that.nombre) && Objects.equals(puesto, that.puesto) && Objects.equals(depno, that.depno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empno, nombre, puesto, depno);
    }
}
