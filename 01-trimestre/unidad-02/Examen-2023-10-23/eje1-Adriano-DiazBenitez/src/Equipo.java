import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class Equipo implements Serializable {

    private final String ruta;
    private final Set<Jugador> equipo;

    public Equipo(String ruta){
        this.ruta = ruta;
        this.equipo = new TreeSet<>();
    }

    public void nuevoJugador(Jugador nuevo){
        equipo.add(nuevo);
    }

    public String buscarNombre(String nombre) {
        String encontrado = "";
        for (Jugador jugador : equipo) {
            if (jugador.getNombre().equalsIgnoreCase(nombre)) {
                encontrado += jugador + "\n";
            }
        }
        if(encontrado.isEmpty()){
            encontrado = null;
        }
        return encontrado;
    }

    public String buscarApodo(String apodo) {
        String encontrado = "";
        for (Jugador jugador : equipo) {
            if (jugador.getApodo().equalsIgnoreCase(apodo)) {
                encontrado += jugador + "\n";
            }
        }
        if(encontrado.isEmpty()){
            encontrado = null;
        }
        return encontrado;
    }

    public String buscarDorsal(int dorsal) {
        String encontrado = "";
        for (Jugador jugador : equipo) {
            if (jugador.getDorsal() == dorsal) {
                encontrado += jugador + "\n";
            }
        }
        if(encontrado.isEmpty()){
            encontrado = null;
        }
        return encontrado;
    }


    @Override
    public String toString() {
        StringBuilder listado = new StringBuilder();
        for (Jugador jugador : equipo) {
            listado.append(jugador.toString());
        }
        return listado.toString();
    }

    public String getRuta() {
        return ruta;
    }

}

