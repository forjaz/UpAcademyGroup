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
	
	// All fields below belong to the returnReceitasOrderByHit() Method
	private boolean orderByCalorias=false;
	private int caloriasPesquisa;
	private boolean greaterThanC=false;
	private boolean segundoCriterioOrdenacaoC=false;
	
	private boolean orderByProteinas=false;
	private double proteinaPesquisa;
	private boolean greaterThanP=false;
	private boolean segundoCriterioOrdenacaoP=false;
	
	private boolean orderByHidratos=false;
	private double hidratosPesquisa;
	private boolean greaterThanH=false;
	private boolean segundoCriterioOrdenacaoH=false;
	
	private boolean orderByGorduras=false;
	private double gordurasPesquisa;
	private boolean greaterThanG=false;
	private boolean segundoCriterioOrdenacaoG=false;
	
	private boolean segundoCriterioOrdenacao=false;

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
		
		String segundoCriterio="";
		
		if(orderByCalorias==true && greaterThanC==false && segundoCriterioOrdenacaoC==true && segundoCriterioOrdenacao==true){
			var += "&& calorias <=" + caloriasPesquisa + " ";
			segundoCriterio += ", calorias DESC";
		}else if(orderByCalorias==true && greaterThanC==false && segundoCriterioOrdenacaoC==true && segundoCriterioOrdenacao==false){
			var += "&& calorias <=" + caloriasPesquisa + " ";
			segundoCriterio += ", calorias";
		}else if(orderByCalorias==true && greaterThanC==true && segundoCriterioOrdenacaoC==true && segundoCriterioOrdenacao==true){
			var += "&& calorias >=" + caloriasPesquisa + " ";
			segundoCriterio += ", calorias DESC";
		}else if(orderByCalorias==true && greaterThanC==true && segundoCriterioOrdenacaoC==true && segundoCriterioOrdenacao==false){
			var += "&& calorias >=" + caloriasPesquisa + " ";
			segundoCriterio += ", calorias";
		}else if(orderByCalorias==true && greaterThanC==false && segundoCriterioOrdenacaoC==false){
			var += "&& calorias <=" + caloriasPesquisa + " ";
		}else if(orderByCalorias==true && greaterThanC==false && segundoCriterioOrdenacaoC==false){
			var += "&& calorias <=" + caloriasPesquisa + " ";
		}else if(orderByCalorias==true && greaterThanC==true && segundoCriterioOrdenacaoC==false){
			var += "&& calorias >=" + caloriasPesquisa + " ";
		}else if(orderByCalorias==true && greaterThanC==true && segundoCriterioOrdenacaoC==false){
			var += "&& calorias >=" + caloriasPesquisa + " ";
		}
		
		if(orderByProteinas==true && greaterThanP==false && segundoCriterioOrdenacaoP==true && segundoCriterioOrdenacao==true){
			var += "&& proteina <=" + proteinaPesquisa + " ";
			segundoCriterio += ", proteina DESC";
		}else if(orderByProteinas==true && greaterThanP==false && segundoCriterioOrdenacaoP==true && segundoCriterioOrdenacao==false){
			var += "&& proteina <=" + proteinaPesquisa + " ";
			segundoCriterio += ", proteina";
		}else if(orderByProteinas==true && greaterThanP==true && segundoCriterioOrdenacaoP==true && segundoCriterioOrdenacao==true){
			var += "&& proteina >=" + proteinaPesquisa + " ";
			segundoCriterio += ", proteina DESC";
		}else if(orderByProteinas==true && greaterThanP==true && segundoCriterioOrdenacaoP==true && segundoCriterioOrdenacao==false){
			var += "&& proteina >=" + proteinaPesquisa + " ";
			segundoCriterio += ", proteina";
		}else if(orderByProteinas==true && greaterThanP==false && segundoCriterioOrdenacaoP==false){
			var += "&& proteina <=" + proteinaPesquisa + " ";
		}else if(orderByProteinas==true && greaterThanP==false && segundoCriterioOrdenacaoP==false){
			var += "&& proteina <=" + proteinaPesquisa + " ";
		}else if(orderByProteinas==true && greaterThanP==true && segundoCriterioOrdenacaoP==false){
			var += "&& proteina >=" + proteinaPesquisa + " ";
		}else if(orderByProteinas==true && greaterThanP==true && segundoCriterioOrdenacaoP==false){
			var += "&& proteina >=" + proteinaPesquisa + " ";
		}
		
		if(orderByHidratos==true && greaterThanH==false && segundoCriterioOrdenacaoH==true && segundoCriterioOrdenacao==true){
			var += "&& hidratos <=" + hidratosPesquisa + " ";
			segundoCriterio += ", hidratos DESC";
		}else if(orderByHidratos==true && greaterThanH==false && segundoCriterioOrdenacaoH==true && segundoCriterioOrdenacao==false){
			var += "&& hidratos <=" + hidratosPesquisa + " ";
			segundoCriterio += ", hidratos";
		}else if(orderByHidratos==true && greaterThanH==true && segundoCriterioOrdenacaoH==true && segundoCriterioOrdenacao==true){
			var += "&& hidratos >=" + hidratosPesquisa + " ";
			segundoCriterio += ", hidratos DESC";
		}else if(orderByHidratos==true && greaterThanH==true && segundoCriterioOrdenacaoH==true && segundoCriterioOrdenacao==false){
			var += "&& hidratos >=" + hidratosPesquisa + " ";
			segundoCriterio += ", hidratos";
		}else if(orderByHidratos==true && greaterThanH==false && segundoCriterioOrdenacaoH==false){
			var += "&& hidratos <=" + hidratosPesquisa + " ";
		}else if(orderByHidratos==true && greaterThanH==false && segundoCriterioOrdenacaoH==false){
			var += "&& hidratos <=" + hidratosPesquisa + " ";
		}else if(orderByHidratos==true && greaterThanH==true && segundoCriterioOrdenacaoH==false){
			var += "&& hidratos >=" + hidratosPesquisa + " ";
		}else if(orderByHidratos==true && greaterThanH==true && segundoCriterioOrdenacaoH==false){
			var += "&& hidratos >=" + hidratosPesquisa + " ";
		}
		
		if(orderByGorduras==true && greaterThanG==false && segundoCriterioOrdenacaoG==true && segundoCriterioOrdenacao==true){
			var += "&& gorduras <=" + gordurasPesquisa + " ";
			segundoCriterio += ", gorduras DESC";
		}else if(orderByGorduras==true && greaterThanG==false && segundoCriterioOrdenacaoG==true && segundoCriterioOrdenacao==false){
			var += "&& gorduras <=" + gordurasPesquisa + " ";
			segundoCriterio += ", gorduras";
		}else if(orderByGorduras==true && greaterThanG==true && segundoCriterioOrdenacaoG==true && segundoCriterioOrdenacao==true){
			var += "&& gorduras >=" + gordurasPesquisa + " ";
			segundoCriterio += ", gorduras DESC";
		}else if(orderByGorduras==true && greaterThanG==true && segundoCriterioOrdenacaoG==true && segundoCriterioOrdenacao==false){
			var += "&& gorduras >=" + gordurasPesquisa + " ";
			segundoCriterio += ", gorduras";
		}else if(orderByGorduras==true && greaterThanG==false && segundoCriterioOrdenacaoG==false){
			var += "&& gorduras <=" + gordurasPesquisa + " ";
		}else if(orderByGorduras==true && greaterThanG==false && segundoCriterioOrdenacaoG==false){
			var += "&& gorduras <=" + gordurasPesquisa + " ";
		}else if(orderByGorduras==true && greaterThanG==true && segundoCriterioOrdenacaoG==false){
			var += "&& gorduras >=" + gordurasPesquisa + " ";
		}else if(orderByGorduras==true && greaterThanG==true && segundoCriterioOrdenacaoG==false){
			var += "&& gorduras >=" + gordurasPesquisa + " ";
		}
		
		String query = "SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN "
				+ var
				+ ") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id "
				+ "WHERE RI.ingrediente_id IN " + var
				+ " GROUP BY receita_id ORDER BY Relevancia DESC"+segundoCriterio+"";

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
	
	public boolean isOrderByCalorias() {
		return orderByCalorias;
	}

	public void setOrderByCalorias(boolean orderByCalorias) {
		this.orderByCalorias = orderByCalorias;
	}

	public int getCaloriasPesquisa() {
		return caloriasPesquisa;
	}

	public void setCaloriasPesquisa(int caloriasPesquisa) {
		this.caloriasPesquisa = caloriasPesquisa;
	}

	public boolean isGreaterThanC() {
		return greaterThanC;
	}

	public void setGreaterThanC(boolean greaterThanC) {
		this.greaterThanC = greaterThanC;
	}

	public boolean isOrderByProteinas() {
		return orderByProteinas;
	}

	public void setOrderByProteinas(boolean orderByProteinas) {
		this.orderByProteinas = orderByProteinas;
	}

	public double getProteinaPesquisa() {
		return proteinaPesquisa;
	}

	public void setProteinaPesquisa(double proteinaPesquisa) {
		this.proteinaPesquisa = proteinaPesquisa;
	}

	public boolean isGreaterThanP() {
		return greaterThanP;
	}

	public void setGreaterThanP(boolean greaterThanP) {
		this.greaterThanP = greaterThanP;
	}

	public boolean isOrderByHidratos() {
		return orderByHidratos;
	}

	public void setOrderByHidratos(boolean orderByHidratos) {
		this.orderByHidratos = orderByHidratos;
	}

	public double getHidratosPesquisa() {
		return hidratosPesquisa;
	}

	public void setHidratosPesquisa(double hidratosPesquisa) {
		this.hidratosPesquisa = hidratosPesquisa;
	}

	public boolean isGreaterThanH() {
		return greaterThanH;
	}

	public void setGreaterThanH(boolean greaterThanH) {
		this.greaterThanH = greaterThanH;
	}

	public boolean isOrderByGorduras() {
		return orderByGorduras;
	}

	public void setOrderByGorduras(boolean orderByGorduras) {
		this.orderByGorduras = orderByGorduras;
	}

	public double getGordurasPesquisa() {
		return gordurasPesquisa;
	}

	public void setGordurasPesquisa(double gordurasPesquisa) {
		this.gordurasPesquisa = gordurasPesquisa;
	}

	public boolean isGreaterThanG() {
		return greaterThanG;
	}

	public void setGreaterThanG(boolean greaterThanG) {
		this.greaterThanG = greaterThanG;
	}

	public boolean isSegundoCriterioOrdenacaoC() {
		return segundoCriterioOrdenacaoC;
	}

	public void setSegundoCriterioOrdenacaoC(boolean segundoCriterioOrdenacaoC) {
		this.segundoCriterioOrdenacaoC = segundoCriterioOrdenacaoC;
	}

	public boolean isSegundoCriterioOrdenacaoP() {
		return segundoCriterioOrdenacaoP;
	}

	public void setSegundoCriterioOrdenacaoP(boolean segundoCriterioOrdenacaoP) {
		this.segundoCriterioOrdenacaoP = segundoCriterioOrdenacaoP;
	}

	public boolean isSegundoCriterioOrdenacaoH() {
		return segundoCriterioOrdenacaoH;
	}

	public void setSegundoCriterioOrdenacaoH(boolean segundoCriterioOrdenacaoH) {
		this.segundoCriterioOrdenacaoH = segundoCriterioOrdenacaoH;
	}

	public boolean isSegundoCriterioOrdenacaoG() {
		return segundoCriterioOrdenacaoG;
	}

	public void setSegundoCriterioOrdenacaoG(boolean segundoCriterioOrdenacaoG) {
		this.segundoCriterioOrdenacaoG = segundoCriterioOrdenacaoG;
	}

	public boolean isSegundoCriterioOrdenacao() {
		return segundoCriterioOrdenacao;
	}

	public void setSegundoCriterioOrdenacao(boolean segundoCriterioOrdenacao) {
		this.segundoCriterioOrdenacao = segundoCriterioOrdenacao;
	}


	public Paginator getPaginator() {
		return paginator;
	}

}
