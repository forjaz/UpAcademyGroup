package io.altar.upacademy.service;

import java.awt.List;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import io.altar.upacademy.model.Ingrediente;
import io.altar.upacademy.model.Receita;

@Named("receitaIngredienteService")
@RequestScoped
@Transactional

public class Receita_IngredienteService extends EntityService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// Constructor
	public Receita_IngredienteService() {
		
	}

	// DB Methods
	public String newReceita_Ingrediente(Ingrediente ingrediente, Receita receita) {
		em.persist(ingrediente);
		em.persist(receita);
		return "ingrediente_Receita" ;
	}
	
	/*
	public List returnIds(String entity){
	return em.createQuery("SELECT c FROM Ingrediente c WHERE c.id LIKE :custId")
			.setParameter("custId", id)
			.getResultList();
	}
	*/
	
	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}

	

