package models;
import java.util.*;
import java.util.ArrayList;

public class Carrito {

    private ArrayList<Compra> arregloCarrito;

    public Carrito(){
        arregloCarrito = new ArrayList<Compra>();
    }

    public ArrayList<Compra> getArregloCarrito() { return arregloCarrito; }
    public void setArregloCarrito(ArrayList<Compra> arregloCarrito) { this.arregloCarrito = arregloCarrito; }

    public void GuardarEnCarrito(Compra compra){
        arregloCarrito.add(compra);
    }

    public void MostrarProductos(){

        List<Compra> compras;
        compras = getArregloCarrito();

        for (Compra compra : compras) {
            System.out.println(
                    "Cantidad: " + compra.getCantidad_producto() + "\n" +
                    "Código de producto: " + compra.getCodigo_producto_compra() + "\n"
            );
        }
    }
}
