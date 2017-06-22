package io.altar.upacademy.view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import io.altar.upacademy.model.Receita_Ingrediente;
import io.altar.upacademy.service.Receita_IngredienteService;

@Named("receitaIngredienteBean")
@RequestScoped

public class Receita_IngredienteBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private Receita_Ingrediente receitaIngrediente = new Receita_Ingrediente();
	private List<String> unidades = Arrays.asList("csp","chá","csb","ccf","xíc","xcf","l","ml","cp","kg","g");
	
	// Constructor
	public Receita_IngredienteBean() {

	}

	// Injector
	@Inject
	private Receita_IngredienteService receitaIngredienteService;
	
	// Getters and Setters
	public Receita_Ingrediente getReceitaIngrediente() {
		return receitaIngrediente;
	}

	public void setReceitaIngrediente(Receita_Ingrediente receitaIngrediente) {
		this.receitaIngrediente = receitaIngrediente;
	}

	public Receita_IngredienteService getReceitaIngredienteService() {
		return receitaIngredienteService;
	}

	public void setReceitaIngredienteService(Receita_IngredienteService receitaIngredienteService) {
		this.receitaIngredienteService = receitaIngredienteService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<String> unidades) {
		this.unidades = unidades;
	}
	
}
