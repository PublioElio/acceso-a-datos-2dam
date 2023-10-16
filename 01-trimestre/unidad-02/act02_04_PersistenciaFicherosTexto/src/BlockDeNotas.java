import java.io.*;

public class BlockDeNotas {

    private String ruta;

    public BlockDeNotas(String ruta) {
        this.ruta = ruta;
    }

    /**
     * Este método escribe en un nuevo documento de texto, antes pregunta al usuario si quiere añadir contenido a un
     * documento ya creado. En caso negativo, lo machaca.
     *
     * @return true si no hay ningún error
     */
    public boolean escribir() {
        boolean escrito = false, append = noSobreescribir(); // en esta asignación el usuario nos dirá si quiere añadir al archivo existente
        String mensajeEscritura = "Escriba una frase y pulse <enter>. " +
                "Pulse <enter> en una línea en blanco para finalizar.\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, append))) {
            int numLinea = contarLineas(append); // contamos las líneas del archivo existente si el usuario quiere añadir

            String line = EntradaTeclado.pedirCadena(mensajeEscritura);

            while (!line.isEmpty()) { // mientras no devuelva una línea en blanco, seguimos introduciendo líneas en el archivo
                writer.write(numLinea + ". " + line);
                writer.newLine();
                numLinea++;
                escrito = true;
                line = EntradaTeclado.pedirCadena(mensajeEscritura);
            }

        } catch (IOException e) {
            System.out.println("Error de lectura/escritura: " + e.getMessage());
            escrito = false;
        }

        return escrito;

    }

    /**
     * Esta función pregunta al usuario si quiere o no sobreescribir el archivo existente
     *
     * @return false si no quiere sobreescribir el archivo
     */
    private boolean noSobreescribir() {
        boolean append = false;
        if (archivoExiste()) {
            char respuesta; // esta es la respuesta que nos va a dar el usuario
            String error = "Escriba una respuesta válida (s/n)"; // esto es un mensaje de error por si escribe algo no válido
            boolean esValida; // con este booleano comprobamos que la respuesta del usuario es válida

            do { // en este bucle vamos a preguntar al usuario hasta que nos dé una respuesta válida
                respuesta = EntradaTeclado.pedirChar("El archivo existe ¿Desea sobreescribirlo? (s/n): ", error);
                esValida = respuesta == 'N' || respuesta == 'S';
                if (!esValida) {
                    System.out.println(error);
                }
            } while (!esValida);

            append = respuesta == 'N'; // si es 'N', no añadimos al archivo existente
        }
        return append;
    }

    /**
     * Esta función cuenta el número de líneas del documento, siempre que el usuario quiera añadir más.
     *
     * @param append este booleano indica si el usuario quiere añadir más líneas al texto
     * @return el número total de líneas del documento
     */
    private int contarLineas(boolean append) {
        int numLinea = 1; // siempre empezamos por la línea uno, de modo que, si el usuario no quiere añadir, es la línea por defecto

        if (append) {

            try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
                while (reader.readLine() != null) {
                    numLinea++;
                }
            } catch (IOException e) {
                System.out.println("Error de lectura/escritura: " + e.getMessage());
            }

        }
        return numLinea;
    }

    /**
     * Esta función comprueba si un archivo existe o no
     *
     * @return true en caso de que exista
     */
    private boolean archivoExiste() {
        return new File(this.ruta).exists();
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
