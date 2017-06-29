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
import java.util.Arrays;
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
    //Ingredientes -> Ingrediente nome
    private ArrayList<String> searchName;
    // Receitas to Render in Grid
    private List<Receita> receitaResult = new ArrayList<>();
    // Receita to Render in Unique
    private Receita uniqueReceita;
    // ReceitaResult Index that Renders 1st Image in Grid
    private int startGridIndex = 0;
    // Show Receita Result Text
    private String receitaResultCounterOutput;
    // Show Page Counter
    private boolean renderPageCounter;
    // RelvanciaSort
    private boolean sortByRelevancia = false;
    // Paginator
    private Paginator paginator;
    
    ArrayList<String> prep= new ArrayList<String>();

    /*-----------------------------------------------------------------------------------*/
    /* CONSTRUCTORS */
    /*-----------------------------------------------------------------------------------*/
    public Global() {
    }

    // Fills Grid With Empty Placeholder at Start
    @PostConstruct
    @Transactional
    public void renderResultInit() {
        receitaPlaceholder = (Receita) em.createQuery("SELECT e FROM Receita e WHERE id=1").getResultList().get(0);
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
    	ArrayList<Ingrediente> listaFinal = new ArrayList<Ingrediente>();
    	ArrayList<Ingrediente> listaNome = (ArrayList<Ingrediente>) em.createQuery("SELECT e FROM Ingrediente e ORDER BY e.nome").getResultList();
    	listaFinal.add(listaNome.get(0));
    	for(int i = 0; i<listaNome.size()-1; i++){
    		if(listaNome.get(i).getNome()!=listaNome.get(i+1).getNome()){
    			listaFinal.add(listaNome.get(i+1));
    		}
    	}
    	return listaFinal;
    }

    // 2 SEARCH INGREDIENTES IN SEARCH BAR
    public void searchIngredientes() {
    	nametoId();
        List<Receita> receitaList = sortByRelevancia();
        receitaResult = ensureLengthMultipleOfFour(receitaList);
        showReceitaResultCounter();
        setRenderPageCounter(true);
    }

    public void searchIngredientesByParameter(String parameter) {
    	nametoId();
        List<Receita> receitaList = sortByParameter(parameter);
        receitaResult = ensureLengthMultipleOfFour(receitaList);
        showReceitaResultCounter();
        setRenderPageCounter(true);
    }

    // GET RECEITAS NOT APPROVED
    public void searchReceitasNaoAprovadas() {
        List<Receita> receitaList = sortByNaoAprovadas();
        receitaResult = ensureLengthMultipleOfFour(receitaList);
        showReceitaResultCounter();
        setRenderPageCounter(true);
    }

    // 2.1 Returns Receitas by Number of Ingredientes
    public List sortByRelevancia() {
        List<Long> lista = searchQuery;
        String sortByRelevanciaQuery = "";
        String var = "(";
        StringBuilder query = new StringBuilder();
        List<Receita> searchResult = new ArrayList<>();
        if (searchQuery.isEmpty()) {
            searchResult.add(receitaPlaceholder);
            return searchResult;
        } else {
            for (int i = 0; i <= lista.size() - 1; i++) {
                if (i < lista.size() - 1) {
                    var += lista.get(i) + ",";
                } else {
                    var += lista.get(i) + ") && validacao = 'aprovada' ";
                }
            }
            if (sortByRelevancia) {
                sortByRelevanciaQuery = "ORDER BY Relevancia DESC";
            }
            query = query.append("SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN ").append(var).append(") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id ").append("WHERE RI.ingrediente_id IN ").append(var).append(" GROUP BY receita_id ").append(sortByRelevanciaQuery);
        }
        return em.createNativeQuery(query.toString(), Receita.class).getResultList();
    }

    // Sort Query
    public List sortByParameter(String parameter) {
        List<Long> lista = searchQuery;
        String sortByRelevanciaGordurasQuery = "";
        String var = "(";
        StringBuilder query = new StringBuilder();
        List<Receita> searchResult = new ArrayList<>();
        if (searchQuery.isEmpty()) {
            searchResult.add(receitaPlaceholder);
            return searchResult;
        } else {
            for (int i = 0; i <= lista.size() - 1; i++) {
                if (i < lista.size() - 1) {
                    var += lista.get(i) + ",";
                } else {
                    var += lista.get(i) + ") && validacao = 'aprovada' ";
                }
            }
            if (sortByRelevancia) {
                sortByRelevanciaGordurasQuery = "ORDER BY Relevancia DESC, " + parameter;
            } else {
                sortByRelevanciaGordurasQuery = "ORDER BY " + parameter;
            }
            query = query.append("SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN ").append(var).append(") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id && validacao = 'aprovada' ").append("WHERE RI.ingrediente_id IN ").append(var).append(" GROUP BY receita_id ").append(sortByRelevanciaGordurasQuery);
        }
        return em.createNativeQuery(query.toString(), Receita.class).getResultList();
    }

    public List sortByNaoAprovadas() {
        List<Receita> receitasNaoAprovadas = em.createNativeQuery("SELECT * FROM receita WHERE receita.validacao='reprovada' ORDER BY receita.id", Receita.class).getResultList();
        if (receitasNaoAprovadas.isEmpty()) {
            receitasNaoAprovadas.add(receitaPlaceholder);
        }
        return receitasNaoAprovadas;
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

    // 5. GET RECEITA ID FROM CLICKABLE IMAGE
    public String showUniqueReceitaNaoAprovada(int index) {
        uniqueReceita = receitaResult.get(index);
        return "aprovacao-detalhe";
    }

    // 6. GET RECEITA_INGREDIENTE LIST FROM RECEITA ID
    @SuppressWarnings("unchecked")
    public List<Receita_Ingrediente> getReceitaIngredienteFromReceitaID(Long ID) {
        return em.createQuery("SELECT e FROM Receita_Ingrediente e WHERE receita_id=" + ID).getResultList();
    }

    // 7. GET RECEITA RESULT NOT EMPTY NUMBER
    public void showReceitaResultCounter() {
        int receitasNotEmpty = 0;
        for (Receita receita : receitaResult) {
            if (receita.getId() != 1) {
                receitasNotEmpty += 1;
            }
        }
        this.paginator = new Paginator(this.receitaResult);
        receitaResultCounterOutput = receitasNotEmpty + " Receitas Encontradas";
    }

    // APPROVE RECEITA
    public String approveReceita(Receita receita) {
        receita.setValidacao("aprovada");
        return "aprovacao";
    }

    // APPROVE RECEITA
    public String rejectReceita(Receita receita) {
        List<Receita_Ingrediente> receitaIngredienteToDelete;
        receitaIngredienteToDelete = em.createNativeQuery("SELECT * FROM receita_ingrediente RI WHERE RI.receita_id=" + receita.getId(), Receita_Ingrediente.class).getResultList();
        for (Receita_Ingrediente receitaIngrediente : receitaIngredienteToDelete) {
            em.remove(receitaIngrediente);
        }
        em.remove(receita);
        return "aprovacao";
    }

    // RESET RECEITAS
    public void resetReceitaGrid(){
        receitaResult = new ArrayList<>();
        receitaResult.addAll(Arrays.asList(receitaPlaceholder, receitaPlaceholder, receitaPlaceholder, receitaPlaceholder));
        setRenderPageCounter(false);
        startGridIndex = 0;
    }

	/*-----------------------------------------------------------------------------------*/
    /* GETTERS AND SETTERS */
    /*-----------------------------------------------------------------------------------*/

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Receita getReceitaPlaceholder() {
        return receitaPlaceholder;
    }

    public void setReceitaPlaceholder(Receita receitaPlaceholder) {
        this.receitaPlaceholder = receitaPlaceholder;
    }

    public List<Long> getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(List<Long> searchQuery) {
        this.searchQuery = searchQuery;
    }

    public List<Receita> getReceitaResult() {
        return receitaResult;
    }

    public void setReceitaResult(List<Receita> receitaResult) {
        this.receitaResult = receitaResult;
    }

    public Receita getUniqueReceita() {
        return uniqueReceita;
    }

    public void setUniqueReceita(Receita uniqueReceita) {
        this.uniqueReceita = uniqueReceita;
    }

    public int getStartGridIndex() {
        return startGridIndex;
    }

    public void setStartGridIndex(int startGridIndex) {
        this.startGridIndex = startGridIndex;
    }

    public String getReceitaResultCounterOutput() {
        return receitaResultCounterOutput;
    }

    public void setReceitaResultCounterOutput(String receitaResultCounterOutput) {
        this.receitaResultCounterOutput = receitaResultCounterOutput;
    }

    public boolean isRenderPageCounter() {
        return renderPageCounter;
    }

    public void setRenderPageCounter(boolean renderPageCounter) {
        this.renderPageCounter = renderPageCounter;
    }

    public boolean isSortByRelevancia() {
        return sortByRelevancia;
    }

    public void setSortByRelevancia(boolean sortByRelevancia) {
        this.sortByRelevancia = sortByRelevancia;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
    public ArrayList<String> getPrep() {
		return prep;
	}

	public void setPrep(ArrayList<String> prep) {
		this.prep = prep;
	}
	
    public void listarPrep(String nome) {
		@SuppressWarnings("unchecked")
        ArrayList<Ingrediente> lista= (ArrayList<Ingrediente>) em.createNativeQuery("SELECT * FROM Ingrediente I "+
				"WHERE I.nome='"+nome+"'", Ingrediente.class).getResultList();
        
        ArrayList<String> mprep= new ArrayList<String>();
        
        for(int i = 0; i<lista.size(); i++){
        	mprep.add(lista.get(i).getModoPreparacao());
        }
        
        prep = mprep;
	}

	public ArrayList<String> getSearchName() {
		return searchName;
	}

	public void setSearchName(ArrayList<String> searchName) {
		this.searchName = searchName;
	}
	
	public void nametoId() {
    	StringBuilder query = new StringBuilder();
    	query = query.append("SELECT * FROM Ingrediente I "+
				"WHERE I.nome='"+searchName.get(0)+"'");
		for(int i = 1; i<searchName.size(); i++){

			query.append(" Or I.nome='"+searchName.get(i)+"'");
		}
		
		
		List<Long> lista2= new ArrayList<Long>();
		
		@SuppressWarnings("unchecked")
		
		ArrayList<Ingrediente> lista1= (ArrayList<Ingrediente>) em.createNativeQuery(query.toString(), Ingrediente.class).getResultList();
		
		for(int j = 0; j < lista1.size(); j++){
			lista2.add(lista1.get(j).getId());
		}
		searchQuery=lista2;

			
	}

}
