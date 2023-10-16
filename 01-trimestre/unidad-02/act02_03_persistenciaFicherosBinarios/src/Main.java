import java.io.File;

public class Main {
    public static void main(String[] args) {
        // en el constructor hay que asegurarse de pasar las rutas correctas y que el directorio de trabajo sea donde están los archivos
        EjemploDeCopiaDeDatos test = new EjemploDeCopiaDeDatos("pruebas.dat", "pruebas2.dat");

        // abajo está la respuesta a la primera actividad
        System.out.println("¿Se ha copiado el contenido de " + new File("pruebas.dat").getName()
                + " en otro archivo? " + test.copiarBloques128());

        // esta es la respuesta a la segunda actividad
        System.out.println("La imagen " + new File("img1.png").getName()
                + " está en formato: " + new ArchivosImagen("img1.png").imgType());
        System.out.println("La imagen " + new File("img2.jpeg").getName()
                + " está en formato: " + new ArchivosImagen("img2.jpeg").imgType());
        System.out.println("La imagen " + new File("img3.gif").getName()
                + " está en formato: " + new ArchivosImagen("img3.gif").imgType());
        System.out.println("La imagen " + new File("img4.ico").getName()
                + " está en formato: " + new ArchivosImagen("img4.ico").imgType());

        // esta es la respuesta a la tercera actividad
        ArchivosImagen bmp = new ArchivosImagen("img5.bmp");
        System.out.println("La imagen " + new File("img5.bmp").getName()
                + " está en formato: " + bmp.imgType());
        System.out.println("Sus datos son: " + bmp.analizarBmp());
    }

}
