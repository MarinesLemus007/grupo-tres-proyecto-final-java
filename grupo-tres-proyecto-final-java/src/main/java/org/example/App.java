package org.example;

import dao.UsuarioDAO;
import models.Usuario;
import dao.BoletaDAO;
import models.Boleta;
import dao.CompraDAO;
import models.Compra;
import dao.ProductoDAO;
import models.Producto;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Crear Socios
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario newUsuario = new Usuario(25321971, "Juan Perez", "La Florida, RM", "juanperez@gmail.com", "admin");
        usuarioDAO.insert(newUsuario);
        Usuario foundUsuario = usuarioDAO.findById(newUsuario.getDni_usuario());
        System.out.println("foundSocio = " + foundUsuario);
    }
}
