import java.io.Serializable;
import java.util.Objects;

/**
 * Esta es una clase simple que guarda un contacto para una agenda
 */
public class Contacto implements Serializable, Comparable<Contacto> {

    private String nombre;
    private String apellido;
    private String tlfn;
    private String correo;
    private String descripcion;

    public Contacto(String nombre, String apellido, String tlfn, String correo, String descripcion){
        this.nombre = nombre;
        this.apellido = apellido;
        this.tlfn = tlfn;
        this.correo = correo;
        this.descripcion = descripcion;
    }
    public Contacto(String nombre, String apellido, String tlfn){
        this.nombre = nombre;
        this.apellido = apellido;
        this.tlfn = tlfn;
        this.correo = "";
        this.descripcion = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return Objects.equals(nombre.toLowerCase(), contacto.nombre.toLowerCase())
                && Objects.equals(apellido.toLowerCase(), contacto.apellido.toLowerCase())
                && Objects.equals(tlfn, contacto.tlfn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, tlfn);
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " " + apellido + "\nTeléfono: "
                + tlfn + "\nCorreo:" + correo + "\nDescripción: " + descripcion + "\n";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTlfn() {
        return tlfn;
    }

    public void setTlfn(String tlfn) {
        this.tlfn = tlfn;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int compareTo(Contacto otroContacto) {
        int comparacionPorNombre = this.nombre.compareTo(otroContacto.nombre);
        if (comparacionPorNombre == 0) {
            return this.apellido.compareTo(otroContacto.apellido);
        }
        return comparacionPorNombre;
    }
}
