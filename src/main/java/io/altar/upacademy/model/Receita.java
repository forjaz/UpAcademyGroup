package io.altar.upacademy.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Receita implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String imagem;
    private String preparacao;
    @ManyToMany
    private Collection<Ingrediente> ingredientes;

    // Constructor
    public Receita() {
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

    public Collection<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Collection<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getPreparacao() {
        return preparacao;
    }

    public void setPreparacao(String preparacao) {
        this.preparacao = preparacao;
    }

}
