package models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
@Entity
public class Producto {
    //Atributos.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long codigo_producto;
    private String nombre_producto;
    private BigDecimal precio_producto;
    private String descripcion_producto;

     @ManyToMany(mappedBy = "productos", cascade = CascadeType.ALL)
     private List<Compra> compras;

    //Constructores.
    public Producto() { }
    public Producto(long codigo_producto, String nombre_producto, BigDecimal precio_producto, String descripcion_producto) {
        this.codigo_producto = codigo_producto;
        this.nombre_producto = nombre_producto;
        this.precio_producto = precio_producto;
        this.descripcion_producto = descripcion_producto;
        this.compras = new ArrayList<>();
    }

    //Getters.
    public long getCodigo_producto() { return codigo_producto; }
    public String getNombre_producto() { return nombre_producto; }
    public BigDecimal getPrecio_producto() { return precio_producto; }
    public String getDescripcion_producto() { return descripcion_producto; }
    public List<Compra> getCompras() { return compras; }

    //Setters.
    public void setCodigo_producto(long codigo_producto) { this.codigo_producto = codigo_producto; }
    public void setNombre_producto(String nombre_producto) { this.nombre_producto = nombre_producto; }
    public void setPrecio_producto(BigDecimal precio_producto) { this.precio_producto = precio_producto; }
    public void setDescripcion_producto(String descripcion_producto) { this.descripcion_producto = descripcion_producto; }
    public void setCompras(List<Compra> compras) { this.compras = compras; }

    //Metodos.
}
