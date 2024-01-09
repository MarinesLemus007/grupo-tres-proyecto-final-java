package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @OneToMany
    @JoinColumn (name = "codigo_producto")
    private Compra compra;

    //Atributos.
    private int codigo_producto;
    private String nombre_producto;
    private int precio_producto;
    private String descripcion_producto;

    //Constructores.
    public Producto() { }
    public Producto(int codigo_producto, String nombre_producto, int precio_producto, String descripcion_producto) {
        this.codigo_producto = codigo_producto;
        this.nombre_producto = nombre_producto;
        this.precio_producto = precio_producto;
        this.descripcion_producto = descripcion_producto; }
    public Producto(int codigo_producto, String nombre_producto, int precio_producto, String descripcion_producto, Compra compra) {
        this.codigo_producto = codigo_producto;
        this.nombre_producto = nombre_producto;
        this.precio_producto = precio_producto;
        this.descripcion_producto = descripcion_producto;
        this.compra = compra; }

    //Getters.
    public int getCodigo_producto() { return codigo_producto; }
    public String getNombre_producto() { return nombre_producto; }
    public int getPrecio_producto() { return precio_producto; }
    public String getDescripcion_producto() { return descripcion_producto; }
    public Compra getCompra() { return compra; }

    //Setters.
    public void setCodigo_producto(int codigo_producto) { this.codigo_producto = codigo_producto; }
    public void setNombre_producto(String nombre_producto) { this.nombre_producto = nombre_producto; }
    public void setPrecio_producto(int precio_producto) { this.precio_producto = precio_producto; }
    public void setDescripcion_producto(String descripcion_producto) { this.descripcion_producto = descripcion_producto; }
    public void setCompra(Compra compra) { this.compra = compra; }

    //Metodos.

}
