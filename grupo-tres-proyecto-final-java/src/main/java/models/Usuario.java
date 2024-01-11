package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Scanner;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @OneToMany
    @JoinColumn(name = "dni_usuario")
    private Boleta boleta;

    //Atributos.
    private int dni_usuario;
    private String nombre_usuario;
    private String direccion_usuario;
    private String email_usuario;
    private String role;

    //Constructores.
    public Usuario() { }
    public Usuario(int dni_usuario, String nombre_usuario, String direccion_usuario, String email_usuario, String role) {
        this.dni_usuario = dni_usuario;
        this.nombre_usuario = nombre_usuario;
        this.direccion_usuario = direccion_usuario;
        this.email_usuario = email_usuario;
        this.role = role; }
    public Usuario(Boleta boleta, int dni_usuario, String nombre_usuario, String direccion_usuario, String email_usuario, String role) {
        this.boleta = boleta;
        this.dni_usuario = dni_usuario;
        this.nombre_usuario = nombre_usuario;
        this.direccion_usuario = direccion_usuario;
        this.email_usuario = email_usuario;
        this.role = role; }

    //Getters.
    public int getDni_usuario() { return dni_usuario; }
    public String getNombre_usuario() { return nombre_usuario; }
    public String getDireccion_usuario() { return direccion_usuario; }
    public String getEmail_usuario() { return email_usuario; }
    public String getRole() { return role; }
    public Boleta getBoleta() { return boleta; }

    //Setters.
    public void setDni_usuario(int dni_usuario) { this.dni_usuario = dni_usuario; }
    public void setNombre_usuario(String nombre_usuario) { this.nombre_usuario = nombre_usuario; }
    public void setDireccion_usuario(String direccion_usuario) { this.direccion_usuario = direccion_usuario; }
    public void setEmail_usuario(String email_usuario) { this.email_usuario = email_usuario; }
    public void setRole(String role) { this.role = role; }
    public void setBoleta(Boleta boleta) { this.boleta = boleta; }

    //Metodos.

}
