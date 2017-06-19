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
	
	
	// DB QUERIES EXAMPLES
	public List<Long> returnIdIngrediente(){
		List<Long> lista = em.createQuery("SELECT nome FROM Ingrediente e").getResultList();
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
	
	// GERADOR DE RECEITAS APENAS COM UM INGREDIENTE 
	public List<Long> returnIdI(){
		String var1 = megaBean.getProcura();
		String var2 = megaBean.getProcura2();
		String var3 = megaBean.getProcura3();
		
		String query = "SELECT id FROM Ingrediente e WHERE ";
		query = query + "nome='"+var1+"'" + " or nome='"+var2+"'" + " or nome='"+var3+"'";
		String query1 = "SELECT e.receita.id FROM Receita_Ingrediente e WHERE ";
		String query2 = "SELECT nome FROM Receita e WHERE ";
		
		List<Long> lista1 = em.createQuery(query).getResultList();
		if(lista1.size()==0){
			return returnIdIngrediente();
		}else{
			
			for(int i=0; i<lista1.size(); i++){
				if(i+1<lista1.size()){
					long a = lista1.get(i);
					query1 = query1 + "ingrediente_id=" + a + " or ";
				}else{
					long a1 = lista1.get(i);
					query1 = query1 + "ingrediente_id=" + a1;
				}
			}
			
			List<Long> lista2 = em.createQuery(query1).getResultList();
			
			for(int i=0; i<lista2.size(); i++){
				if(i+1<lista2.size()){
					long b = lista2.get(i);
					query2 = query2 + "id=" + b + " or ";
					
				}else{
					long b1 = lista2.get(i);
					query2 = query2 + "id=" + b1;
				}
					
			}
			
			
		}
		List<Long> lista3 = em.createQuery(query2).getResultList();
		return lista3;
	}
	
	// DB QUERIES TESTE 
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

	

