package org.example;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name="jugador")
@XmlType(propOrder ={"nombre","apodo","dorsal","puesto","descripcion"})
public class Jugador implements Serializable {
    private String nombre;
    private String apodo;
    private int dorsal;
    private String puesto;
    private String descripcion;

    public Jugador(String nombre, String apodo, int dorsal, String puesto, String descripcion) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.dorsal = dorsal;
        this.puesto = puesto;
        this.descripcion = descripcion;
    }

    public Jugador(String nombre, String apodo, int dorsal) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.dorsal = dorsal;
        this.puesto = null;
        this.descripcion = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jugador jugador)) return false;
        return dorsal == jugador.dorsal && Objects.equals(nombre.toLowerCase(), jugador.nombre.toLowerCase())
                && Objects.equals(apodo.toLowerCase(), jugador.apodo.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apodo, dorsal);
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

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + nombre + "; apodo: " + apodo + "; dorsal: " + dorsal +
                "; puesto: " + puesto + "; descripción: " + descripcion + ".\n";
    }
}
