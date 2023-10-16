import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneradorListasOrdenadas {

    private String ruta1;

    private String ruta2;

    private final String NUEVO_ARCHIVO = "ordenado.txt";

    public GeneradorListasOrdenadas(String ruta1, String ruta2) {
        this.ruta1 = ruta1;
        this.ruta2 = ruta2;
    }

    /**
     * Esta función genera una nueva lista ordenada de nombres a partir de dos documentos de texto.
     *
     * @return true si se ha ejecutado con éxito
     */
    public boolean generarNuevaLista() {
        boolean nuevaListaGenerada = false;

        // Leer los contenidos de los archivos de entrada y almacenarlos en listas
        List<String> contenido1 = leerArchivo(ruta1);
        List<String> contenido2 = leerArchivo(ruta2);

        if (!(contenido1.isEmpty() && contenido2.isEmpty())) { // hay que asegurarse de que ambas listas tengan elementos

            // Unir los contenidos de ambos archivos en una sola lista
            List<String> contenidoUnido = new ArrayList<>(contenido1);
            contenidoUnido.addAll(contenido2);

            // Ordenar la lista alfabéticamente
            Collections.sort(contenidoUnido);

            // Escribir la lista ordenada en el archivo de salida
            nuevaListaGenerada = escribirArchivo(contenidoUnido);
        }
        return nuevaListaGenerada;
    }

    /**
     * Esta función lee el contenido de un archivo línea a línea y lo copia en un ArrayList de Strings.
     *
     * @param rutaArchivo el nombre del archivo (ruta) que se desea leer
     * @return una lista desordenada con el contenido del documento, cada elemento es una línea del documento
     */
    private List<String> leerArchivo(String rutaArchivo) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer del archivo " + rutaArchivo + ": " + e.getMessage());
        }
        return lineas;
    }

    /**
     * Esta función escribe un nuevo archivo con el contenido proporcionado, en este caso un ArrayList de Strings.
     * Cada elemento es copiado en una nueva línea.
     *
     * @param contenido la lista que queremos copiar en el nuevo documento de destino
     * @return true si se ha ejecutado correctamente
     */
    private boolean escribirArchivo(List<String> contenido) {
        boolean escrito = false;
        if (!contenido.isEmpty()) { // si el contenido que recibimos está vacío, no hacemos nada
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(NUEVO_ARCHIVO))) {
                for (String linea : contenido) {
                    writer.write(linea);
                    writer.newLine();
                }
                escrito = true;
            } catch (IOException e) {
                System.out.println("Error al crear nuevo archivo: " + e.getMessage());
            }
        }
        return escrito;
    }

    /**
     * Esta función muestra por consola el contenido del documento proporcionado, en una cantidad de líneas enviadas
     * como parámetro.
     *
     * @param lista     el documento que vamos a leer (lista de nombres)
     * @param numLineas el número de líneas que vamos a mostrar antes de pedir al usuario que pulse una tecla
     */
    public void mostrarContenidoLista(String lista, int numLineas) {
        int contador = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(lista))) {
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line); // mostramos por pantalla cada línea leída
                line = reader.readLine();

                if (contador++ == numLineas) { // vamos contando, si alcanzamos el número de líneas proporcionado, paramos
                    EntradaTeclado.pedirCadena("Pulse <enter> para continuar. ");
                    contador = 1; // reiniciamos el contador
                }
            }

        } catch (IOException e) {
            System.out.println("Error al mostrar contenido del archivo indicado: " + e.getMessage());
        }

    }

    /**
     * Esta función busca la primera ocurrencia de una cadena de texto dentro de un archivo. Si la encuentra, muestra
     * por pantalla la línea dónde la ha encontrado. En caso contrario o de no existir el archivo, devuelve falso.
     *
     * @param cadena  la cadena de caracteres que vamos a buscar en el archivo de texto.
     * @param archivo la ruta del archivo de texto donde vamos a buscar la cadena de caracteres.
     * @return false en caso de no encontrar la cadena.
     */
    public boolean buscarCadena(String cadena, String archivo) {
        boolean encontrada = false;
        if (archivoExiste(archivo) || cadena != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String line = reader.readLine();
                int contadorLineas = 1;
                while (!encontrada && line != null) {
                    encontrada = line.contains(cadena);
                    if (encontrada) {
                        System.out.printf("La primera ocurrencia de la cadena %s se encuentra en la línea %d.\n",
                                cadena, contadorLineas);
                    } else {
                        line = reader.readLine();
                        contadorLineas++;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error de lectura: " + e.getMessage());
            }
        }
        return encontrada;
    }

    /**
     * Este método devuelve true si existe un archivo con ese nombre/ruta
     *
     * @param ruta el nombre del archivo o ruta que queremos encontrar
     * @return true si encuentra el archivo
     */
    private boolean archivoExiste(String ruta) {
        return new File(ruta).exists();
    }

    public String getRuta1() {
        return ruta1;
    }

    public void setRuta1(String ruta1) {
        this.ruta1 = ruta1;
    }

    public String getRuta2() {
        return ruta2;
    }

    public void setRuta2(String ruta2) {
        this.ruta2 = ruta2;
    }
}