package io.altar.upacademy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private double proteina;
	private double hidratos;
	private double gorduras;
	private int calorias;
	private String modoPreparacao;
	


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

	public double getGorduras() {
		return gorduras;
	}

	public void setGorduras(double gorduras) {
		this.gorduras = gorduras;
	}

	public int getCalorias() {
		return calorias;
	}

	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}

	public String getModoPreparacao() {
		return modoPreparacao;
	}

	public void setModoPreparacao(String modoPreparacao) {
		this.modoPreparacao = modoPreparacao;
	}

}
