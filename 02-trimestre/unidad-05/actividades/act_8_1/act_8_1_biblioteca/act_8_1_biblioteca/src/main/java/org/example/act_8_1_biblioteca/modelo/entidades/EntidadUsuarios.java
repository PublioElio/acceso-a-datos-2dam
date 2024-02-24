package org.example.act_8_1_biblioteca.modelo.entidades;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "usuarios", schema = "public", catalog = "biblioteca")
public class EntidadUsuarios {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo", nullable = false, length = 8)
    private String codigo;
    @Basic
    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;
    @Basic
    @Column(name = "apellidos", nullable = false, length = 25)
    private String apellidos;
    @Basic
    @Column(name = "fechanacimiento", nullable = true)
    private Date fechanacimiento;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntidadUsuarios that = (EntidadUsuarios) o;

        if (codigo != null ? !codigo.equals(that.codigo) : that.codigo != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (fechanacimiento != null ? !fechanacimiento.equals(that.fechanacimiento) : that.fechanacimiento != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo != null ? codigo.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (fechanacimiento != null ? fechanacimiento.hashCode() : 0);
        return result;
    }
}
