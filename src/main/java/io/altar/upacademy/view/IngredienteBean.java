package io.altar.upacademy.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import io.altar.upacademy.model.Ingrediente;
import io.altar.upacademy.service.IngredienteService;

@Named("ingredienteBean")
@RequestScoped
public class IngredienteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Ingrediente ingrediente = new Ingrediente();
	private List<String> preparacoes = Arrays.asList("cru","cozido","grelhado","assado","frito");


	// Constructor
	public IngredienteBean() {

	}

	// Injector
	@Inject
	private IngredienteService ingredienteService;
	
	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public IngredienteService getIngredienteService() {
		return ingredienteService;
	}

	public void setIngredienteService(IngredienteService ingredienteService) {
		this.ingredienteService = ingredienteService;
	}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

	public List<String> getPreparacoes() {
		return preparacoes;
	}

	public void setPreparacoes(List<String> preparacoes) {
		this.preparacoes = preparacoes;
	}

	
}
