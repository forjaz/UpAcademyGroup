package io.altar.upacademy.dto;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import io.altar.upacademy.model.Receita;

@ManagedBean(eager = true)
@ApplicationScoped
@Named("receitaActual")
public class ReceitaActual implements Serializable {
	private static final long serialVersionUID = 1L;




	private Receita r;

	public Receita getR() {
		return r;
	}

	public void setR(Receita r) {
		this.r = r;
	}


	
	

}
