
public class Main {

    public static void menu() {
        GeneradorListasOrdenadas lista = new GeneradorListasOrdenadas("lista1.txt", "lista2.txt");
        String mensajeMenu = """
                Elija una opción:
                1. Block de notas.
                2. Combinar dos listas de ordenadas en otra ("ordenadas.txt").
                3. Mostrar lista ordenada.
                4. Buscar una cadena de texto en la lista ordenada.
                5. Salir.
                """;
        int opcion;

        do {
            opcion = EntradaTeclado.pedirEntero(mensajeMenu, 1, 5);
            switch (opcion) {
                case 1 -> {
                    System.out.println("----------------------- BLOCK DE NOTAS -----------------------");
                    // respuesta de la primera pregunta
                    BlockDeNotas block = new BlockDeNotas("texto.txt");
                    if (block.escribir()) {
                        System.out.println("Block de notas ejecutado correctamente.");
                    } else {
                        System.out.println("Error de ejecución en block de notas.");
                    }
                }
                case 2 -> {
                    System.out.println("---------------------- COMBINAR LISTAS ----------------------");
                    // respuesta de la segunda pregunta
                    if (lista.generarNuevaLista()) {
                        System.out.println("Nuevo documento creado correctamente.");
                    } else {
                        System.out.println("Error al copiar listas.");
                    }
                }
                case 3 -> {
                    System.out.println("------------------- MOSTRAR LISTA ORDENADA -------------------");
                    // respuesta de la tercera pregunta
                    lista.mostrarContenidoLista("ordenado.txt", 23);
                }
                case 4 -> {
                    System.out.println("----------------------- BUSCAR CADENA -----------------------");
                    // respuesta de la cuarta pregunta
                    String cadena = EntradaTeclado.pedirCadena("Introduzca un nombre para buscar en el archivo "
                            + "ordenado.txt: ");
                    if (!lista.buscarCadena(cadena, "ordenado.txt")) {
                        System.out.println("Documento no encontrado o la cadena no se encuentra en el documento.");
                    }
                }
                case 5 -> System.out.println("Fin de programa.");
            }
        } while (opcion != 5);
    }

    public static void main(String[] args) {
        menu();
    }
}