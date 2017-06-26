package io.altar.upacademy.service;

import java.util.Arrays;
import java.util.LinkedHashMap;
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

	
	private LinkedHashMap<String,Double> conversor = new LinkedHashMap<String,Double>();

	// Constructor
	public Receita_IngredienteService() {
		conversor.put("csp",new Double(15));
		conversor.put("chá",new Double(2.5));
		conversor.put("csb",new Double(6));
		conversor.put("ccf",new Double(2.5));
		conversor.put("xíc",new Double(240));
		conversor.put("xcf",new Double(80));
		conversor.put("l",new Double(1000));
		conversor.put("ml",new Double(1));
		conversor.put("cp",new Double(200));
		conversor.put("kg",new Double(1000));
		conversor.put("g",new Double(1));
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
	

	
	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	// Input do cliente
	public String newRct_Ing(Receita recipie, Ingrediente ingredient, Receita_Ingrediente rct_ing) {
		
		//sacar o id da receita através do nome
		long idR = ((Number)em.createNativeQuery("SELECT id FROM Receita R "+
				"WHERE R.nome='"+recipie.getNome()+"'").getSingleResult()).longValue();
		
		//sacar a receita da base de dados
		Receita emr=em.find(Receita.class, idR);
		
		//set da receita no Rct_ing
		rct_ing.setReceita(emr);
		
		//repetir o processo para o ingrediente
		System.out.println(ingredient.getNome());
		
		long idI = ((Number)em.createNativeQuery("SELECT id FROM Ingrediente I "+
				"WHERE I.nome='"+ingredient.getNome()+"' && I.modoPreparacao='"+ingredient.getModoPreparacao()+"'").getSingleResult()).longValue();
		
		Ingrediente emp=em.find(Ingrediente.class, idI);
		
		rct_ing.setIngrediente(emp);
		
		//determinar as quantidades em gramas
		
		int qtd = (int) (rct_ing.getQuantidadeCliente()*conversor.get(rct_ing.getMedidas()));
		rct_ing.setQuantidade(qtd);
		
		em.persist(rct_ing);
		
		return "migeG";
	}
	
}

	

