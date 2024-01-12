package models;

import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long numero_compra;
    @ManyToOne
    @JoinColumn (name = "codigo_producto")
    private Boleta boleta;

    @ManyToMany(mappedBy = "compras", cascade = CascadeType.ALL)
    private List<Producto> productos = new ArrayList<>();

    //Atributos.
    private int cantidad_producto;
    private boolean esta_pagada;
    private int total_compra;
    private Date fecha_compra;

    //Constructores.
    public Compra() { }
    public Compra(long numero_compra, int cantidad_producto, boolean esta_pagada, int total_compra) {
        this.numero_compra = numero_compra;
        this.cantidad_producto = cantidad_producto;
        this.esta_pagada = esta_pagada;
        this.total_compra = total_compra;
        this.fecha_compra = new Date(); }
    public Compra(Boleta boleta, long numero_compra, int cantidad_producto, boolean esta_pagada, int total_compra) {
        this.boleta = boleta;
        this.numero_compra = numero_compra;
        this.cantidad_producto = cantidad_producto;
        this.esta_pagada = esta_pagada;
        this.total_compra = total_compra;
        this.fecha_compra = new Date(); }

    //Getters.
    public long getNumero_compra() { return numero_compra; }
    public int getCantidad_producto() { return cantidad_producto; }
    public boolean isEsta_pagada() { return esta_pagada; }
    public int getTotal_compra() { return total_compra; }
    public Date getFecha_compra() { return fecha_compra; }
    public Boleta getBoleta() { return boleta; }

    //Setters.
    public void setNumero_compra(long numero_compra) { this.numero_compra = numero_compra; }
    public void setCantidad_producto(int cantidad_producto) { this.cantidad_producto = cantidad_producto; }
    public void setEsta_pagada(boolean esta_pagada) { this.esta_pagada = esta_pagada; }
    public void setTotal_compra(int total_compra) { this.total_compra = total_compra; }
    public void setFecha_compra(Date fecha_compra) { this.fecha_compra = fecha_compra; }
    public void setBoleta(Boleta boleta) { this.boleta = boleta; }


}

