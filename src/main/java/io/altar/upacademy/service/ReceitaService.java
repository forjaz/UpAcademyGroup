package io.altar.upacademy.service;

import io.altar.upacademy.model.Receita;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named("receitaService")
@RequestScoped
@Transactional
public class ReceitaService extends EntityService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Constructor
	public ReceitaService() {

	}

	// DB Methods
	public String newReceita(Receita receita) {
		em.persist(receita);
		
		return "receitas";
	}
	
	

	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
