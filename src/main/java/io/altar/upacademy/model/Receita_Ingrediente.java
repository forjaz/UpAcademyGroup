package io.altar.upacademy.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Receita_Ingrediente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private int Quantidade;
    @ManyToOne
    private Receita receita;
    @ManyToOne
    private Ingrediente ingrediente;

   // Constructor
    public Receita_Ingrediente() {

    }

    // Getters and Setters
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    @ManyToOne(optional = false)
    private Ingrediente ingredientes;

    public Ingrediente getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Ingrediente ingredientes) {
        this.ingredientes = ingredientes;
    }

    @ManyToOne(optional = false)
    private Receita receitas;

    public Receita getReceitas() {
        return receitas;
    }

    public void setReceitas(Receita receitas) {
        this.receitas = receitas;
    }
}
