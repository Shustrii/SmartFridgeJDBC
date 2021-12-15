package com.szub.SmartFridgeJDBCProj.MainClass;

import com.szub.SmartFridgeJDBCProj.DAO.FridgeDAO;
import com.szub.SmartFridgeJDBCProj.Tables.FridgeTable;
import com.szub.SmartFridgeJDBCProj.Tables.ProductTable;
import com.szub.SmartFridgeJDBCProj.Tables.RecipeTable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SmartFridgeMain {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("datasource-drivermanager.xml");
        FridgeDAO fridgeDAO = (FridgeDAO) context.getBean("contactDao");

        //listOfProducts(fridgeDAO.findProductsForRecipe(1L));
        listOfRecipe(fridgeDAO.allRecipes());
        listOfProducts(fridgeDAO.allProducts());
        listOfFridge(fridgeDAO.fridge());
        //listOfFridge(fridgeDAO.findProductNotInFridge());
    }


    private static void listOfRecipe(List<RecipeTable> recipeTables){
        for (RecipeTable recipeTable: recipeTables){
            System.out.println(recipeTable);
            if (recipeTable.getProductTables()!=null){
                for (ProductTable productTable : recipeTable.getProductTables()){
                        System.out.println("---" + productTable);
                }
            }
            System.out.println();
        }
    }
    private static void listOfProducts(List<ProductTable> productTables){
        for (ProductTable productTable: productTables){
            System.out.println(productTable);
        }
    }
    private static void listOfFridge(List<FridgeTable> fridgeTables){
        for (FridgeTable fridgeTable: fridgeTables){
            System.out.println(fridgeTable);
        }
    }
}
