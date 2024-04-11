package br.com.manu.model.recursos.grupoDeRecurso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GrupoDeRecursoResponse {
    private Object summaryItems;
    private Object allItems;


    public GrupoDeRecursoResponse() {
        summaryItems = new Object();
        allItems = new Object();
    }

    public GrupoDeRecursoResponse(Object summaryItems, Object allItems) {
        this.summaryItems = summaryItems;
        this.allItems = allItems;
    }

    public void addSummaryItems(Object item) {
        summaryItems = item;
    }

    public void addAllItems(Object item) {
        allItems = item;
    }

    public Object getSummaryItems() {
        return summaryItems;
    }

    public void setSummaryItems(Object summaryItems) {
        this.summaryItems = summaryItems;
    }

    public Object getAllItems() {
        return allItems;
    }

    public void setAllItems(Object allItems) {
        this.allItems = allItems;
    }
}
