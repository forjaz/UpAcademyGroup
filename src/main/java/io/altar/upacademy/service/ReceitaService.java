package io.altar.upacademy.service;

import io.altar.upacademy.dto.ReceitaActual;
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
	
	@Inject
	ReceitaActual receitaActual;
	
	public void loadReceita(Receita r) {
		
		this.receitaActual.setR(r);
		
	}
	
	// Constructor
	public ReceitaService() {

	}

	// DB Methods
	public String newReceita(Receita receita) {
		em.persist(receita);
		
		return "receitas";
	}
	
	public String selectRecipe() {
		
		List<Receita> receitaL = em.createQuery("Select e FROM Receita e").getResultList();
		if (receitaL != null && receitaL.size()>0) {
			Random random = new Random();
			int min = 0;
			int max = receitaL.size();
			int index = random.nextInt(max - min + 1) + min;
			Receita r = receitaL.get(2);
			this.loadReceita(r);
		}
		return "detalhe-receita";
	}

	public Receita getReceitaFromID(int receitaID) {
	    List list = em.createQuery("SELECT e FROM Receita e").getResultList();
	    return (Receita) list.get(receitaID - 1);
    }

	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ReceitaActual getReceitaActual() {
		return receitaActual;
	}

	public void setReceitaActual(ReceitaActual receitaActual) {
		this.receitaActual = receitaActual;
	}
	
	
}
