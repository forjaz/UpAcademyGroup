package io.altar.upacademy.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import io.altar.upacademy.model.Ingrediente;
import io.altar.upacademy.service.EntityService;

@ManagedBean(eager = true)
@Named("ingredienteActual")
@ApplicationScoped
public class IngredienteActual extends EntityService implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Long> searchQuery;

	// Constructor
	public IngredienteActual() {

	}

	// Methods
	public List<Ingrediente> returnIngredientes() {
		List<Ingrediente> list = em.createQuery("SELECT e FROM Ingrediente e").getResultList();
		return list;
	}

	public List<String> returnNomeIngredientes() {
		List<String> list = em.createQuery("SELECT e.nome FROM Ingrediente e").getResultList();
		return list;
	}

	public List<Long> returnIdIngredientes() {
		List<Long> list = em.createQuery("SELECT e.id FROM Ingrediente e").getResultList();
		return list;
	}

	public List<Long> convertIngredienteIDToReceitaID(List<Long> inputList) {
		String query = "SELECT e.receita_id FROM Receita_Ingrediente e WHERE ";
		List<Long> resultList = new ArrayList<>();

		if (inputList == null) {
			return resultList;

		} else {

			for (int i = 0; i < inputList.size(); i++) {
				query = query + "ingrediente_id=" + inputList.get(i) + " or ";
			}

			return em.createQuery(query).getResultList();
		}
	}

	// Getters and Setters

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Long> getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(List<Long> searchQuery) {
		this.searchQuery = searchQuery;
	}

}
