package org.example;

import dao.*;
import models.*;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    static UsuarioDAO usuarioDAO = new UsuarioDAO();
    static CompraDAO compraDAO = new CompraDAO();
    static BoletaDAO boletaDAO = new BoletaDAO();
    static ProductoDAO productoDAO = new ProductoDAO();

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
        Producto newProducto = new Producto("Silla escritorio", 20000, "Silla gamer ergonómica color amarillo");
        productoDAO.insert(newProducto);
        Producto foundProducto = productoDAO.findById(newProducto.getCodigo_producto());
        System.out.println("foundProducto = " + foundProducto);

        //Crear Compra
        CompraDAO compraDAO = new CompraDAO();
        Compra newCompra = new Compra(1, 2, true, 260000);
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
        return true;
    }

    //pagar
    public static void pagar(Compra compra) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su el monto a pagar: ");
        double monto = scanner.nextDouble();
        //Compra compra;
        boolean p = true;
        if (compra.realizarPago((int)monto)) {
             p = monto > 0;
            System.out.println("Pago exitoso, gracias por su compra");
        } else {
             p = false;
            System.out.println("Error en el procecsamiento del pago. Por favor, intentelo nuevamente. ");
        }
    }

    //Historial de Pedidos
    public static void historial (Usuario usuario){
        BoletaDAO boletaDAO = new BoletaDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su DNI: ");
        int dni = scanner.nextInt();
        System.out.println("Historial de pedidos de: " +usuario.getNombre_usuario() +" " +usuario.obtenerBoletas(dni));
    }

    //Al crear el menu, se debe preguntar que tipo de usuario es (admin o cliente),
    // solo pueden crear productos los admin.
    public static boolean crearProducto(String respuesta){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese nombre del producto");
        String nombreProducto = scanner.next();
        System.out.println("Ingrese precio del producto");
        int precioProducto = scanner.nextInt();
        System.out.println("Ingrese descripcion del producto");
        String descripcionProducto = scanner.next();
        Producto producto;

        //depende del numero que se le asigne en el menu, modificar de ser necesario.
        if (respuesta.equals("1")){
            producto = new Producto(nombreProducto, precioProducto,descripcionProducto);
        }else {
            System.out.println("Usuario no valido para crear productos");
            producto = new Producto();
        }
        productoDAO.insert(producto);
        return  true;
    }
}
