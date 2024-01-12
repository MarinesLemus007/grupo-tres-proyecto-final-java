package org.example;

import dao.UsuarioDAO;
import models.Usuario;
import dao.BoletaDAO;
import models.Boleta;
import dao.CompraDAO;
import models.Compra;
import dao.ProductoDAO;
import models.Producto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Crear Usuario
        /*UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario newUsuario = new Usuario(25321971, "Juan Perez", "La Florida, RM", "juanperez@gmail.com", "admin");
        usuarioDAO.insert(newUsuario);
        Usuario foundUsuario = usuarioDAO.findById(newUsuario.getDni_usuario());
        System.out.println("foundSocio = " + foundUsuario);*/

        //Crear Productos
        /*ProductoDAO productoDAO = new ProductoDAO();
        Producto newProducto = new Producto("Silla Gamer", new BigDecimal(250000), "Silla gamer ergonómica color amarillo");
        productoDAO.insert(newProducto);
        Producto foundProducto = productoDAO.findById(newProducto.getCodigo_producto());
        System.out.println("foundProducto = " + foundProducto);*/

        //Crear Compra (no agrega número de producto ni el dni)
        /*CompraDAO compraDAO = new CompraDAO();
        Compra newCompra = new Compra(2, true, new BigDecimal(250000));
        compraDAO.insert(newCompra);
        Compra foundCompra = compraDAO.findById(newCompra.getNumero_compra());
        System.out.println("foundCompra = " + foundCompra);*/

        //Crear Boleta (no tiene constructor)
        /*BoletaDAO boletaDAO = new BoletaDAO();
        Boleta newBoleta = new Boleta();
        boletaDAO.insert(newBoleta);
        Boleta foundBoleta = boletaDAO.findById(newBoleta.);
        System.out.println("foundBoleta = " + foundBoleta);*/

    }
}
