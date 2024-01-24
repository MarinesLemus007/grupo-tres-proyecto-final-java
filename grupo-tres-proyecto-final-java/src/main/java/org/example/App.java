package org.example;
import java.util.*;
import models.*;
import dao.*;

public class App
{
    public static void main( String[] args )
    {

        String nombre_usuario_role , direccion_usuario_role, email_usuario_role, nombre_producto_creacion, descripcion_producto_creacion, mensaje = "";
        long dni_usuario_role = 0, codigo_producto_compra;
        int respuesta_role, opcion_menu_admin, cantidad_producto_compra, menu_continuar_compra;
        double precio_producto_creacion;
        boolean itera_menu_inicial = false, itera_enrolamiento, itera_menu_admin_crear_producto = true, itera_cod_producto = false, itera_cantidad_compra = false, itera_compra_cliente = true;
        List<Producto> productoExistente;

        Scanner scanner = new Scanner(System.in);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        CompraDAO compraDAO = new CompraDAO();
        ProductoDAO productoDAO = new ProductoDAO();
        BoletaDAO boletaDAO = new BoletaDAO();
        TarjetaDAO tarjetaDAO = new TarjetaDAO();
        Carrito newCarrito = new Carrito();

        do {
            //Menú inicial, Crear Usuario o entrar con usuario ya existente
            System.out.println("""
                    Bienvenido a Nuestra Tienda
                    Seleccione la opción que desee realizar :
                    1. Registrarse como administrador
                    2. Registrarse como cliente
                    3. Ingresar con su cuenta""");
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

                        if (dni_usuario_role != 0 && !nombre_usuario_role.isEmpty() && !direccion_usuario_role.isEmpty() && !email_usuario_role.isEmpty()) {
                            //Crear Usuario
                            Usuario newUsuario;
                            if (respuesta_role == 1) {
                                newUsuario = new Usuario(dni_usuario_role, nombre_usuario_role, direccion_usuario_role, email_usuario_role, "admin");
                                usuarioDAO.insert(newUsuario);
                            } else {
                                newUsuario = new Usuario(dni_usuario_role, nombre_usuario_role, direccion_usuario_role, email_usuario_role, "cliente");
                                System.out.println("Para culminar el registro, por favor ingresa los siguientes datos: \n" +
                                        "Número de tarjeta : ");
                                String numCard = scanner.next();
                                System.out.println("Monto que desee cargar a la tarjeta :");
                                int amount = scanner.nextInt();
                                System.out.println("Se ha registrado la información de su tarjeta exitosamente");

                                Tarjeta newTarjeta;
                                newTarjeta = new Tarjeta(numCard,amount,newUsuario);
                                usuarioDAO.insert(newUsuario);
                                tarjetaDAO.insert(newTarjeta);
                            }

                            //Ver Usuario Creado
                            Usuario foundUsuario = usuarioDAO.findById(newUsuario.getDni_usuario());
                            System.out.println("\nEl siguiente usuario ha sido creado\n" +
                                    "Nombre : " + foundUsuario.getNombre_usuario() + "\n" +
                                    "Dni : " + foundUsuario.getDni_usuario() + "\n" +
                                    "Dirección : " + foundUsuario.getDireccion_usuario() + "\n" +
                                    "Email : " + foundUsuario.getEmail_usuario() + "\n" +
                                    "Role : " + foundUsuario.getRole() + "\n");

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
                        System.out.println("\nEl usuario existe en el registro.\n");
                        itera_menu_inicial = false;
                    } else {
                        System.out.println("\nEl usuario no existe en el registro.\n");
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
                System.out.println("""
                        Bienvenido a Nuestra Tienda
                        seleccione la opción que desee realizar :
                        1. Crear Productos
                        2. Ver lista de nuestros clientes
                        3. Salir""");
                opcion_menu_admin = scanner.nextInt();
                scanner.nextLine();
                itera_menu_admin_crear_producto = true;

                switch (opcion_menu_admin) {
                    case 1:
                        while(itera_menu_admin_crear_producto) {
                            //Crear Productos
                            System.out.println("Ingrese el nombre del producto:");
                            nombre_producto_creacion = scanner.nextLine();
                            System.out.println("Ingrese el precio del producto:");
                            precio_producto_creacion = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.println("Ingrese la descripción del producto:");
                            descripcion_producto_creacion = scanner.nextLine();

                            if (nombre_producto_creacion.isEmpty() || precio_producto_creacion == 0 || descripcion_producto_creacion.isEmpty()) {
                                System.out.println("Por favor, revise los datos ingresados para la creación del producto\n");
                            } else {
                                Producto newProducto = new Producto(nombre_producto_creacion, precio_producto_creacion, descripcion_producto_creacion);
                                productoDAO.insert(newProducto);
                                Producto foundProducto = productoDAO.findById(newProducto.getCodigo_producto());

                                System.out.println("\nSe ha creado el Producto\n" +
                                        "Nombre : " + foundProducto.getNombre_producto() + ", " +
                                        "Precio : " + foundProducto.getPrecio_producto() + ", " +
                                        "Descripción : " + foundProducto.getDescripcion_producto());

                                itera_menu_admin_crear_producto = false;
                            }
                        }
                        break;
                    case 2:

                        List<Usuario> usuarios = usuarioDAO.findByRole("cliente");
                        System.out.println("\nA continuación, te presentamos una lista de los cientes que tenemos : \n");
                        for (Usuario usuario : usuarios) {
                            System.out.println(
                                "Nombre: " + usuario.getNombre_usuario()+ ", "+
                                "Correo: " + usuario.getEmail_usuario()+ ", "+
                                "Dirección: " + usuario.getDireccion_usuario()
                            );
                        }

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
                System.out.println("""
                        Bienvenido a Nuestra Tienda
                        seleccione la opción que desee realizar :
                        1. Realizar Compra
                        2. Ver Boletas
                        3. Ver Saldo
                        4. Abonar Saldo
                        5. Salir""");
                int opcion_menu_cliente = scanner.nextInt();
                scanner.nextLine();

                switch (opcion_menu_cliente) {
                    case 1:
                        //Comprar Productos
                        Compra newCompra;

                        while (itera_compra_cliente){
                            //Productos en base de datos
                            List<Producto> productos = productoDAO.findAll();
                            System.out.println("A continuación, te presentamos una lista de los productos que tenemos en catálogo : \n");
                            for (Producto producto : productos) {
                                System.out.println("ID: " + producto.getCodigo_producto() + ", " +
                                        "Nombre: " + producto.getNombre_producto() + ", " +
                                        "Precio: $ " + producto.getPrecio_producto());
                            }

                            System.out.println("\nPor favor, escribe el código del producto");
                            codigo_producto_compra = scanner.nextLong();

                            productoExistente = productoDAO.findByName(codigo_producto_compra);
                            double precioProducto = productoExistente.getFirst().getPrecio_producto();

                            System.out.println("\nIngrese cuantos quiere llevar:");
                            cantidad_producto_compra = scanner.nextInt();

                            //Creación de Compra
                            newCompra = new Compra(dni_usuario_role, codigo_producto_compra, cantidad_producto_compra, true, precioProducto);

                            //Agregar al carrito
                            newCarrito.GuardarEnCarrito(newCompra);

                            System.out.println("Tu carrito de compras contiene: \n");
                            newCarrito.MostrarProductos();

                            //Consulta a cliente si desea realizar una nueva compra
                            System.out.println(
                                    """
                                    ¿Desea agregar otro producto al carrito?\s
                                    1. Sí, deseo ver más productos\s
                                    2. No, deseo pagar"""
                            );
                            menu_continuar_compra = scanner.nextInt();

                            if(menu_continuar_compra == 1){
                                itera_compra_cliente = true;
                            }else if(menu_continuar_compra == 2){
                                itera_compra_cliente = false;
                            }
                        }

                        //Comprobar si la tarjeta tiene saldo suficiente
                        int saldoInicial = tarjetaDAO.findByUser(dni_usuario_role).getAmount();
                        ArrayList<Compra> comprobarCompra = newCarrito.getArregloCarrito();
                        double limpiarTotalCompra = 0;
                        int saldoEvaluado = 0;
                        for (Compra compra : comprobarCompra) {
                            double valor_unitario = compra.getTotal_compra();
                            //saldo en tarjeta luego de la evaluación de la compra
                            saldoEvaluado += compra.totalCompra(usuarioDAO.findBydni(dni_usuario_role).getTarjeta()).getAmount();

                            //Seteando valor a unitario
                            compra.setTotal_compra(valor_unitario);
                        }
                        if(saldoEvaluado < 0){
                            System.out.println("\nPor favor, abone saldo a su tarjeta para realizar la compra\n");
                            newCarrito.VaciarCarrito();
                            itera_compra_cliente = true;
                            break;
                        }

                        // Asociar Compra con Boleta
                        // Crear Boleta para el usuario
                        Boleta newBoleta = new Boleta();
                        boletaDAO.insert(newBoleta);

                        //Asociar el número de boleta a todas las compras
                        Boleta foundBoleta = boletaDAO.findById(newBoleta.getId());

                        ArrayList<Compra> comprasEnCarrito  = newCarrito.getArregloCarrito();

                        for (Compra compra : comprasEnCarrito ) {
                            compra.setBoleta(foundBoleta);
                        }
                        foundBoleta.getCompras().addAll(comprasEnCarrito);

                        for(Compra compra : comprasEnCarrito){
                            //Se actualiza saldo de tarjeta y valor de la compra total
                            Tarjeta t = compra.totalCompra(usuarioDAO.findBydni(dni_usuario_role).getTarjeta());
                            tarjetaDAO.update(t);

                            //Asociación compra-producto
                            Producto productoAgregado = productoDAO.findById(compra.getCodigo_producto_compra());
                            compra.getProductos().add(productoAgregado);

                            compraDAO.insert(compra);
                        }

                        //Asociación de boleta con usuario
                        Usuario usuarioComprador = usuarioDAO.findById(dni_usuario_role);
                        boletaDAO.update(newBoleta, usuarioComprador);

                        //Ver compras asociadas a boleta
                        System.out.println("\nNúmero de boleta " + foundBoleta.getId() + " :");
                        for (Compra compra : foundBoleta.getCompras()) {
                            Producto foundProducto = productoDAO.findById(compra.getCodigo_producto_compra());
                            System.out.println("Código Producto: " + compra.getCodigo_producto_compra() + ", " +
                                    "Nombre: " + foundProducto.getNombre_producto() + ", " +
                                    "Cantidad : " + compra.getCantidad_producto() + ", " +
                                    "Precio : " + compra.getTotal_compra() + ","
                            );
                        }

                        //Vaciar Carrito
                        newCarrito.VaciarCarrito();
                        itera_compra_cliente = true;
                        break;
                    case 2:

                        usuarioDAO = new UsuarioDAO();

                        System.out.println("Historial de compras: \n");
                        List<Compra> compras = compraDAO.addCompraToUsuario(dni_usuario_role);
                        for (Compra compra : compras) {
                            System.out.println(
                                    "Boleta : " + compra.getBoleta().getId() + ", " +
                                    "Cantidad : " + compra.getCantidad_producto()+ ", " +
                                    "Producto : " + productoDAO.findById(compra.getCodigo_producto_compra()).getNombre_producto() + ", " +
                                    "Total: " + compra.getTotal_compra());
                        }
                        break;
                    case 3:
                        Tarjeta verSaldo = usuarioDAO.findBydni(dni_usuario_role).getTarjeta();
                        System.out.println("\nSaldo actual: $ " + verSaldo.getAmount());
                        break;
                    case 4:
                        System.out.println("Por favor, ingrese el monto a abonar en su tarjeta.");
                        int monto_abonar_tarjeta, monto_actual_tarjeta, suma_total_tarjeta;
                        monto_abonar_tarjeta = scanner.nextInt();

                        Tarjeta abonarSaldo = usuarioDAO.findBydni(dni_usuario_role).getTarjeta();
                        monto_actual_tarjeta = abonarSaldo.getAmount();
                        suma_total_tarjeta = monto_abonar_tarjeta + monto_actual_tarjeta;
                        abonarSaldo.setAmount(suma_total_tarjeta);

                        tarjetaDAO.update(abonarSaldo);

                        System.out.println("\nSu nuevo saldo es de: $ " + abonarSaldo.getAmount() + "\n");
                        break;
                    case 5:
                        mensaje = "Hasta Luego";
                        break;
                    default:
                        mensaje = "la opción ingresada no es válida\nIngrese una opción válido entre 1 y 4";
                }

                System.out.println(mensaje);

            }while(!mensaje.equals("Hasta Luego"));

            scanner.close();
        }
    }
}