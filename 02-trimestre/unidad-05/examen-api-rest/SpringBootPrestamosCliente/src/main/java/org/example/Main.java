package org.example;

public class Main
{
    public static void main( String[] args )
    {
        RequestManager.getRequest(); // Obtengo la lista de autores
        RequestManager.getRequest(1); // Obtengo un autor en concreto (si existe)
    }
}
