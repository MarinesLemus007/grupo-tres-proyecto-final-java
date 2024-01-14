package models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_pagos;
    @OneToOne(mappedBy = "pagos")
    @JoinColumn(name = "numero_compra")

    private Compra compra;
    private double precio_total;
    private int usuario_id;
    private int pedido_id;


    public Pagos(int id_pagos, double precio_total, int usuario_id, int pedido_id) {
        this.id_pagos = id_pagos;
        this.precio_total = precio_total;
        this.usuario_id = usuario_id;
        this.pedido_id = pedido_id;
    }

    public int getId_pagos() {
        return id_pagos;
    }

    public void setId_pagos(int id_pagos) {
        this.id_pagos = id_pagos;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }


}
