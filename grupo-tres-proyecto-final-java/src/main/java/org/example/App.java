package org.example;

import dao.CompraDAO;
import dao.ProductoDAO;
import dao.TarjetaDAO;
import dao.UsuarioDAO;
import models.Compra;
import models.Producto;
import models.Tarjeta;
import models.Usuario;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    static Usuario usuarioEnSesion;

    static UsuarioDAO usuarioDAO = new UsuarioDAO();
    static TarjetaDAO tarjetaDAO = new TarjetaDAO();
    static ProductoDAO productoDAO = new ProductoDAO();

    public static void main( String[] args )
    {

        System.out.println("Tienda abierta :D");

        boolean bucle = true;
        while (bucle){

            Scanner scanner = new Scanner(System.in);
            System.out.println("1.registrarse como administrador");
            System.out.println("2.registrarse como usuario");
            System.out.println("3.Iniciar sesion");
            System.out.println("4.Salir");

            String respuesta = scanner.next();

            if (respuesta.equals("1") || respuesta.equals("2")){
                usuarioEnSesion = crearUsuario(respuesta);
            } else if (respuesta.equals("3")) {
                System.out.println("Ingrese dni");
                Long dni = scanner.nextLong();

                Usuario validacion = usuarioDAO.findBydni(dni);
                if (validacion != null){
                    usuarioEnSesion = validacion;
                    System.out.println("Sesion iniciada con exito " + validacion.getNombre_usuario());
                    //compra---
                }else {
                    System.out.println("Los datos ingresados no son correctos");
                }

            }
            else if(respuesta.equals("4")){
                break;
            }
            else {
                System.out.println("Respuesta invalida");
            }
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
    }



    public static Usuario crearUsuario(String respuesta){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese su direccion");
        String direccion = scanner.nextLine();
        System.out.println("Ingrese su email");
        String email = scanner.nextLine();

        if (respuesta.equals("1")){
            Usuario usuario = new Usuario(nombre,direccion,email,"admin");
            usuarioDAO.insert(usuario);

            Tarjeta tarjeta = new Tarjeta();
            Scanner scanner1 = new Scanner(System.in);

            System.out.println(usuario.getRole() + " tiene acceso a ingresar prooductos a la tienda");

            System.out.println("Ingrese nombre de producto");
            String nameProduct = scanner1.nextLine();

            System.out.println("Descripcion: ");
            String description = scanner1.nextLine();
            System.out.println("Ingrese precio del producto");
            int priceProduct = scanner1.nextInt();

           Producto producto = new Producto(nameProduct, priceProduct, description);
           productoDAO.insert(producto);

           return usuario;

        } else if (respuesta.equals("2")) {
            Usuario usuario = new Usuario(nombre,direccion,email,"cliente");
            usuarioDAO.insert(usuario);

            System.out.println("Cliente " + usuario.getNombre_usuario() + " se ha registrado con exito");
            System.out.println("ingrese numero de tarjeta");
            String numCard = scanner.next();
            System.out.println("Ingrese monto a recargar");
            int amount = scanner.nextInt();
            System.out.println("Su tarjeta se registro con exito!");

            tarjetaDAO.insert(new Tarjeta(numCard,amount,usuario));

            return usuario;
       }
        System.out.println("Vuelva a ingresar datos");
        return null;
    }

}
