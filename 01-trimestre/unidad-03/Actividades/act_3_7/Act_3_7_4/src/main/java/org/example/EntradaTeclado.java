package org.example;



import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Adriano Díaz Benítez <Adriano.Díaz>
 */
public class EntradaTeclado {

    private static final Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    /**
     * Esta función solicita un entero por terminal
     *
     * @param cadena un mensaje para mostrar al usuario
     * @return el entero
     */
    public static int pedirEntero(String cadena) {
        int num = 0;
        boolean error;
        do {
            error = false;
            try {
                System.out.print(cadena);
                num = sc.nextInt();
            } catch (InputMismatchException ex) {
                error = true;
            } finally {
                sc.nextLine();
            }
        } while (error);
        return (num);
    }

    /**
     * Esta función solicita un entero por terminal, el entero ha de tener un
     * valor mínimo
     *
     * @param cadena un mensaje para mostrar al usuario
     * @param min    el mínimo valor del entero
     * @return el entero
     */
    public static int pedirEntero(String cadena, int min) {
        int num = 0;
        boolean error;
        do {
            try {
                System.out.print(cadena);
                num = sc.nextInt();
            } catch (InputMismatchException ex) {
                error = true;
            } finally {
                sc.nextLine();
            }
            error = num < min;
        } while (error);
        return (num);
    }

    /**
     * Esta función solicita un entero por terminal, el entero ha de encontrarse
     * entre un mínimo y un máximo
     *
     * @param cadena un mensaje para mostrar al usuario
     * @param min    el mínimo valor del entero
     * @param max    el máximo valor del entero
     * @return el entero
     */
    public static int pedirEntero(String cadena, int min, int max) {
        int num = 0;
        boolean error;
        do {
            try {
                System.out.print(cadena);
                num = sc.nextInt();
            } catch (InputMismatchException ex) {
                error = true;
            } finally {
                sc.nextLine();
            }
            error = num < min || num > max;

        } while (error);
        return (num);
    }

    /**
     * Esta función solicita un número decimal (float) por terminal
     *
     * @param cadena un mensaje para mostrar al usuario
     * @return un float
     */
    public static float pedirFloat(String cadena) {
        float num = 0;
        boolean error;
        do {
            error = false;
            try {
                System.out.print(cadena);
                num = sc.nextFloat();
            } catch (InputMismatchException ex) {
                error = true;
            } finally {
                sc.nextLine();
            }
        } while (error);
        return (num);
    }

    /**
     * Esta función solicita un número decimal (double) por terminal
     *
     * @param cadena un mensaje para mostrar al usuario
     * @return un double
     */
    public static double pedirDouble(String cadena) {
        double num = 0;
        boolean error;
        do {
            error = false;
            try {
                System.out.print(cadena);
                num = sc.nextDouble();
            } catch (InputMismatchException ex) {
                error = true;
            } finally {
                sc.nextLine();
            }
        } while (error);
        return (num);
    }

    /**
     * Esta función solicita un entero (long) por terminal
     *
     * @param cadena un mensaje para mostrar al usuario
     * @return un long
     */
    public static long pedirLong(String cadena) {
        long num = 0;
        boolean error;
        do {
            error = false;
            try {
                System.out.print(cadena);
                num = sc.nextLong();
            } catch (InputMismatchException ex) {
                error = true;
            } finally {
                sc.nextLine();
            }
        } while (error);
        return (num);
    }

    /**
     * Esta función solicita un carácter por terminal
     *
     * @param cadena un mensaje para mostrar al usuario
     * @return un carácter, en mayúsculas
     */
    public static char pedirChar(String cadena, String errorMsg) {
        String caracter;
        boolean error;
        do {
            System.out.print(cadena);
            caracter = sc.nextLine();
            error = caracter.length() != 1;
            if (error) {
                System.out.println(errorMsg);
            }
        } while (error);
        return (caracter.toUpperCase().charAt(0));
    }

    /**
     * Esta función solicita una cadena de caracteres al usuario por terminal
     *
     * @param cadena un mensaje para mostrar al usuario
     * @return una cadena de caracteres
     */
    public static String pedirCadena(String cadena) {
        System.out.print(cadena);
        return (sc.nextLine());
    }

}
