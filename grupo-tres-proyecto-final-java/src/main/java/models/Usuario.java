package models;

import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import org.hibernate.annotations.SourceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
public class Usuario {
    @Id
    private long dni_usuario;
    @OneToMany(mappedBy = "usuario")
    private List<Boleta> boletas = new ArrayList<>();

    @OneToMany(mappedBy = "compras")
    private List<Compra> compras = new ArrayList<>();


    //Atributos.
    private String nombre_usuario;
    private String direccion_usuario;
    private String email_usuario;
    private String role;


    //Constructores.
    public Usuario() { }
    public Usuario(long dni_usuario, String nombre_usuario, String direccion_usuario, String email_usuario, String role) {
        this.dni_usuario = dni_usuario;
        this.nombre_usuario = nombre_usuario;
        this.direccion_usuario = direccion_usuario;
        this.email_usuario = email_usuario;
        this.role = role; }

    //Getters.
    public long getDni_usuario() { return dni_usuario; }
    public String getNombre_usuario() { return nombre_usuario; }
    public String getDireccion_usuario() { return direccion_usuario; }
    public String getEmail_usuario() { return email_usuario; }
    public String getRole() { return role; }
    public List<Boleta> getBoleta() { return boletas; }



    //Setters.
    public void setDni_usuario(long dni_usuario) { this.dni_usuario = dni_usuario; }
    public void setNombre_usuario(String nombre_usuario) { this.nombre_usuario = nombre_usuario; }
    public void setDireccion_usuario(String direccion_usuario) { this.direccion_usuario = direccion_usuario; }
    public void setEmail_usuario(String email_usuario) { this.email_usuario = email_usuario; }
    public void setRole(String role) { this.role = role; }
    public void addBoleta(Boleta boleta) { this.boletas.add(boleta); }

    public void historialComra(){
        System.out.println("Historial de pedidos de: "+ this.nombre_usuario+" ");
        for (Compra compra : this.compras){
            System.out.println(compra);
        }
    }
    public void addCompra(Compra compra) { this.compras.add(compra); }


}
