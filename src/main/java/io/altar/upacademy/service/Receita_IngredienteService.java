package io.altar.upacademy.service;

import java.util.Arrays;
import java.util.List;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import io.altar.upacademy.model.Ingrediente;
import io.altar.upacademy.model.Receita;
import io.altar.upacademy.model.Receita_Ingrediente;

@Named("receitaIngredienteService")
@RequestScoped
@Transactional

public class Receita_IngredienteService extends EntityService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private megaRepository megaBean;
	
	public megaRepository getMegaBean() {
		return megaBean;
	}

	public void setMegaBean(megaRepository megaBean) {
		this.megaBean = megaBean;
	}

	// Constructor
	public Receita_IngredienteService() {
		
	}

	// DB Methods
	public String newReceita_Ingrediente(Receita_Ingrediente ir) {
		em.persist(ir);
		return "ingrediente_Receita" ;
	}
	
	public List<Ingrediente> returnIdIngrediente(){
		List<Ingrediente> lista = em.createQuery("SELECT e FROM Ingrediente e").getResultList();
		return lista;
	}
	
	public List<Receita> returnIdReceita(){
		List<Receita> lista = em.createQuery("SELECT e FROM Receita e WHERE id=1").getResultList();
		return lista;
	}
	
	public List<Receita_Ingrediente> returnReceitaIngrediente(){
		List<Receita_Ingrediente> lista = em.createQuery("SELECT e FROM Ingrediente e").getResultList();
		return lista;
	}
	
	// GERADOR DE RECEITAS
	public List<Ingrediente> returnIdReceitaIngrediente(){
		String var = megaBean.getProcura();
		List<Ingrediente> lista = em.createQuery("SELECT id FROM Ingrediente e WHERE nome='"+var+"'").getResultList();
		return lista;
	}
	
//	public List<Receita_Ingrediente> idReceita(){
//		String a = returnIdReceitaIngrediente().toString();
//		List<Receita_Ingrediente> lista = em.createQuery("SELECT receita.id FROM Receita_Ingrediente e WHERE ingrediente.id='"+a+"'").getResultList();
//		return lista;
//	}
	
	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
	

}

	

