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
@Named("global")
@SessionScoped
public class Global extends EntityService implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Long> searchQuery;
	private List<Long> receitaIDList;
	private List<Long> uniqueReceitaIDList;
	private List<Long> renderedResult = new ArrayList<>();
	private Long uniqueReceitaID = 1L;
	private Receita uniqueReceita;
	private List<Receita_Ingrediente> uniqueReceitaIngredienteList;
	private List<String> uniqueIngredienteNomeList;
	private String displayIngrediente;
	private int startGridIndex = 0;

	// Constructor
	public Global() {
	}

	@PostConstruct
	public void renderResultInit() {
		renderedResult.add(1L);
		renderedResult.add(1L);
		renderedResult.add(1L);
		renderedResult.add(1L);
	}

	// Methods
	public List<Long> returnReceitasOrderByHit(){
		List<Long> lista2 = searchQuery;
		String var="(";
		for(int i = 0; i <= lista2.size() - 1; i++){
			if(i<lista2.size()-1){
				var = var + lista2.get(i)+",";
			}else{
				var = var + lista2.get(i)+")";
			}
		}
		String query = "SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN "
						+var+") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id "
						+"WHERE RI.ingrediente_id IN "+var+" GROUP BY receita_id ORDER BY Relevancia DESC";
		
		List<Long> listaOrdenada = em.createNativeQuery(query).getResultList();
		return listaOrdenada;
	}
	
	public List returnIngredientes() {
		List lista = em.createQuery("SELECT e FROM Ingrediente e").getResultList();
		return lista;
	}

	// 1 SEARCH INGREDIENTES IN SEARCH BAR
	public void searchIngredientes() {
		receitaIDList = convertIngredienteIDToReceitaID(searchQuery);
		uniqueReceitaIDList = removeReceitaIDDuplicates(receitaIDList);
		renderedResult = ensureLengthMultipleOfFour(uniqueReceitaIDList);
		returnReceitasOrderByHit();
	}

	// 1.1 CONVERT INGREDIENTE ID --> RECEITA ID
	public List<Long> convertIngredienteIDToReceitaID(List<Long> inputList) {
		String query = "SELECT e.receita.id FROM Receita_Ingrediente e WHERE ";
		int inputSize = inputList.size();
		List<Long> placeholder = new ArrayList<>();
		placeholder.add(1L);
		if (inputList.isEmpty()) {
			return placeholder;
		} else {
			for (int i = 0; i < inputSize; i++) {
				if (i == inputSize - 1) {
					query = query + "ingrediente_id=" + inputList.get(i);
				} else {
					query = query + "ingrediente_id=" + inputList.get(i) + " or ";
				}
			}
			return em.createQuery(query).getResultList();
		}
	}

	// 1.2 REMOVE DUPLICATE IDs FROM RECEITA ID
    public List<Long> removeReceitaIDDuplicates(List<Long> inputList) {
    	long contador = 1;
    	List<Long> contadores = new ArrayList<>();
        List<Long> resultList = new ArrayList<>();
        int inputSize = inputList.size();
        for (int i = 1; i <= inputSize; i++) {
        	if(i==1){
    			contadores.add(contador);	
    		}
        	if(i < inputSize && inputList.get(i-1)!=inputList.get(i)){
        		contadores.add(contador);
        	}
        	
            if (!resultList.contains(inputList.get(i-1))) {
                resultList.add(inputList.get(i-1));
            }
        }
        
        int i = 1;
    	for(int j = 1; j <= contadores.size() ; j++){
        	while(i < inputSize && inputList.get(i-1)==inputList.get(i)){
        		contadores.set(j-1, ++contador);
        		i++;
        		if(i < inputSize && inputList.get(i-1)!=inputList.get(i)){
            		contador=1;
                    i++;
            		break;
        		}
        		
        	}
    	
    	}
    	System.out.println(contadores);
    	return resultList;
    }


	// 1.3 ENSURE MULTIPLES OF FOUR IN RENDERED LIST FOR NAVIGATION
	public List<Long> ensureLengthMultipleOfFour(List<Long> inputList) {
		while (inputList.size() % 4 != 0) {
			inputList.add(1L);
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
		if (renderedResult.size() > startGridIndex + 4) {
			startGridIndex += 4;
		}
	}

	// 2 GET RECEITA ID FROM CLICKABLE IMAGE
	public String showUniqueReceita(int index) {
		findReceitaID(index);
		uniqueReceita = getReceitaFromID(uniqueReceitaID);
		return "receita";
	}

	// 2.1 GET RECEITA IN RENDERED LIST FROM IMAGE INDEX
	public void findReceitaID(int index) {
		uniqueReceitaID = renderedResult.get(index);
	}

	// 2.2 GET RECEITA FRM RECEITA ID
	public Receita getReceitaFromID(Long ID) {
		return (Receita) em.createQuery("SELECT e FROM Receita e WHERE id=" + ID).getResultList().get(0);
	}

	// 3 GETS INGREDIETES FROM RECEITA ID AND RETURNS A COLUMN OF THEIR NAMES
	public String showIngredientesColumnFromReceitaID(Long ID) {
		uniqueReceitaIngredienteList = getReceitaIngredienteFromReceitaID(ID);
		uniqueIngredienteNomeList = getIngredienteNomeFromReceitaIngredienteID(uniqueReceitaIngredienteList);
		displayIngrediente = showIngredienteNomeList(uniqueIngredienteNomeList);
		return displayIngrediente;
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

	public List<Long> getReceitaIDList() {
		return receitaIDList;
	}

	public void setReceitaIDList(List<Long> receitaIDList) {
		this.receitaIDList = receitaIDList;
	}

	public List<Long> getUniqueReceitaIDList() {
		return uniqueReceitaIDList;
	}

	public void setUniqueReceitaIDList(List<Long> uniqueReceitaIDList) {
		this.uniqueReceitaIDList = uniqueReceitaIDList;
	}

	public List<Long> getRenderedResult() {
		return renderedResult;
	}

	public void setRenderedResult(List<Long> renderedResult) {
		this.renderedResult = renderedResult;
	}

	public Long getUniqueReceitaID() {
		return uniqueReceitaID;
	}

	public void setUniqueReceitaID(Long uniqueReceitaID) {
		this.uniqueReceitaID = uniqueReceitaID;
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

	public List<String> getUniqueIngredienteNomeList() {
		return uniqueIngredienteNomeList;
	}

	public void setUniqueIngredienteNomeList(List<String> uniqueIngredienteNomeList) {
		this.uniqueIngredienteNomeList = uniqueIngredienteNomeList;
	}

	public String getDisplayIngrediente() {
		return displayIngrediente;
	}

	public void setDisplayIngrediente(String displayIngrediente) {
		this.displayIngrediente = displayIngrediente;
	}

	public int getStartGridIndex() {
		return startGridIndex;
	}

	public void setStartGridIndex(int startGridIndex) {
		this.startGridIndex = startGridIndex;
	}
}
