package models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @OneToMany( mappedBy = "boleta")
    private List<Compra> compras = new ArrayList<>();

    @ManyToOne
    private Usuario usuario;

    // Atributos de info_compra
    private long codigo_producto;
    private int cantidad_producto;
    private BigDecimal total_compra;
    private boolean esta_pagada;
    //List<String> info_compra = new ArrayList<>();

    //Constructores.
    public Boleta() {}

    //Getters
    public long getId() { return id; }
    public List<Compra> getCompras() {return compras; }
    public Usuario getUsuario() { return usuario;}
    public long getCodigo_producto() { return codigo_producto;}
    public int getCantidad_producto() { return cantidad_producto; }
    public BigDecimal getTotal_compra() { return total_compra; }
    public boolean isEsta_pagada() { return esta_pagada;}
    //public List<String> getInfo_compra() { return info_compra; }

    //Setters.
    public void setId(long id) { this.id = id; }
    public void setCompras(List<Compra> compras) { this.compras = compras; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setCodigo_producto(long codigo_producto) { this.codigo_producto = codigo_producto; }
    public void setCantidad_producto(int cantidad_producto) { this.cantidad_producto = cantidad_producto; }
    public void setTotal_compra(BigDecimal total_compra) { this.total_compra = total_compra; }
    public void setEsta_pagada(boolean esta_pagada) { this.esta_pagada = esta_pagada; }
    //public void setInfo_compra(List<String> info_compra) { this.info_compra = info_compra; }

    //Metodos.

    public List<String> obtenerInfoCompraConsolidada() {
        List<String> infoConsolidada = new ArrayList<>();
        for (Compra compra : compras) {
            for (Producto producto : compra.getProductos()) {
                // Procesar la información de cada producto y agregar a la lista
                String info = "Producto: " + producto.getNombre_producto() +
                        ", Cantidad: " + compra.getCantidad_producto() +
                        ", Total: " + compra.getTotal_compra();
                infoConsolidada.add(info);
            }
        }
        return infoConsolidada;
    }
}