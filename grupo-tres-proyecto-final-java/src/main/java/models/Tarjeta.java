package models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String numCard;

    private int amount;

    /*@JoinColumn(name = "usuario_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario cardOwner;*/

    @OneToOne
    @JoinColumn(name = "dni_usuario")
    private Usuario usuario;

    public Tarjeta(String numCard, int amount) {
    }

    public Tarjeta(String numCard, int amount, Usuario usuario) {
        this.numCard = numCard;
        this.amount = amount;
        this.usuario = usuario;
    }

    public Tarjeta() {
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

    /*public Usuario getCardOwner() {
        return cardOwner;
    }*/

    /*public void setCardOwner(Usuario cardOwner) {
        this.cardOwner = cardOwner;
    }*/
}
