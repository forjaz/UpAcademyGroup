package io.altar.upacademy.service;


import io.altar.upacademy.model.Ingrediente;
import io.altar.upacademy.model.Receita;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
		
		return "ingrediente_Receita";
	}
	
	public String selectRecipe(int id) {
		
		List<Long> receitaL = global.getRenderedResult();
		if (receitaL != null && receitaL.size()>0) {
			Long l = global.getRenderedResult().get(id);
			//this.loadReceita(l);z
		}
		System.out.println(id);
		return "index";
	}

	public Receita getReceitaFromID(int receitaID) {
	    List list = em.createQuery("SELECT e FROM Receita e").getResultList();
	    return (Receita) list.get(receitaID - 1);
    }

	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
