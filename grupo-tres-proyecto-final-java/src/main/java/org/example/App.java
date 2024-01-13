package org.example;

import java.util.*;

import dao.UsuarioDAO;
import models.Usuario;
import dao.BoletaDAO;
import models.Boleta;
import dao.CompraDAO;
import models.Compra;
import dao.ProductoDAO;
import models.Producto;
import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        String nombre_usuario_role = "", direccion_usuario_role = "", email_usuario_role = "", mensaje = "", nombre_producto_creacion = "", descripcion_producto_creacion = "";
        long dni_usuario_role = 0, codigo_producto_compra = 0;
        int respuesta_role, opcion_menu_admin;
        BigDecimal precio_producto_creacion;
        boolean itera_menu_inicial = false, itera_enrolamiento, itera_menu_admin_crear_producto = true;

        Scanner scanner = new Scanner(System.in);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        CompraDAO compraDAO = new CompraDAO();
        ProductoDAO productoDAO = new ProductoDAO();

        do {
            //Menú inicial, Crear Usuario o entrar con usuario ya existente
            System.out.println("\nBienvenido a Nuestra Tienda\n" +
                    "seleccione la opción que desee realizar : \n" +
                    "1. Registrarse como administrador \n" +
                    "2. Registrarse como cliente \n" +
                    "3. Ingresar con su cuenta");
            respuesta_role = scanner.nextInt();

            switch (respuesta_role) {
                case 1:
                case 2:

                    do {
                        System.out.println("Ingrese su DNI");
                        dni_usuario_role = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Ingrese su nombre de usuario");
                        nombre_usuario_role = scanner.nextLine();
                        System.out.println("Ingrese su dirección");
                        direccion_usuario_role = scanner.nextLine();
                        System.out.println("Ingrese su email");
                        email_usuario_role = scanner.nextLine();

                        if (dni_usuario_role != 0 && !nombre_usuario_role.equals("") && !direccion_usuario_role.equals("") && !email_usuario_role.equals("")) {
                            //Crear Usuario
                            Usuario newUsuario;
                            if (respuesta_role == 1) {
                                newUsuario = new Usuario(dni_usuario_role, nombre_usuario_role, direccion_usuario_role, email_usuario_role, "admin");
                            } else {
                                newUsuario = new Usuario(dni_usuario_role, nombre_usuario_role, direccion_usuario_role, email_usuario_role, "cliente");
                            }
                            usuarioDAO.insert(newUsuario);
                            //Ver Usuario Creado
                            Usuario foundUsuario = usuarioDAO.findById(newUsuario.getDni_usuario());
                            System.out.println("\nEl siguiente usuario ha sido creado\n" +
                                    "Nombre : " + foundUsuario.getNombre_usuario() + "\n" +
                                    "Dni : " + foundUsuario.getDni_usuario() + "\n" +
                                    "Dirección : " + foundUsuario.getDireccion_usuario() + "\n" +
                                    "Email : " + foundUsuario.getEmail_usuario() + "\n" +
                                    "Role : " + foundUsuario.getRole());

                            itera_enrolamiento = false;
                            itera_menu_inicial = false;

                        } else {
                            System.out.println("Por favor, revise los datos para enrolarse. \n");
                            itera_enrolamiento = true;
                        }

                    } while (itera_enrolamiento);

                    break;
                case 3:

                    System.out.println("Por favor, Ingrese su DNI");
                    dni_usuario_role = scanner.nextInt();
                    scanner.nextLine();
                    Usuario usuarioExistente = usuarioDAO.findById(dni_usuario_role);
                    if (usuarioExistente != null) {
                        System.out.println("El usuario existe en el registro.");
                        itera_menu_inicial = false;
                    } else {
                        System.out.println("El usuario no existe en el registro.");
                        itera_menu_inicial = true;
                    }

                    break;

                default:
                    System.out.println("la opción ingresada no es válida\nIngrese una opción válido entre 1 y 3");
                    itera_menu_inicial = true;
            }

        }while(itera_menu_inicial);

        if(Objects.equals(usuarioDAO.findById(dni_usuario_role).getRole(), "admin")) {

            do {
                System.out.println("\nBienvenido a Nuestra Tienda\n" +
                        "seleccione la opción que desee realizar : \n" +
                        "1. Crear Productos\n" +
                        "2. Ver lista de nuestros clientes\n" +
                        "3. Salir");
                opcion_menu_admin = scanner.nextInt();
                scanner.nextLine();

                switch (opcion_menu_admin) {
                    case 1:
                        while(itera_menu_admin_crear_producto) {
                            //Crear Productos
                            System.out.println("Ingrese el nombre del producto:");
                            nombre_producto_creacion = scanner.nextLine();
                            System.out.println("Ingrese el precio del producto:");
                            precio_producto_creacion = scanner.nextBigDecimal();
                            scanner.nextLine();
                            System.out.println("Ingrese la descripción del producto:");
                            descripcion_producto_creacion = scanner.nextLine();

                            if (nombre_producto_creacion.isEmpty() || precio_producto_creacion.compareTo(BigDecimal.ZERO) == 0 || descripcion_producto_creacion.isEmpty()) {
                                System.out.println("Por favor, revise los datos ingresados para la creación del producto\n");
                            } else {
                                Producto newProducto = new Producto(nombre_producto_creacion, precio_producto_creacion, descripcion_producto_creacion);
                                productoDAO.insert(newProducto);
                                Producto foundProducto = productoDAO.findById(newProducto.getCodigo_producto());

                                System.out.println("\nSe ha creado el Producto\n" +
                                        "Nombre : " + foundProducto.getNombre_producto() + "\n" +
                                        "Precio : " + foundProducto.getPrecio_producto() + "\n" +
                                        "Descripción : " + foundProducto.getDescripcion_producto());

                                itera_menu_admin_crear_producto = false;
                            }
                        }
                        break;
                    case 2:
                        mensaje = "Ver Lista de clientes";
                        break;
                    case 3:
                        mensaje = "Hasta Luego";
                        break;
                    default:
                        mensaje = "la opción ingresada no es válida\nIngrese una opción válido entre 1 y 3";
                }

                System.out.println(mensaje);

            }while(!mensaje.equals("Hasta Luego"));

            scanner.close();
        }else{
            //Si es cliente

            do {
                System.out.println("\nBienvenido a Nuestra Tienda\n" +
                        "seleccione la opción que desee realizar : \n" +
                        "1. Realizar Compra\n" +
                        "2. Ver Boletas\n" +
                        "3. Ver Saldo\n" +
                        "4. Salir");
                int opcion_menu_cliente = scanner.nextInt();
                scanner.nextLine();

                switch (opcion_menu_cliente) {
                    case 1:
                        //Comprar Productos
                        //Productos en base de datos
                        List<Producto> productos = productoDAO.findAll();
                        System.out.println("\n A continuación, te presentamos una lista de los productos que tenemos en catálogo : \n");
                        for (Producto producto : productos) {
                            System.out.println("ID: " + producto.getCodigo_producto());
                            System.out.println("Nombre: " + producto.getNombre_producto());
                            System.out.println("Precio: " + producto.getPrecio_producto());
                            System.out.println("---------------------------\n");
                        }

                        System.out.println("Por favor, escribe el código del producto \n");
                        codigo_producto_compra = scanner.nextLong();
                        scanner.nextLine();
                        List<Producto> productoExistente = productoDAO.findByName(codigo_producto_compra);

                        if (productoExistente != null) {
                            BigDecimal precioProducto = productoExistente.getFirst().getPrecio_producto();

                            System.out.println("\nIngrese cuantos quiere llevar:");
                            int cantidad_producto_compra = scanner.nextInt();
                            scanner.nextLine();
                            //Creación de compra
                            Compra newCompra = new Compra(dni_usuario_role, codigo_producto_compra, cantidad_producto_compra, true, precioProducto);
                            Producto productoAgregado = productoDAO.findById(codigo_producto_compra);
                            newCompra.getProductos().add(productoAgregado);
                            compraDAO.insert(newCompra);
                            Compra foundCompra = compraDAO.findById(newCompra.getNumero_compra());

                            String cp1 = String.valueOf(foundCompra.getCantidad_producto());
                            String tc1 = String.valueOf(foundCompra.getTotal_compra());

                            double cp2 = Double.parseDouble(cp1);
                            double tc2 = Double.parseDouble(tc1);

                            double total_compra_realizada = cp2 * tc2;

                            System.out.println("\nSe ha creado la siguiente Compra\n" +
                                    "Número de la compra : "+ foundCompra.getNumero_compra() +"\n" +
                                    "Código de Producto : "+ foundCompra.getCodigo_producto_compra() +"\n" +
                                    "Precio : "+  foundCompra.getTotal_compra() +"\n" +
                                    "Total : " + total_compra_realizada);
                        } else {
                            System.out.println("El código de producto ingresado no existe, inténtelo nuevamente");
                        }

                        break;
                    case 2:
                        mensaje = "Ver Boletas";
                        break;
                    case 3:
                        mensaje = "Ver Saldo";
                        break;
                    case 4:
                        mensaje = "Hasta Luego";
                        break;
                    default:
                        mensaje = "la opción ingresada no es válida\nIngrese una opción válido entre 1 y 4";
                }

                System.out.println(mensaje);

            }while(!mensaje.equals("Hasta Luego"));

            scanner.close();
        }
        //Crear Boleta (no tiene constructor)
        /*BoletaDAO boletaDAO = new BoletaDAO();
        Boleta newBoleta = new Boleta();
        boletaDAO.insert(newBoleta);
        Boleta foundBoleta = boletaDAO.findById(newBoleta.);
        System.out.println("foundBoleta = " + foundBoleta);*/
    }
}