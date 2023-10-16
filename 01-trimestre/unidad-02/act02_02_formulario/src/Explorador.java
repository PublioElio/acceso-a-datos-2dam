import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Explorador extends File {

    public Explorador(String pathname) {
        super(pathname);
    }

    /**
     * Esta función te devuelve un listado de los directorios que hay en el atributo pathname (siempre que sean ficheros
     * y no estén ocultos)
     *
     */
    public String[] listarDirectorios() {
        List<String> s = new ArrayList<>();
        File[] ficheros = new File(this.getPath()).listFiles();

        assert ficheros != null;
        for (File f : ficheros) {
            if (f.isDirectory() && !f.isHidden()) {
                s.add(f.getName());
            }
        }
        return s.toArray(new String[0]);
    }

    /**
     * Esta función te devuelve un array de Strings con la lista de los directorios raíz del equipo
     *
     * @return un listado de los directorios raíz del equipo
     */
    public String[] getRootList() {
        File[] roots = File.listRoots(); // esta es la lista de unidades del equipo
        String[] driveNames = new String[roots.length]; // la vamos a guardar en este array de Strings
        // iteramos por el listado de unidades, guardándolas en el array
        for (int i = 0; i < roots.length; i++) {
            driveNames[i] = roots[i].getPath();
        }
        return driveNames;
    }

    public String[] listarArchivos(){
        List<String> s = new ArrayList<>();
        File[] ficheros = new File(this.getPath()).listFiles();
        assert ficheros != null;
        for (File f : ficheros) {
            if (!f.isDirectory() && !f.isHidden()) {
                s.add(f.getName());
            }
        }
        return s.toArray(new String[0]);
    }
}
