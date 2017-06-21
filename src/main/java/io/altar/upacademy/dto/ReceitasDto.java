package io.altar.upacademy.dto;

import io.altar.upacademy.model.Receita;
import io.altar.upacademy.model.Receita_Ingrediente;
import io.altar.upacademy.service.EntityService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(eager = true)
@Named("receitaDto")
@SessionScoped
public class ReceitasDto extends EntityService implements Serializable {

	private static final long serialVersionUID = 1L;
	private Receita receita;
	
	public ArrayList<Receita> listarReceitas(){
		List<Receita> lista=  em.createNativeQuery("Select * From Receita").getResultList();
		System.out.println((ArrayList <Receita>)lista);
		return (ArrayList <Receita>)lista;
	}

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
		emp.setValidacao("valida");
		em.remove(emp);
		return "index";
	}
	
	public String calcular() {
		//String id = Long.toString(receita.getId());
		
		int esc = ((Number)em.createNativeQuery("SELECT sum(calorias) FROM Ingrediente I "+
				"INNER JOIN Receita_Ingrediente RI ON I.id = RI.ingrediente_id WHERE RI.receita_id=4").getSingleResult()).intValue();
		
				System.out.println("dsffdskfdskjfdjskfhdskfhsdkdsffdskfdskjfdjskfhdskfhsdkfhsdkfddsffdskfdskjfdjskfhdskfhsdkfhsdkfdfhsdkfdsf"+esc);
	
		return "receitas";
	}
}
