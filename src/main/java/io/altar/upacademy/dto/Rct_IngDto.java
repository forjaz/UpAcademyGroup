package io.altar.upacademy.dto;

import io.altar.upacademy.model.Ingrediente;
import io.altar.upacademy.model.Receita;
import io.altar.upacademy.model.Receita_Ingrediente;
import io.altar.upacademy.service.EntityService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.transaction.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@ManagedBean(eager = true)
@Named("rct_IngDto")
@SessionScoped
@Transactional

public class Rct_IngDto extends EntityService implements Serializable {

	private static final long serialVersionUID = 1L;
	private Receita_Ingrediente rctIng;

	private LinkedHashMap<String,Double> conversor = new LinkedHashMap<String,Double>();

	// Constructor
	public Rct_IngDto() {
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
	
	public String newRct_Ing(Receita recipie, Ingrediente ingredient, Receita_Ingrediente rct_ing) {
		
		System.out.println("fdsfsdfsdfdsffdsfdjhdfshdfhdsfhffkfgadjskfhdshfdsjgfgfgdsgfgdsgkjfgdsgjkfgdsgfgdsgfgdsgfgdsgfgdsgjkfgdsgjkfgdsgjkfgdsgfgdsgjkfjgdsgfgdsgjkfgsdjkfgsdjkfgfdsf comeca aqui");
		//sacar o id da receita através do nome
		long idR = ((Number)em.createNativeQuery("SELECT id FROM Receita R "+
				"WHERE R.nome='"+recipie.getNome()+"'").getSingleResult()).longValue();
		
		System.out.println(idR);
		
		//sacar a receita da base de dados
		Receita emr=em.find(Receita.class, idR);
		
		//set da receita no Rct_ing
		rct_ing.setReceita(emr);
		
		//repetir o processo para o ingrediente
		
		long idI = ((Number)em.createNativeQuery("SELECT id FROM Ingrediente I "+
				"WHERE I.nome='"+ingredient.getNome()+"' && I.modoPreparacao='"+ingredient.getModoPreparacao()+"'").getSingleResult()).longValue();
		
		System.out.println(idI+idR);
		
		Ingrediente emp=em.find(Ingrediente.class, idI);
		
		rct_ing.setIngrediente(emp);
		
		//determinar as quantidades em gramas
		
		
		
		int qtd = (int) (rct_ing.getQuantidadeCliente()*conversor.get(rct_ing.getMedidas()));
		rct_ing.setQuantidadeCliente(qtd);
		
		em.persist(rct_ing);
		
		return "migeG";
	}
	
}
