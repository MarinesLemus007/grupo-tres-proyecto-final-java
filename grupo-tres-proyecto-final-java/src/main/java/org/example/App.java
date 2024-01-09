package org.example;

import daos.ClienDAO;
import models.Usuario;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println("Tienda abierta");

        Scanner scanner = new Scanner(System.in);
        System.out.println("1.registrarse como administrador");
        System.out.println("2.registrarse como usuario");
        String respuesta = scanner.nextLine();
        if (respuesta.equals("1") || respuesta.equals("2")){

            Usuario.createUsuario(respuesta);
        }
    }


}
