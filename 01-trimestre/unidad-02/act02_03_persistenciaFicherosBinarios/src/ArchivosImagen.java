import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ArchivosImagen {
    private String rutaImg;

    public ArchivosImagen(String rutaImg) {
        this.rutaImg = rutaImg;
    }

    /**
     * Esta función te devuelve en String con el tipo de archivo de imagen
     *
     * @return un String
     */
    public String imgType() {
        String type = null;

        try (FileInputStream in = new FileInputStream(rutaImg)) {
            // en el siguiente array de bytes guardaremos los ocho primeros bytes del archivo
            byte[] bytes = new byte[8];
            // en la siguiente variable guardamos el total de bytes leídos, si no son ocho, el archivo no es de imagen
            int bytesRead = in.read(bytes, 0, 8);

            if (bytesRead == 8) {
                if (bytes[0] == 0x42 && bytes[1] == 0x4D) {
                    type = "BMP";
                } else if (bytes[0] == 0x47 && bytes[1] == 0x49) {
                    type = "GIF";
                } else if (bytes[0] == 0x00 && bytes[1] == 0x00) {
                    type = "ICO";
                } else if (bytes[0] == (byte) 0xFF && bytes[1] == (byte) 0xD8) { // hay que hacer casting para evitar errores
                    type = "JPEG";
                } else if (bytes[0] == (byte) 0x89 && bytes[1] == 0x50) {
                    type = "PNG";
                } else {
                    type = "Tipo de archivo no válido.";
                }
            } else {
                type = "Tipo de archivo no válido.";
            }

        } catch (IOException e) {
            System.out.println("Error de entrada: " + e.getMessage());
        }

        return type;
    }

    /**
     * Esta función obtiene los datos de un archivo de tipo .bmp a partir de su cabecera.
     *
     * @return un String con los datos del archivo
     */
    public String analizarBmp() {
        String datosImg = new File(rutaImg).getName() + " no es un archivo BMP.";

        // si es un archivo válido, entrará y obtendremos sus datos
        if (this.imgType().equals("BMP")) {
            try (FileInputStream in = new FileInputStream(rutaImg)) {
                byte[] encabezado = new byte[54]; // un BMP tiene un encabezado de 54 bytes
                in.read(encabezado);

                int tam = calcularDatos(encabezado, 2);
                int ancho = calcularDatos(encabezado, 18);
                int alto = calcularDatos(encabezado, 22);

                // dividimos por ocho el tamaño para sacarlo en bytes y no en bits
                datosImg = "Tamaño: " + (tam / 8) + " bytes, ancho: " + ancho + " pixels, alto: " + alto + " pixels. ";
                datosImg = (encabezado[30] != 0) ? datosImg + "Está comprimido." : datosImg + "No está comprimido.";

            } catch (IOException e) {
                datosImg = "Error al analizar la imagen: " + e.getMessage();
            }
        }
        return datosImg;
    }

    private int calcularDatos(byte[] datos, int inicio) {
        return datos[inicio] + (datos[inicio + 1] * 256) + (datos[inicio + 2] * 256 * 256)
                + (datos[inicio + 3] * 256 * 256 * 256);
    }


    public String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }


}
