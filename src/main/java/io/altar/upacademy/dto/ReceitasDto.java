package io.altar.upacademy.dto;

import io.altar.upacademy.model.Ingrediente;
import io.altar.upacademy.model.Receita;
import io.altar.upacademy.model.Receita_Ingrediente;
import io.altar.upacademy.service.EntityService;
import io.altar.upacademy.service.Paginator;

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
//coisas
	private static final long serialVersionUID = 1L;
	private Receita receita;
	private Ingrediente ingrediente;
	private Receita_Ingrediente receitaIngrediente;
	
	public String newReceita(Receita recipie) {
		recipie.setValidacao("reprovada");
		setReceita(recipie);
		em.persist(recipie);
		return "ingrediente_Receita";
	}
	
	public String validar(Long id) {
		Receita emp=em.find(Receita.class, id);
		emp.setValidacao("aprovada");
		em.merge(emp);
		return listarRep();
	}
	public String negar(Long id) {
		Receita emp=em.find(Receita.class, id);
		
		ArrayList<Receita_Ingrediente> lista= (ArrayList<Receita_Ingrediente>) em.createNativeQuery("SELECT * FROM Receita_Ingrediente RI WHERE RI.receita_id="+id, Receita_Ingrediente.class).getResultList();
		
		for(int i = 0; i < lista.size(); i++){
			Receita_Ingrediente emi= lista.get(i);
			
			em.remove(emi);
			
		}
		
		em.remove(emp);
		
		return listarRep();
	}
	
	public String calcular() {
		
		long id = ((Number)em.createNativeQuery("SELECT id FROM Receita R "+
				"WHERE R.nome='"+receita.getNome()+"'").getSingleResult()).longValue();

		int cal = ((Number)em.createNativeQuery("SELECT sum(calorias*Quantidade/100) FROM Ingrediente I "+
				"INNER JOIN Receita_Ingrediente RI ON I.id = RI.ingrediente_id WHERE RI.receita_id="+id).getSingleResult()).intValue();
		
		double prot = ((Number)em.createNativeQuery("SELECT sum(proteina*Quantidade/100) FROM Ingrediente I "+
				"INNER JOIN Receita_Ingrediente RI ON I.id = RI.ingrediente_id WHERE RI.receita_id="+id).getSingleResult()).doubleValue();
		
		double hid = ((Number)em.createNativeQuery("SELECT sum(hidratos*Quantidade/100) FROM Ingrediente I "+
				"INNER JOIN Receita_Ingrediente RI ON I.id = RI.ingrediente_id WHERE RI.receita_id="+id).getSingleResult()).doubleValue();
		
		double gord = ((Number)em.createNativeQuery("SELECT sum(gorduras*Quantidade/100) FROM Ingrediente I "+
				"INNER JOIN Receita_Ingrediente RI ON I.id = RI.ingrediente_id WHERE RI.receita_id="+id).getSingleResult()).doubleValue();
		
		int qtd = ((Number)em.createNativeQuery("SELECT sum(Quantidade) FROM Ingrediente I "+
				"INNER JOIN Receita_Ingrediente RI ON I.id = RI.ingrediente_id WHERE RI.receita_id="+id).getSingleResult()).intValue();
		
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
		global.resetReceitaGrid();

		return "aprovacao";
	}
	
	public String listarRep() {
		@SuppressWarnings("unchecked")
        List<Receita> lista= (List<Receita>) em.createQuery("SELECT e FROM Receita e WHERE validacao='reprovada'").getResultList();
        
        global.setReceitaResult(global.ensureLengthMultipleOfFour(lista));
		global.showReceitaResultCounter();
        global.setRenderPageCounter(true);
        
        return "aprovacao";
	}
	
	public String showUniqueReceita(int index) {
        global.setUniqueReceita(global.getReceitaResult().get(index));
        return "dtlAprovacao";
    }

    // Getters e Setters

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Receita_Ingrediente getReceitaIngrediente() {
        return receitaIngrediente;
    }

    public void setReceitaIngrediente(Receita_Ingrediente receitaIngrediente) {
        this.receitaIngrediente = receitaIngrediente;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }
}
