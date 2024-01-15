package models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int numero_compra;

    private String informacion_compra;
    @OneToMany( mappedBy = "boleta")
    private List<Compra> compras = new ArrayList<>();
    @ManyToOne
    private Usuario usuario;

    //Constructores.
    public Boleta() { }
    public Boleta(String informacion_compra) { this.informacion_compra = informacion_compra; }
    public Boleta(String informacion_compra, Usuario usuario) {
        this.informacion_compra = informacion_compra;
        this.usuario = usuario; }

        //Getters
        public String getInformacion_compra() { return informacion_compra; }
        public List<Compra> getCompra() { return compras; }
        public Usuario getUsuario() { return usuario; }

        //Setters.
        public void setInformacion_compra(String informacion_compra) { this.informacion_compra = informacion_compra; }
        public void addCompra(Compra compra) { this.compras.add(compra); }
        public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    }


