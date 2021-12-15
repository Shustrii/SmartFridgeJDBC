package com.szub.SmartFridgeJDBCProj.Tables;

import java.io.Serializable;
import java.util.List;

public class RecipeTable implements Serializable {
    private Long id;
    private String recipe;
    private List<ProductTable> productTables;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public List<ProductTable> getProductTables() {
        return productTables;
    }

    public void setProductTables(List<ProductTable> productTables) {
        this.productTables = productTables;
    }

    @Override
    public String toString() {
        return "RecipeTable{" +
                "id=" + id +
                ", recipe='" + recipe + '\'' +
                '}';
    }
}
