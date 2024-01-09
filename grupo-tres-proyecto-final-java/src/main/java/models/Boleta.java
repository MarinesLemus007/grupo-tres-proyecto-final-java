package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private String informacion_compra;
    @OneToMany
    @JoinColumn (name = "numero_compra")
    private Compra compra;
    @OneToMany
    @JoinColumn(name = "dni_usuario")
    private Usuario usuario;

    //Constructores.
    public Boleta() { }
    public Boleta(String informacion_compra) { this.informacion_compra = informacion_compra; }
    public Boleta(String informacion_compra, Compra compra, Usuario usuario) {
        this.informacion_compra = informacion_compra;
        this.compra = compra;
        this.usuario = usuario; }

    //Getters
    public String getInformacion_compra() { return informacion_compra; }
    public Compra getCompra() { return compra; }
    public Usuario getUsuario() { return usuario; }

    //Setters.
    public void setInformacion_compra(String informacion_compra) { this.informacion_compra = informacion_compra; }
    public void setCompra(Compra compra) { this.compra = compra; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    //Metodos.

}
