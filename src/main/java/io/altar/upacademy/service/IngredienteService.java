package io.altar.upacademy.service;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import io.altar.upacademy.model.Ingrediente;

@Named("ingredienteService")
@RequestScoped
@Transactional
public class IngredienteService extends EntityService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Constructor
	public IngredienteService() {
		
	}

	// DB Methods
	public String newIngrediente(Ingrediente ingrediente) {
		em.persist(ingrediente);
		return "ingredientes";
	}

	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
