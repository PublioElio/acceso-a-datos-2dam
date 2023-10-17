import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Esta clase es una estructura que guarda una serie de contactos
 */
public class Agenda implements Serializable {

    private final String ruta; // La ruta es dónde guardaremos el archivo con la agenda
    private final Set<Contacto> agenda; // He escogido el TreeSet porque así no admite elementos repetidos y además, siempre estarán ordenados

    public Agenda(String ruta){
        this.ruta = ruta;
        this.agenda = new TreeSet<>();
    }

    /**
     * Esta función añade un nuevo contacto a la agenda
     *
     * @param nuevo el nuevo contacto a añadir
     */
    public void nuevoContacto(Contacto nuevo){
        agenda.add(nuevo);
    }

    /**
     * Esta función borra un contacto de la agenda
     *
     * @param borrar el contacto a borrar
     */
    public void borrarContacto(Contacto borrar){
        agenda.remove(borrar);
    }

    /**
     * Esta función devuelve una cadena de caracteres con todos los contactos que coinciden, en nombre y apellidos
     * completo. Se contempla que un contacto pueda estar repetido, pero con diferentes teléfonos.
     *
     * @param nombreCompleto el nombre y apellidos del contacto a buscar, separados por un espacio
     * @return la nueva lista con las ocurrencias
     */
    public String buscarPorNombreCompleto(String nombreCompleto) {
        String nombreApellido, encontrado = "";
        for (Contacto contacto : agenda) {
            nombreApellido = contacto.getNombre() + " " + contacto.getApellido();
            if (nombreApellido.equalsIgnoreCase(nombreCompleto)) {
                encontrado += contacto + "\n";
            }
        }
        if(encontrado.isEmpty()){
            encontrado = null;
        }
        return encontrado; // Si no se encontró ningún contacto con el nombre completo devolverá nulo
    }

    /**
     * Esta función devuelve una cadena de caracteres con el listado de contactos que comparten el mismo teléfono.
     *
     * @param tlfn el teléfono que hay que buscar
     * @return un listado (cadena de caracteres) con los contactos que tienen un mismo teléfono
     */
    public String buscarPorTlfn(String tlfn) {
        String encontrado = "";
        for (Contacto contacto : agenda) {
            if (contacto.getTlfn().equalsIgnoreCase(tlfn)) {
                encontrado += contacto + "\n";
            }
        }
        if(encontrado.isEmpty()){
            encontrado = null;
        }
        return encontrado; // Si no se encontró ningún contacto con ese teléfono, devolverá nulo
    }

    /**
     * Devuelve una cadena de caracteres con la lista de los contactos de la agenda
     *
     * @return la lista de los contactos
     */
    @Override
    public String toString() {
        StringBuilder listado = new StringBuilder();
        for (Contacto contacto : agenda) {
            listado.append(contacto.toString());
        }
    return listado.toString();
    }

    public String getRuta() {
        return ruta;
    }

}
