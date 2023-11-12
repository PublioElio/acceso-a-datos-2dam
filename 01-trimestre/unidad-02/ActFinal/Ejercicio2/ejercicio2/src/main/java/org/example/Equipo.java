package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="equipo")
@XmlType(propOrder = {"nombre","equipo"})
public class Equipo implements Serializable {

    private List<Jugador> equipo = new ArrayList<>();
    private String nombre;

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Equipo() {
        this.nombre = null;
    }

    public boolean anyadirJugador(Jugador nuevo) {
        return equipo.add(nuevo);
    }

    public Jugador buscarJugador(String nombre, String apodo, int dorsal) {
        Jugador jugadorBuscado = new Jugador(nombre, apodo, dorsal);
        for (Jugador jugador : equipo) {
            if (jugador.equals(jugadorBuscado)) {
                return jugador;
            }
        }
        return null;
    }

    public Jugador buscarJugador(Jugador jugadorBuscado){
        for(Jugador jugador : equipo){
            if(jugador.equals(jugadorBuscado)){
                return jugador;
            }
        }
        return null;
    }

    public boolean borrarJugador(Jugador borrar) {
        return equipo.remove(borrar);
    }

    @XmlElement(name="nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElementWrapper(name="equipo")
    @XmlElement(name="jugador")
    public List<Jugador> getEquipo() {
        return equipo;
    }

    public void setEquipo(List<Jugador> equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        String s = this.getClass().getSimpleName() + " " + nombre + ":\n";
        for (Jugador jugador : equipo) {
            s += jugador.toString();
        }
        return s;
    }
}
