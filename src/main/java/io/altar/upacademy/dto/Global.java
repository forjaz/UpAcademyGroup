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
@Named("global")
@SessionScoped
public class Global extends EntityService implements Serializable {

	/*-----------------------------------------------------------------------------------*/
	/* FIELDS */
	/*-----------------------------------------------------------------------------------*/
	private static final long serialVersionUID = 1L;

	// Empty Receita Placeholder - Default
	private Receita receitaPlaceholder;

	// Search Bar Output Ingredientes -> Ingrediente ID
	private List<Long> searchQuery;

	// Receitas to Render in Grid
	private List<Receita> receitaResult = new ArrayList<>();

	// Receita to Render in Unique
	private Receita uniqueReceita;

	// Receita_Ingrediente to use in Ingrediente/Nutricao Table
	private List<Receita_Ingrediente> uniqueReceitaIngredienteList;

	// ReceitaResult Index that Renders 1st Image in Grid
	private int startGridIndex = 0;

	// Receita Results Non-Placeholders
	private int receitaResultNumb = 0;

	// Show Receita Result Text
	private String receitaResultCounterOutput;

	// Paginator
	private Paginator paginator;

	/*-----------------------------------------------------------------------------------*/
	/* CONSTRUCTORS */
	/*-----------------------------------------------------------------------------------*/
	public Global() {
	}

	// Fills Grid With Empty Placeholder at Start
	@PostConstruct
	@Transactional
	public void renderResultInit() {
		receitaPlaceholder = (Receita) em
				.createQuery("SELECT e FROM Receita e WHERE id=1")
				.getResultList().get(0);
		receitaResult.add(receitaPlaceholder);
		receitaResult.add(receitaPlaceholder);
		receitaResult.add(receitaPlaceholder);
		receitaResult.add(receitaPlaceholder);
		paginator = new Paginator(this.receitaResult);
	}

	/*-----------------------------------------------------------------------------------*/
	/* METHODS */
	/*-----------------------------------------------------------------------------------*/
	// 1. SELECT2 DROPDOWN IN SEARCH BAR
	@SuppressWarnings("unchecked")
	public List<Ingrediente> returnIngredientes() {
		return em.createQuery("SELECT e FROM Ingrediente e ORDER BY e.nome")
				.getResultList();
	}

	// 2 SEARCH INGREDIENTES IN SEARCH BAR
	public void searchIngredientes() {
		List<Receita> receitaList = returnReceitasOrderByHit();
		receitaResult = ensureLengthMultipleOfFour(receitaList);
		showReceitaResultCounter();
		paginator = new Paginator(this.receitaResult);
	}

	// 2.1 Returns Receitas by Number of Ingredientes in Common
	public List<Receita> returnReceitasOrderByHit() {
		List<Long> lista2 = searchQuery;
		String var = "(";
		for (int i = 0; i <= lista2.size() - 1; i++) {
			if (i < lista2.size() - 1) {
				var += lista2.get(i) + ",";
			} else {
				var += lista2.get(i) + ")";
			}
		}

		String query = "SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN "
				+ var
				+ ") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id "
				+ "WHERE RI.ingrediente_id IN " + var
				+ " GROUP BY receita_id ORDER BY Relevancia DESC";

		@SuppressWarnings("unchecked")
		List<Receita> listaOrdenada = em.createNativeQuery(query, Receita.class)
				.getResultList();
		return listaOrdenada;
	}

	// 2.2 Ensure ResultList is Multiple of Four to Enable Navigation
	public List<Receita> ensureLengthMultipleOfFour(List<Receita> inputList) {
		while (inputList.size() % 4 != 0) {
			inputList.add(receitaPlaceholder);
		}
		return inputList;
	}

	// 3. NAVIGATE LEFT
	public void renderLeft() {
		if (startGridIndex > 3) {
			startGridIndex -= 4;
			paginator.setPageIndex(paginator.getPageIndex() - 1);
		}
	}

	// 4. NAVIGATE RIGHT
	public void renderRight() {
		if (receitaResult.size() > startGridIndex + 4) {
			startGridIndex += 4;
			paginator.setPageIndex(paginator.getPageIndex() + 1);
		}
	}

	// 5. GET RECEITA ID FROM CLICKABLE IMAGE
	public String showUniqueReceita(int index) {
		uniqueReceita = receitaResult.get(index);
		return "receita-detalhe";
	}

	// 6. GET RECEITA_INGREDIENTE LIST FROM RECEITA ID
	@SuppressWarnings("unchecked")
	public List<Receita_Ingrediente> getReceitaIngredienteFromReceitaID(
			Long ID) {
		return em.createQuery(
				"SELECT e FROM Receita_Ingrediente e WHERE receita_id=" + ID)
				.getResultList();
	}

	// 7. GET RECEITA RESULT NOT EMPTY NUMBER
	public void showReceitaResultCounter() {
		int receitasNotEmpty = 0;
		for (Receita receita : receitaResult) {
			if (receita.getId() != 1) {
				receitasNotEmpty += 1;
			}
		}
		receitaResultNumb = receitasNotEmpty;
		receitaResultCounterOutput = receitasNotEmpty + " Receitas Encontradas";
	}

	public void updateGridIndexFromPageIndex(int pageIndex) {
		startGridIndex = 4 * pageIndex - 3;
	}

	/*-----------------------------------------------------------------------------------*/
	/* GETTERS AND SETTERS */
	/*-----------------------------------------------------------------------------------*/

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

	public void setUniqueReceitaIngredienteList(
			List<Receita_Ingrediente> uniqueReceitaIngredienteList) {
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

	public int getReceitaResultNumb() {
		return receitaResultNumb;
	}

	public void setReceitaResultNumb(int receitaResultNumb) {
		this.receitaResultNumb = receitaResultNumb;
	}

	public String getReceitaResultCounterOutput() {
		return receitaResultCounterOutput;
	}

	public void setReceitaResultCounterOutput(
			String receitaResultCounterOutput) {
		this.receitaResultCounterOutput = receitaResultCounterOutput;
	}

	public Paginator getPaginator() {
		return paginator;
	}

}
