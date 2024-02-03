package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidadorDNI {

    public static boolean validarDNI(String dni) {
        boolean dniValido = false;
        if (dni.length() == 9) {
            String numeroStr = dni.substring(0, 8);
            try {
                int numero = Integer.parseInt(numeroStr);
                int resto = numero % 23;
                char letra = obtenerLetra(resto);
                dniValido = letra == dni.charAt(8);
            } catch (NumberFormatException e) {
                Logger.getLogger(Integer.class.getName()).log(Level.SEVERE, "El número no es válido", e);
            }
        }
        return dniValido;
    }

    private static char obtenerLetra(int resto) {
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B',
                'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        char letra = ' ';
        if (resto >= 0 && resto < 23) {
            letra = letras[resto];
        }
        return letra;
    }
}


