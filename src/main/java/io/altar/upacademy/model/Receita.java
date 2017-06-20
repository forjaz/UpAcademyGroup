package io.altar.upacademy.model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Column;

@Entity
public class Receita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private double proteina;
	private double hidratos;
	private double gorduras;
	private int calorias;
	private String tipo;
	private int nPessoas;
	@Lob
	@Column
	private String imagem;
	@Lob
	@Column
	private String preparacao;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getnPessoas() {
		return nPessoas;
	}

	public void setnPessoas(int nPessoas) {
		this.nPessoas = nPessoas;
	}

}
