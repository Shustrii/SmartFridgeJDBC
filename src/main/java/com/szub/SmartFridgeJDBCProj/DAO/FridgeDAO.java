package com.szub.SmartFridgeJDBCProj.DAO;

import com.szub.SmartFridgeJDBCProj.Tables.*;

import javax.sql.DataSource;
import java.util.List;

public interface FridgeDAO {
    public void setDataSource(DataSource dataSource);

    List<RecipeTable> allRecipes();
    List<ProductInRecipe> findProductsForRecipe(Long recipId);
    RecipeTable findRecipeById(Long id);
    List<ProductTable> allProducts();
    List<FridgeTable> fridge();
    List<ProductInRecipe> findProductNotInRecipe(Long Id);
    void insertIntoRecipe(ProductInRecipe productInRecipe);
    void deleteProductFromRecipe(ProductInRecipe productInRecipe);
    void deleteProductFromFridge(FridgeTable fridgeTable);
    void insertProductInFridge(FridgeTable fridgeTable);
    List<ProductTable> findProductNotInFridge();
    List<Order> findProductForOrder(Long recipeId);
}
