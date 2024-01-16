package models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String numCard;

    private int amount;

    @OneToOne
    @JoinColumn(name = "dni_usuario", unique = true)
    private Usuario usuario;

    public Tarjeta() {
    }

    public Tarjeta(String numCard, int amount, Usuario usuario) {
        this.numCard = numCard;
        this.amount = amount;
        this.usuario = usuario;
    }
    public Tarjeta(String numCard, int amount) {
    }

    public Long getId() {
        return id;
    }

    public String getNumCard() {
        return numCard;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}