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
	
	public List<Long> returnIdIngrediente(){
		List<Long> lista = em.createQuery("SELECT e FROM Ingrediente e").getResultList();
		return lista;
	}
	
	public List<Receita> returnIdReceita(){
		List<Receita> lista = em.createQuery("SELECT nome FROM Receita e WHERE id=3").getResultList();
		return lista;
	}
	
	public List<Receita_Ingrediente> returnReceitaIngrediente(){
		List<Receita_Ingrediente> lista = em.createQuery("SELECT e FROM Ingrediente e").getResultList();
		return lista;
	}
	
	// GERADOR DE RECEITAS
	public List<Long> returnIdI(){
		String var = megaBean.getProcura();
		List<Long> lista1 = em.createQuery("SELECT id FROM Ingrediente e WHERE nome='"+var+"'").getResultList();
		if(lista1.size()==0){
			return returnIdIngrediente();
		}else{
			
			String query = "SELECT e.receita.id FROM Receita_Ingrediente e WHERE ";
			long a = lista1.get(0);
			query = query + "ingrediente_id=" + a;
			List<Long> lista2 = em.createQuery(query).getResultList();
			
			List<Long> lista3 = em.createQuery("SELECT nome FROM Receita e").getResultList();
			for(int i=0; i<lista2.size(); i++){
				if(lista2.size()==1){
					query = "SELECT nome FROM Receita e WHERE ";
					long b = lista2.get(i);
					query = query + "id=" + b;
					lista3 = em.createQuery(query).getResultList();
				}else if(lista2.size()==2){
					query = "SELECT nome FROM Receita e WHERE ";
					long b = lista2.get(i);
					long c = lista2.get(i+1);
					query = query + "id=" + b + " or id="+c;
					lista3 = em.createQuery(query).getResultList();
					break;
				}
					
			}
			return lista3;
		}
	}
	
	public List<Long> returnIdRI(){
		long a = returnIdI().get(0);
		List<Long> lista = em.createQuery("SELECT e.receita.id FROM Receita_Ingrediente e WHERE ingrediente_id="+a).getResultList();
		return lista;
	}
	
	public List<Long> returnNomeR(){	
		String b = returnIdRI().toString();
		int b1 = Integer.parseInt(b);
		List<Long> lista = em.createQuery("SELECT nome FROM Receita e WHERE id="+b1+"").getResultList();
		return lista;
	}
	
	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

	

