package models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Compra> compras = new ArrayList<>();
    @ManyToOne
    private Usuario usuario;

    //Constructores.
    public Boleta() {}

    public Boleta(Usuario usuario) {
        this.usuario = usuario;
    }

    //Getters
    public long getId() { return id; }
    public List<Compra> getCompras() {return compras; }
    public Usuario getUsuario() { return usuario;}

    //Setters.
    public void setId(long id) { this.id = id; }
    public void setCompras(List<Compra> compras) { this.compras = compras; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    //Metodos
}