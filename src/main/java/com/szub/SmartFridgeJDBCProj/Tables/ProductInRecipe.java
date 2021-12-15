package com.szub.SmartFridgeJDBCProj.Tables;

public class ProductInRecipe extends ProductTable {
    private Long recipeID;
    private Long quantity;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getRecipeId() {
        return recipeID;
    }

    public void setRecipeId(Long recipeID) {
        this.recipeID = recipeID;
    }

    @Override
    public String toString() {
        return "ProductsInRecipe{" +
                "recipeID=" + getRecipeId() +
                ", name='" + getName() + '\'' +
                ", productID='" + getId() + '\'' +
                ", quantity=" + getQuantity() +
                '}';
    }}
