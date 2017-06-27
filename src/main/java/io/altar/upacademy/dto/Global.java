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
    // ReceitaResult Index that Renders 1st Image in Grid
    private int startGridIndex = 0;
    // Show Receita Result Text
    private String receitaResultCounterOutput;
    // Show Page Counter
    private boolean renderPageCounter;
    // RelvanciaSort
    private boolean sortByRelevancia = true;
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
        return em.createQuery("SELECT e FROM Ingrediente e ORDER BY e.nome").getResultList();
    }

    // 2 SEARCH INGREDIENTES IN SEARCH BAR
    public void searchIngredientes() {
        List<Receita> receitaList = sortByRelevancia();
        receitaResult = ensureLengthMultipleOfFour(receitaList);
        showReceitaResultCounter();
        setRenderPageCounter(true);
    }

    public void searchIngredientesByCaloriasAsc() {
        List<Receita> receitaList = sortByCaloriasAsc();
        receitaResult = ensureLengthMultipleOfFour(receitaList);
        showReceitaResultCounter();
        setRenderPageCounter(true);
    }

    public void searchIngredientesByCaloriasDesc() {
        List<Receita> receitaList = sortByCaloriasDesc();
        receitaResult = ensureLengthMultipleOfFour(receitaList);
        showReceitaResultCounter();
        setRenderPageCounter(true);
    }

    public void searchIngredientesByProteinasAsc() {
        List<Receita> receitaList = sortByProteinasAsc();
        receitaResult = ensureLengthMultipleOfFour(receitaList);
        showReceitaResultCounter();
        setRenderPageCounter(true);
    }

    public void searchIngredientesByProteinasDesc() {
        List<Receita> receitaList = sortByProteinasDesc();
        receitaResult = ensureLengthMultipleOfFour(receitaList);
        showReceitaResultCounter();
        setRenderPageCounter(true);
    }

    public void searchIngredientesByHidratosAsc() {
        List<Receita> receitaList = sortByHidratosAsc();
        receitaResult = ensureLengthMultipleOfFour(receitaList);
        showReceitaResultCounter();
        setRenderPageCounter(true);
    }

    public void searchIngredientesByHidratosDesc() {
        List<Receita> receitaList = sortByHidratosDesc();
        receitaResult = ensureLengthMultipleOfFour(receitaList);
        showReceitaResultCounter();
        setRenderPageCounter(true);
    }

    public void searchIngredientesByGordurasAsc() {
        List<Receita> receitaList = sortByGordurasAsc();
        receitaResult = ensureLengthMultipleOfFour(receitaList);
        showReceitaResultCounter();
        setRenderPageCounter(true);
    }

    public void searchIngredientesByGordurasDesc() {
        List<Receita> receitaList = sortByGordurasDesc();
        receitaResult = ensureLengthMultipleOfFour(receitaList);
        showReceitaResultCounter();
        setRenderPageCounter(true);
    }

    // 2.1 Returns Receitas by Number of Ingredientes
    public List sortByRelevancia() {
        List<Long> lista = searchQuery;
        String sortByRelevanciaQuery = "";
        String var = "(";
        for (int i = 0; i <= lista.size() - 1; i++) {
            if (i < lista.size() - 1) {
                var += lista.get(i) + ",";
            } else {
                var += lista.get(i) + ")";
            }
        }
        if (sortByRelevancia) {
            sortByRelevanciaQuery = "ORDER BY Relevancia DESC";
        }
        String query = new StringBuilder().append("SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN ").append(var).append(") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id ").append("WHERE RI.ingrediente_id IN ").append(var).append(" GROUP BY receita_id ").append(sortByRelevanciaQuery).toString();
        return em.createNativeQuery(query, Receita.class).getResultList();
    }

    // 2.1.1 Sorts by Calorias
    public List sortByCaloriasAsc() {
        List<Long> lista = searchQuery;
        String sortByRelevanciaCaloriasQuery = "";
        String var = "(";
        for (int i = 0; i <= lista.size() - 1; i++) {
            if (i < lista.size() - 1) {
                var += lista.get(i) + ",";
            } else {
                var += lista.get(i) + ")";
            }
        }
        if (sortByRelevancia) {
            sortByRelevanciaCaloriasQuery = "ORDER BY Relevancia DESC, calorias ASC";
        } else {
            sortByRelevanciaCaloriasQuery = "ORDER BY calorias ASC";
        }
        String query = new StringBuilder().append("SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN ").append(var).append(") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id ").append("WHERE RI.ingrediente_id IN ").append(var).append(" GROUP BY receita_id ").append(sortByRelevanciaCaloriasQuery).toString();
        return em.createNativeQuery(query, Receita.class).getResultList();
    }

    public List sortByCaloriasDesc() {
        List<Long> lista = searchQuery;
        String sortByRelevanciaCaloriasQuery = "";
        String var = "(";
        for (int i = 0; i <= lista.size() - 1; i++) {
            if (i < lista.size() - 1) {
                var += lista.get(i) + ",";
            } else {
                var += lista.get(i) + ")";
            }
        }
        if (sortByRelevancia) {
            sortByRelevanciaCaloriasQuery = "ORDER BY Relevancia DESC, calorias DESC";
        } else {
            sortByRelevanciaCaloriasQuery = "ORDER BY calorias DESC";
        }
        String query = new StringBuilder().append("SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN ").append(var).append(") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id ").append("WHERE RI.ingrediente_id IN ").append(var).append(" GROUP BY receita_id ").append(sortByRelevanciaCaloriasQuery).toString();
        return em.createNativeQuery(query, Receita.class).getResultList();
    }

    // 2.1.2 Sorts by Proteinas
    public List sortByProteinasAsc() {
        List<Long> lista = searchQuery;
        String sortByRelevanciaProteinasQuery = "";
        String var = "(";
        for (int i = 0; i <= lista.size() - 1; i++) {
            if (i < lista.size() - 1) {
                var += lista.get(i) + ",";
            } else {
                var += lista.get(i) + ")";
            }
        }
        if (sortByRelevancia) {
            sortByRelevanciaProteinasQuery = "ORDER BY Relevancia DESC, proteina ASC";
        } else {
            sortByRelevanciaProteinasQuery = "ORDER BY proteina ASC";
        }
        String query = new StringBuilder().append("SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN ").append(var).append(") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id ").append("WHERE RI.ingrediente_id IN ").append(var).append(" GROUP BY receita_id ").append(sortByRelevanciaProteinasQuery).toString();
        return em.createNativeQuery(query, Receita.class).getResultList();
    }

    public List sortByProteinasDesc() {
        List<Long> lista = searchQuery;
        String sortByRelevanciaProteinasQuery = "";
        String var = "(";
        for (int i = 0; i <= lista.size() - 1; i++) {
            if (i < lista.size() - 1) {
                var += lista.get(i) + ",";
            } else {
                var += lista.get(i) + ")";
            }
        }
        if (sortByRelevancia) {
            sortByRelevanciaProteinasQuery = "ORDER BY Relevancia DESC, proteina DESC";
        } else {
            sortByRelevanciaProteinasQuery = "ORDER BY proteina DESC";
        }
        String query = new StringBuilder().append("SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN ").append(var).append(") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id ").append("WHERE RI.ingrediente_id IN ").append(var).append(" GROUP BY receita_id ").append(sortByRelevanciaProteinasQuery).toString();
        return em.createNativeQuery(query, Receita.class).getResultList();
    }

    // 2.1.3 Sorts by Hidratos
    public List sortByHidratosAsc() {
        List<Long> lista = searchQuery;
        String sortByRelevanciaHidratosQuery = "";
        String var = "(";
        for (int i = 0; i <= lista.size() - 1; i++) {
            if (i < lista.size() - 1) {
                var += lista.get(i) + ",";
            } else {
                var += lista.get(i) + ")";
            }
        }
        if (sortByRelevancia) {
            sortByRelevanciaHidratosQuery = "ORDER BY Relevancia DESC, hidratos ASC";
        } else {
            sortByRelevanciaHidratosQuery = "ORDER BY hidratos ASC";
        }
        String query = new StringBuilder().append("SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN ").append(var).append(") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id ").append("WHERE RI.ingrediente_id IN ").append(var).append(" GROUP BY receita_id ").append(sortByRelevanciaHidratosQuery).toString();
        return em.createNativeQuery(query, Receita.class).getResultList();
    }

    public List sortByHidratosDesc() {
        List<Long> lista = searchQuery;
        String sortByRelevanciaHidratosQuery = "";
        String var = "(";
        for (int i = 0; i <= lista.size() - 1; i++) {
            if (i < lista.size() - 1) {
                var += lista.get(i) + ",";
            } else {
                var += lista.get(i) + ")";
            }
        }
        if (sortByRelevancia) {
            sortByRelevanciaHidratosQuery = "ORDER BY Relevancia DESC, hidratos DESC";
        } else {
            sortByRelevanciaHidratosQuery = "ORDER BY hidratos DESC";
        }
        String query = new StringBuilder().append("SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN ").append(var).append(") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id ").append("WHERE RI.ingrediente_id IN ").append(var).append(" GROUP BY receita_id ").append(sortByRelevanciaHidratosQuery).toString();
        return em.createNativeQuery(query, Receita.class).getResultList();
    }

    // 2.1.2 Sorts by Gorduras
    public List sortByGordurasAsc() {
        List<Long> lista = searchQuery;
        String sortByRelevanciaGordurasQuery = "";
        String var = "(";
        for (int i = 0; i <= lista.size() - 1; i++) {
            if (i < lista.size() - 1) {
                var += lista.get(i) + ",";
            } else {
                var += lista.get(i) + ")";
            }
        }
        if (sortByRelevancia) {
            sortByRelevanciaGordurasQuery = "ORDER BY Relevancia DESC, gorduras ASC";
        } else {
            sortByRelevanciaGordurasQuery = "ORDER BY gorduras ASC";
        }
        String query = new StringBuilder().append("SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN ").append(var).append(") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id ").append("WHERE RI.ingrediente_id IN ").append(var).append(" GROUP BY receita_id ").append(sortByRelevanciaGordurasQuery).toString();
        return em.createNativeQuery(query, Receita.class).getResultList();
    }

    public List sortByGordurasDesc() {
        List<Long> lista = searchQuery;
        String sortByRelevanciaGordurasQuery = "";
        String var = "(";
        for (int i = 0; i <= lista.size() - 1; i++) {
            if (i < lista.size() - 1) {
                var += lista.get(i) + ",";
            } else {
                var += lista.get(i) + ")";
            }
        }
        if (sortByRelevancia) {
            sortByRelevanciaGordurasQuery = "ORDER BY Relevancia DESC, gorduras DESC";
        } else {
            sortByRelevanciaGordurasQuery = "ORDER BY gorduras DESC";
        }
        String query = new StringBuilder().append("SELECT R.*, (SELECT COUNT(*) FROM Receita_Ingrediente WHERE receita_id = R.id && ingrediente_id IN ").append(var).append(") as 'Relevancia' FROM Receita R INNER JOIN Receita_Ingrediente RI ON R.id = RI.receita_id ").append("WHERE RI.ingrediente_id IN ").append(var).append(" GROUP BY receita_id ").append(sortByRelevanciaGordurasQuery).toString();
        return em.createNativeQuery(query, Receita.class).getResultList();
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
}
