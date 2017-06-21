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
@Named("global")
@SessionScoped
public class Global extends EntityService implements Serializable {

	private static final long serialVersionUID = 1L;
	private Receita receitaPlaceholder;
	private List<Long> searchQuery;
	private List<Receita> receitaResult = new ArrayList<>();
	private Receita uniqueReceita;
	private List<Receita_Ingrediente> uniqueReceitaIngredienteList;
	private int startGridIndex = 0;

	// Constructor
	public Global() {
	}

	@PostConstruct
	@Transactional
	public void renderResultInit() {
		receitaPlaceholder = (Receita) em.createQuery("SELECT e FROM Receita e WHERE id=1").getResultList().get(0);
		receitaResult.add(receitaPlaceholder);
		receitaResult.add(receitaPlaceholder);
		receitaResult.add(receitaPlaceholder);
		receitaResult.add(receitaPlaceholder);
	}

	// Methods
	public List returnIngredientes() {
		return em.createQuery("SELECT e FROM Ingrediente e ORDER BY e.nome").getResultList();
	}

	// 1 SEARCH INGREDIENTES IN SEARCH BAR
	public void searchIngredientes() {
		List<Long> receitaIDList = convertIngredienteIDToReceitaID(searchQuery);
		List<Receita> receitaList = getReceitaFromReceitaID(receitaIDList);
		receitaResult = ensureLengthMultipleOfFour(receitaList);
	}

	// 1.1 CONVERT INGREDIENTE ID --> RECEITA ID
	public List<Long> convertIngredienteIDToReceitaID(List<Long> inputList) {
		StringBuilder query = new StringBuilder("SELECT e.receita.id FROM Receita_Ingrediente e WHERE ");
		int inputSize = inputList.size();

		// Field for Empty Input
		List<Long> placeholder = new ArrayList<>();
		placeholder.add(1L);

		if (inputList.isEmpty()) {
			return placeholder;
		} else {
			for (int i = 0; i < inputSize; i++) {
				if (i == inputSize - 1) {
					query.append("ingrediente_id=").append(inputList.get(i)).append(" GROUP BY receita_id");
				} else {
					query.append("ingrediente_id=").append(inputList.get(i)).append(" or ");
				}
			}
			return em.createQuery(query.toString()).getResultList();
		}
	}

	// 1.3 CONVERT RECEITAID LIST TO RECEITA
	public List<Receita> getReceitaFromReceitaID(List<Long> inputList) {
		StringBuilder query = new StringBuilder("SELECT e FROM Receita e WHERE ");
		int inputSize = inputList.size();
		for (int i = 0; i < inputSize; i++) {
			if (i == inputSize - 1) {
				query.append("id=").append(inputList.get(i));
			} else {
				query.append("id=").append(inputList.get(i)).append(" or ");
			}
		}
		return em.createQuery(query.toString()).getResultList();
	}

	// 1.2 ENSURE MULTIPLES OF FOUR IN RENDERED LIST FOR NAVIGATION
	public List<Receita> ensureLengthMultipleOfFour(List<Receita> inputList) {
		while (inputList.size() % 4 != 0) {
			inputList.add(receitaPlaceholder);
		}
		return inputList;
	}

	// NAVIGATE LEFT
	public void renderLeft() {
		if (startGridIndex > 3) {
			startGridIndex -= 4;
		}
	}

	// NAVIGATE RIGHT
	public void renderRight() {
		if (receitaResult.size() > startGridIndex + 4) {
			startGridIndex += 4;
		}
	}

	// 2 GET RECEITA ID FROM CLICKABLE IMAGE
	public String showUniqueReceita(int index) {
		uniqueReceita = receitaResult.get(index);
		return "receita-detalhe";
	}

	// 3 GETS INGREDIETES FROM RECEITA ID AND RETURNS A COLUMN OF THEIR NAMES
	public String showIngredientesColumnFromReceitaID(Long ID) {
		uniqueReceitaIngredienteList = getReceitaIngredienteFromReceitaID(ID);
		List<String> uniqueIngredienteNomeList = getIngredienteNomeFromReceitaIngredienteID(
				uniqueReceitaIngredienteList);
		return showIngredienteNomeList(uniqueIngredienteNomeList);
	}

	// 3.1 GET RECEITA_INGREDIENTE LIST FROM RECEITA ID
	public List<Receita_Ingrediente> getReceitaIngredienteFromReceitaID(Long ID) {
		return em.createQuery("SELECT e FROM Receita_Ingrediente e WHERE receita_id=" + ID).getResultList();
	}

	// 3.2 GET INGREDIENTE NOME LIST FROM RECEITA_INGREDIENTE ID
	public List<String> getIngredienteNomeFromReceitaIngredienteID(List<Receita_Ingrediente> receitaIngredienteList) {
		List<String> ingredienteNomeList = new ArrayList<>();
		for (Receita_Ingrediente receitaIngrediente : receitaIngredienteList) {
			ingredienteNomeList.add(receitaIngrediente.getIngrediente().getNome());
		}
		return ingredienteNomeList;
	}

