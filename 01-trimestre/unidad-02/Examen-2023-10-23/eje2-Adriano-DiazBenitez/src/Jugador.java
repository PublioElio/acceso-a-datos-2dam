import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement

// Defining order
@XmlType(propOrder = { "nombre", "apodo", "puesto", "dorsal", "descripcion"})
public class Jugador implements Serializable, Comparable<Jugador> {

    private String nombre;
    private String apodo;
    private String puesto;
    private int dorsal;
    private String descripcion;

    public Jugador(String nombre, String apodo, String puesto, int dorsal, String descripcion){
        this.nombre = nombre;
        this.apodo = apodo;
        this.puesto = puesto;
        this.dorsal = dorsal;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", apodo='" + apodo + '\'' +
                ", puesto='" + puesto + '\'' +
                ", dorsal=" + dorsal +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return dorsal == jugador.dorsal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dorsal);
    }

    @Override
    public int compareTo(Jugador o) {
        return 0;
    }
}
