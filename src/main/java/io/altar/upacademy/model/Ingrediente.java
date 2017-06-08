package io.altar.upacademy.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Ingrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
    private Long id;
    private String nome;
    private double proteina;
    private double hidratos;
    private double gordurat;
    private int calorias;


	// Constructor
	public Ingrediente() {

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getProteina() {
        return proteina;
    }

    public void setProteina(double proteina) {
        this.proteina = proteina;
    }

    public double getHidratos() {
        return hidratos;
    }

    public void setHidratos(double hidratos) {
        this.hidratos = hidratos;
    }

    public double getGordurat() {
        return gordurat;
    }

    public void setGordurat(double gordurat) {
        this.gordurat = gordurat;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

}
