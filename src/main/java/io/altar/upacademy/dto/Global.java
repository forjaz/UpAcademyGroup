package io.altar.upacademy.dto;
import io.altar.upacademy.model.Receita;
import io.altar.upacademy.service.EntityService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.ViewScoped;
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
    private List<Long> receitaIDListDebug = new ArrayList<>();
    private List<Long> uniqueReceitaIDListDebug = new ArrayList<>();
    private List<Long> renderedResult = new ArrayList<>();

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
    public List returnIngredientes() {
        return em.createQuery("SELECT e FROM Ingrediente e").getResultList();
    }

    public void searchIngredientes() {
        List<Long> receitaIDList = convertIngredienteIDToReceitaID(searchQuery);
        receitaIDListDebug = receitaIDList;
        List<Long> uniqueReceitaIDList = removeReceitaIDDuplicates(receitaIDList);
        uniqueReceitaIDListDebug = uniqueReceitaIDList;
        List<Long> listMultipleOfFour = ensureLengthMultipleOfFour(uniqueReceitaIDList);
        renderedResult = listMultipleOfFour;
    }

    public List<Long> convertIngredienteIDToReceitaID(List<Long> inputList) {
        String query = "SELECT e.receita.id FROM Receita_Ingrediente e WHERE ";
        List<Long> resultList = new ArrayList<>();
        if (inputList == null) {
            return resultList;
        } else {
            for (int i = 0; i < inputList.size(); i++) {
                if (i == inputList.size() - 1) {
                    query = query + "ingrediente_id=" + inputList.get(i);
                } else {
                    query = query + "ingrediente_id=" + inputList.get(i) + " or ";
                }
            }
            return em.createQuery(query).getResultList();
        }
    }

    public List<Long> removeReceitaIDDuplicates(List<Long> inputList) {
        List<Long> resultList = new ArrayList<>();
        for (Long id : inputList) {
            if (!resultList.contains(id)) {
                resultList.add(id);
            }
        }
        return resultList;
    }

    public List<Long> ensureLengthMultipleOfFour(List<Long> inputList) {
        if (inputList.size() % 4 != 0) {
            inputList.add(1L);
        }
        return inputList;
    }

    public void renderLeft() {
        if (startGridIndex > 3) {
            startGridIndex -= 4;
        }
    }

    public void renderRight() {
        if (renderedResult.size() > startGridIndex + 4) {
            startGridIndex += 4;
        }
    }

    public Long findUniqueRecipeId(int index) {
		
		Long rId = renderedResult.get(index);
		
		return rId;
	}
    
    public void queryGetRecipe (Long id) {
    	
    	Receita r =  (Receita) em.createQuery("SELECT e FROM Receita WHERE id="+ id).getResultList();
    	
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

    public List<Long> getRenderedResult() {
        return renderedResult;
    }

    public void setRenderedResult(List<Long> renderedResult) {
        this.renderedResult = renderedResult;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getStartGridIndex() {
        return startGridIndex;
    }

    public void setStartGridIndex(int startGridIndex) {
        this.startGridIndex = startGridIndex;
    }

    public List<Long> getReceitaIDListDebug() {
        return receitaIDListDebug;
    }

    public void setReceitaIDListDebug(List<Long> receitaIDListDebug) {
        this.receitaIDListDebug = receitaIDListDebug;
    }

    public List<Long> getUniqueReceitaIDListDebug() {
        return uniqueReceitaIDListDebug;
    }

    public void setUniqueReceitaIDListDebug(List<Long> uniqueReceitaIDListDebug) {
        this.uniqueReceitaIDListDebug = uniqueReceitaIDListDebug;
    }
}
