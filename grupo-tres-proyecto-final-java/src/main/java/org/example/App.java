package org.example;

import dao.CompraDAO;
import dao.ProductoDAO;
import dao.UsuarioDAO;
import models.Compra;
import models.Producto;
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

        //Crear Producto
        ProductoDAO productoDAO = new ProductoDAO();
        Producto newProducto = new Producto("Silla Gamer", 250000, "Silla gamer ergonómica color amarillo");
        productoDAO.insert(newProducto);
        Producto foundProducto = productoDAO.findById(newProducto.getCodigo_producto());
        System.out.println("foundProducto = " + foundProducto);

        //Crear Compra
        CompraDAO compraDAO = new CompraDAO();
        Compra newCompra = new Compra(1, 2, true, 250000);
        compraDAO.insert(newCompra);
        Compra foundCompra = compraDAO.findById(newCompra.getNumero_compra());
        System.out.println("foundCompra = " + foundCompra);
        //System.out.println("Ingrese monto a pagar: "+ newCompra.setTotal_compra(9990));
    }

    public static boolean crearUsuario(String respuesta){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su DNI");
        int dni = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese su nombre de usuario");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese su direccion");
        String direccion = scanner.nextLine();
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