	// 3.3 SEPARATE INGREDIENTE NOMES WITH NEWLINE FROM INGREDIENTE NOME LIST
	public String showIngredienteNomeList(List<String> ingredienteNomeList) {
		StringBuilder resultString = new StringBuilder();
		for (int i = 0; i < ingredienteNomeList.size(); i++) {
			if (i != ingredienteNomeList.size() - 1) {
				resultString.append(ingredienteNomeList.get(i)).append("\n ");
			} else {
				resultString.append(ingredienteNomeList.get(i));
			}
		}
		return resultString.toString();
	}

	// 4 GETS QUANTIDADES FROM RECEITA ID AND RETURNS A COLUMN OF THEIR NAMES
	public String showQuantidadeColumnFromReceitaID(Long ID) {
		uniqueReceitaIngredienteList = getReceitaIngredienteFromReceitaID(ID);
		List<Integer> uniqueIngredienteQuantidadeList = getIngredienteQuantidadeFromReceitaIngredienteID(
				uniqueReceitaIngredienteList);
		return showIngredienteQuantidadeList(uniqueIngredienteQuantidadeList);
	}

	public List<Integer> getIngredienteQuantidadeFromReceitaIngredienteID(
			List<Receita_Ingrediente> receitaIngredienteList) {
		List<Integer> ingredienteQuantidadeList = new ArrayList<>();
		for (Receita_Ingrediente receitaIngrediente : receitaIngredienteList) {
			ingredienteQuantidadeList.add(receitaIngrediente.getQuantidade());
		}
		return ingredienteQuantidadeList;
	}

	public String showIngredienteQuantidadeList(List<Integer> ingredienteQuantidadeList) {
		StringBuilder resultString = new StringBuilder();
		for (int i = 0; i < ingredienteQuantidadeList.size(); i++) {
			if (i != ingredienteQuantidadeList.size() - 1) {
				resultString.append(ingredienteQuantidadeList.get(i)).append("\n ");
			} else {
				resultString.append(ingredienteQuantidadeList.get(i));
			}
		}
		return resultString.toString();
	}

	// 4 GETS MEDIDAS FROM RECEITA ID AND RETURNS A COLUMN OF THEIR NAMES
	public String showMedidaColumnFromReceitaID(Long ID) {
		uniqueReceitaIngredienteList = getReceitaIngredienteFromReceitaID(ID);
		List<String> uniqueIngredienteMedidaList = getIngredienteMedidaFromReceitaIngredienteID(
				uniqueReceitaIngredienteList);
		return showIngredienteMedidaList(uniqueIngredienteMedidaList);
	}

	public List<String> getIngredienteMedidaFromReceitaIngredienteID(List<Receita_Ingrediente> receitaIngredienteList) {
		List<String> ingredienteMedidaList = new ArrayList<>();
		for (Receita_Ingrediente receitaIngrediente : receitaIngredienteList) {
			ingredienteMedidaList.add(receitaIngrediente.getMedidas());
		}
		return ingredienteMedidaList;
	}

	public String showIngredienteMedidaList(List<String> ingredienteMedidaList) {
		StringBuilder resultString = new StringBuilder();
		for (int i = 0; i < ingredienteMedidaList.size(); i++) {
			if (i != ingredienteMedidaList.size() - 1) {
				resultString.append(ingredienteMedidaList.get(i)).append("\n ");
			} else {
				resultString.append(ingredienteMedidaList.get(i));
			}
		}
		return resultString.toString();
	}

	// Getters and Setters

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<Long> getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(List<Long> searchQuery) {
		this.searchQuery = searchQuery;
	}

	public Receita getUniqueReceita() {
		return uniqueReceita;
	}

	public void setUniqueReceita(Receita uniqueReceita) {
		this.uniqueReceita = uniqueReceita;
	}

	public List<Receita_Ingrediente> getUniqueReceitaIngredienteList() {
		return uniqueReceitaIngredienteList;
	}

	public void setUniqueReceitaIngredienteList(List<Receita_Ingrediente> uniqueReceitaIngredienteList) {
		this.uniqueReceitaIngredienteList = uniqueReceitaIngredienteList;
	}

	public int getStartGridIndex() {
		return startGridIndex;
	}

	public void setStartGridIndex(int startGridIndex) {
		this.startGridIndex = startGridIndex;
	}

	public List<Receita> getReceitaResult() {
		return receitaResult;
	}

	public void setReceitaResult(List<Receita> receitaResult) {
		this.receitaResult = receitaResult;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Receita getReceitaPlaceholder() {
		return receitaPlaceholder;
	}

	public void setReceitaPlaceholder(Receita receitaPlaceholder) {
		this.receitaPlaceholder = receitaPlaceholder;
	}

}
