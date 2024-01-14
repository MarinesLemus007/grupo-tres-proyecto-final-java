package models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Compra {
    //Atributos.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long numero_compra;
    private long dni_comprador;
    private long codigo_producto_compra;
    private int cantidad_producto;
    private boolean esta_pagada;
    private BigDecimal total_compra;
    private Date fecha_compra;
    /*@ManyToMany(cascade = CascadeType.ALL)
    private List<Producto> productos = new ArrayList<>();*/
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "compra_producto",
            joinColumns = @JoinColumn(name = "numero_compra", referencedColumnName = "numero_compra"),
            inverseJoinColumns = @JoinColumn(name = "codigo_producto", referencedColumnName = "codigo_producto")
    )
    private List<Producto> productos = new ArrayList<>();

    @ManyToOne
    @JoinColumn (name = "codigo_producto")
    private Boleta boleta;

    //Constructores.
    public Compra() { }
    public Compra(long dni_comprador, long codigo_producto_compra, int cantidad_producto, boolean esta_pagada, BigDecimal total_compra) {
        this.codigo_producto_compra = codigo_producto_compra;
        this.dni_comprador = dni_comprador;
        this.cantidad_producto = cantidad_producto;
        this.esta_pagada = esta_pagada;
        this.total_compra = total_compra;
        this.fecha_compra = new Date(); }
    public Compra(Boleta boleta, long numero_compra, int cantidad_producto, boolean esta_pagada, BigDecimal total_compra) {
        this.boleta = boleta;
        this.numero_compra = numero_compra;
        this.cantidad_producto = cantidad_producto;
        this.esta_pagada = esta_pagada;
        this.total_compra = total_compra;
        this.fecha_compra = new Date();}

    //Getters.

    public long getNumero_compra() { return numero_compra; }
    public int getCantidad_producto() { return cantidad_producto; }
    public boolean isEsta_pagada() { return esta_pagada; }
    public BigDecimal getTotal_compra() { return total_compra; }
    public Date getFecha_compra() { return fecha_compra; }
    public List<Producto> getProductos() { return productos;}
    public Boleta getBoleta() { return boleta; }
    public long getDni_comprador() { return dni_comprador; }
    public long getCodigo_producto_compra() { return codigo_producto_compra;}
//Setters.

    public void setNumero_compra(long numero_compra) { this.numero_compra = numero_compra; }
    public void setCantidad_producto(int cantidad_producto) { this.cantidad_producto = cantidad_producto; }
    public void setEsta_pagada(boolean esta_pagada) { this.esta_pagada = esta_pagada; }
    public void setTotal_compra(BigDecimal total_compra) { this.total_compra = total_compra; }
    public void setFecha_compra(Date fecha_compra) { this.fecha_compra = fecha_compra; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }
    public void setBoleta(Boleta boleta) { this.boleta = boleta; }
    public void setDni_comprador(long dni_comprador) { this.dni_comprador = dni_comprador; }
    public void setCodigo_producto_compra(long codigo_producto_compra) { this.codigo_producto_compra = codigo_producto_compra; }

    //Metodos.

    public void totalCompra(){
        BigDecimal total_compra_realizada = getTotal_compra().multiply(BigDecimal.valueOf(getCantidad_producto()));
        setTotal_compra(total_compra_realizada);
    }
    /*public void verBoletas( long dni_usuario_logeado){
        System.out.println(
                "______________________\n" +
                "COD BOLETA " + getNumero_compra() + "\n" +
                getCantidad_producto() + " " + getProductos().get(0) + " " + getTotal_compra() + "\n" +
                        "______________________"
        );

    }*/
}

