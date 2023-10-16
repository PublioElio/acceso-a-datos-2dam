import java.io.*;

/**
 * Esta clase lee archivos y los copia en otro archivo especificado, si este no existe, lo crea.
 * Puede hacerlo bit a bit o en buffers de 128 bytes.
 */
public class EjemploDeCopiaDeDatos {

    private String entrada;

    private String salida;

    private final int BUFFER = 128;

    public EjemploDeCopiaDeDatos(String entrada, String salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    public EjemploDeCopiaDeDatos(String entrada) {
        this.entrada = entrada;
        this.salida = entrada.substring(0, entrada.lastIndexOf('.'))
                + " copia" + entrada.substring(entrada.lastIndexOf('.'));
    }

    /**
     * Este método copia bit a bit el contenido de un archivo en otro, en caso de que el segundo no exista, lo crea,
     * añadiendo la palabra "copia" al nombre anterior.
     *
     * @return true si no hay ningún error y ha conseguido copiar el archivo
     */
    public boolean copiarBitABit() {
        boolean copiado = false;
        // he utilizado el try-catch con recursos para no tener que cerrar los flujos en el bloque finally
        try (FileInputStream in = new FileInputStream(entrada);
             FileOutputStream out = new FileOutputStream(salida)) {
            int bit;
            while ((bit = in.read()) != -1) {
                out.write(bit);
            }
            copiado = true;
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
        return copiado;
    }

    /**
     * Este método copia en bloques de 128 bytes el contenido de un archivo en otro archivo, en caso de que el segundo
     * no exista, lo crea, añadiendo la palabra "copia" al nombre anterior.
     *
     * @return true si no hay ningún error y ha conseguido copiar el archivo
     */
    public boolean copiarBloques128() {
        boolean copiado = false;
        try (FileInputStream in = new FileInputStream(entrada); FileOutputStream out = new FileOutputStream(salida)) {
            // creamos un buffer de lectura variable
            byte[] buffer = new byte[BUFFER];
            // en esta variable guardaremos el número de bytes leídos, que pueden no ser 128
            int bytesRead;
            // mientras no lleguemos a final de archivo
            while ((bytesRead = in.read(buffer)) != -1) {
                // escribimos lo que hemos leído en el buffer, desde el primer byte ('0') al total leído
                out.write(buffer, 0, bytesRead);
            }
            copiado = true;
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
        return copiado;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

}
