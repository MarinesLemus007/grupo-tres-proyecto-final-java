package org.example;

import dao.UsuarioDAO;
import models.Usuario;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    static UsuarioDAO usuarioDAO = new UsuarioDAO();
    public static void main( String[] args )
    {
        System.out.println("Tienda abierta");

        Scanner scanner = new Scanner(System.in);
        System.out.println("1.registrarse como administrador");
        System.out.println("2.registrarse como usuario");
        String respuesta = scanner.next();
        if (respuesta.equals("1") || respuesta.equals("2")){

            crearUsuario(respuesta);
        }
    }

    public static boolean crearUsuario(String respuesta){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su DNI");
        int dni = scanner.nextInt();
        System.out.println("Ingrese su nombre de usuario");
        String nombre = scanner.next();
        System.out.println("Ingrese su direccion");
        String direccion = scanner.next();
        System.out.println("Ingrese su email");
        String email = scanner.next();

        Usuario usuario;

        if (respuesta.equals("1")){
            usuario = new Usuario(dni,nombre,direccion,email,"admin");
        }else {
            usuario = new Usuario(dni,nombre,direccion,email,"cliente");
        }

        usuarioDAO.insert(usuario);
        return  true;
    }

}
