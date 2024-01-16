package models;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
@Entity
public class Usuario {

    //Atributos.
    @Id
    private long dni_usuario;
    private String nombre_usuario;
    private String direccion_usuario;
    private String email_usuario;
    private String role;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Boleta> boletas;

    @OneToOne(mappedBy = "usuario")
    private Tarjeta tarjeta;

    //Constructores.
    public Usuario(String nombre, String direccion, String email, String admin) {
        this.nombre_usuario = nombre;
        this.direccion_usuario = direccion;
        this.email_usuario = email;
        this.role = admin;
    }
    public Usuario(long dni_usuario, String nombre_usuario, String direccion_usuario, String email_usuario, String role) {
        this.dni_usuario = dni_usuario;
        this.nombre_usuario = nombre_usuario;
        this.direccion_usuario = direccion_usuario;
        this.email_usuario = email_usuario;
        this.role = role;
        this.boletas = new ArrayList<>();
    }

    public Usuario() {
    }

    //Getters.
    public long getDni_usuario() { return dni_usuario; }
    public String getNombre_usuario() { return nombre_usuario; }
    public String getDireccion_usuario() { return direccion_usuario; }
    public String getEmail_usuario() { return email_usuario; }
    public String getRole() { return role; }
    public List<Boleta> getBoletas() {
        return boletas;
    }
    public Tarjeta getTarjeta(){return this.tarjeta;}

    //Setters.
    public void setDni_usuario(long dni_usuario) { this.dni_usuario = dni_usuario; }
    public void setNombre_usuario(String nombre_usuario) { this.nombre_usuario = nombre_usuario; }
    public void setDireccion_usuario(String direccion_usuario) { this.direccion_usuario = direccion_usuario; }
    public void setEmail_usuario(String email_usuario) { this.email_usuario = email_usuario; }
    public void setRole(String role) { this.role = role; }
    public void setBoletas(List<Boleta> boletas) { this.boletas = boletas; }
    public void setTarjeta(Tarjeta t) { this.tarjeta = tarjeta; }

    //Metodos.
    /*public List<String> generarInfoBoletas() {
        List<String> infoBoletas = new ArrayList<>();

        for (Boleta boleta : boletas) {
            List<String> infoConsolidada = boleta.obtenerInfoCompraConsolidada();
            infoBoletas.addAll(infoConsolidada);
        }

        return infoBoletas;
    }*/

}