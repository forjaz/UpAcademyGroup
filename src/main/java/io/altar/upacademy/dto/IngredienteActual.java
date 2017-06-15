package io.altar.upacademy.dto;
import io.altar.upacademy.service.EntityService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(eager = true)
@Named("ingredienteActual")
@ApplicationScoped
public class IngredienteActual extends EntityService implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Long> searchQuery;
    private List<Long> renderedResult;

    private int startGridIndex = 0;


    // Constructor
    public IngredienteActual() {
    }

    @PostConstruct
    public void renderResultInit() {
        renderedResult.add(1L);
        renderedResult.add(2L);
        renderedResult.add(3L);
    }

    // Methods
    public List returnIngredientes() {
        return em.createQuery("SELECT e FROM Ingrediente e").getResultList();
    }

    public List convertIngredienteIDToReceitaID(List<Long> inputList) {
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
        renderedResult = resultList;
        return resultList;
    }

    public void renderReceita(List<Long> inputList) {
        for (int i = 0; i < inputList.size(); i++) {

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
}
