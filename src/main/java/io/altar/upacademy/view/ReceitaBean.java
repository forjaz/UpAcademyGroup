package io.altar.upacademy.view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import io.altar.upacademy.model.Receita;
import io.altar.upacademy.service.ReceitaService;

@Named("receitaBean")
@RequestScoped
public class ReceitaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Receita receita = new Receita();
	private List<String> tipos = Arrays.asList("Vegan", "Carne","Peixe",
			"Sobremesa", "Vegetariano");

	// Constructor
	public ReceitaBean() {

	}

	// Injector
	@Inject
	private ReceitaService receitaService;

	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ReceitaService getReceitaService() {
		return receitaService;
	}

	public void setReceitaService(ReceitaService receitaService) {
		this.receitaService = receitaService;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public List<String> getTipos() {
		return tipos;
	}

	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}

}
