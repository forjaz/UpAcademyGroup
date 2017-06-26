package io.altar.upacademy.dto;

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
import java.util.List;

@ManagedBean(eager = true)
@Named("receitaDto")
@SessionScoped
@Transactional

public class ReceitasDto extends EntityService implements Serializable {

	private static final long serialVersionUID = 1L;
	private Receita receita;

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}
	
	public String newReceita(Receita recipie) {
		recipie.setValidacao("invalida");
		setReceita(recipie);
		em.persist(recipie);
		return "ingrediente_Receita";
	}
	
	public String validar(Long id) {
		Receita emp=em.find(Receita.class, id);
		emp.setValidacao("valida");
		em.merge(emp);
		return "index";
	}
	public String negar(Long id) {
		Receita emp=em.find(Receita.class, id);
		emp.setValidacao("invalida");
		em.remove(emp);
		return "index";
	}
	
	public String calcular() {
		long id4 = 4;
		long id = ((Number)em.createNativeQuery("SELECT id FROM Receita R "+
				"WHERE R.nome='"+receita.getNome()+"'").getSingleResult()).longValue();

		int cal = ((Number)em.createNativeQuery("SELECT sum(calorias*Quantidade/100) FROM Ingrediente I "+
				"INNER JOIN Receita_Ingrediente RI ON I.id = RI.ingrediente_id WHERE RI.receita_id="+id4).getSingleResult()).intValue();
		
		double prot = ((Number)em.createNativeQuery("SELECT sum(proteina*Quantidade/100) FROM Ingrediente I "+
				"INNER JOIN Receita_Ingrediente RI ON I.id = RI.ingrediente_id WHERE RI.receita_id="+id4).getSingleResult()).doubleValue();
		
		double hid = ((Number)em.createNativeQuery("SELECT sum(hidratos*Quantidade/100) FROM Ingrediente I "+
				"INNER JOIN Receita_Ingrediente RI ON I.id = RI.ingrediente_id WHERE RI.receita_id="+id4).getSingleResult()).doubleValue();
		
		double gord = ((Number)em.createNativeQuery("SELECT sum(gorduras*Quantidade/100) FROM Ingrediente I "+
				"INNER JOIN Receita_Ingrediente RI ON I.id = RI.ingrediente_id WHERE RI.receita_id="+id4).getSingleResult()).doubleValue();
		
		int qtd = ((Number)em.createNativeQuery("SELECT sum(Quantidade) FROM Ingrediente I "+
				"INNER JOIN Receita_Ingrediente RI ON I.id = RI.ingrediente_id WHERE RI.receita_id="+id4).getSingleResult()).intValue();
		
		int ppl;
		if(receita.getTipo()=="sobremesa"){
			ppl = qtd/100 +1;
		}else{
			ppl = qtd/350 +1;
		}
	
		
		Receita emp=em.find(Receita.class, id);
		emp.setCalorias(cal/ppl);
		emp.setProteina(Math.round(prot/(double)ppl));
		emp.setHidratos(Math.round(hid/(double)ppl));
		emp.setGorduras(Math.round(gord/(double)ppl));
		emp.setnPessoas(ppl);

		em.merge(emp);
		
		
		return "migeG";
	}
}
