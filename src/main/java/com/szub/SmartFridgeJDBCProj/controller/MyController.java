package com.szub.SmartFridgeJDBCProj.controller;

import com.szub.SmartFridgeJDBCProj.DAO.FridgeDAO;
import com.szub.SmartFridgeJDBCProj.Tables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private FridgeDAO fridgeDAO;

    @RequestMapping("/")
    public String mainPage(){
        return "index";
    }
    @RequestMapping("/fridge")
    public String fridgePage(Model model){
        List<FridgeTable> fridgeTables = fridgeDAO.fridge();
        List<ProductTable> productNotInFridge = fridgeDAO.findProductNotInFridge();
        System.out.println("Fridge  "+ fridgeTables);
        System.out.println("Size of productNotInFridge ->>"+ productNotInFridge.size());
        System.out.println("ProductNotInFridge ->>"+ productNotInFridge);

        model.addAttribute("fridge", fridgeTables);
        model.addAttribute("productInFridge", new FridgeTable());
        model.addAttribute("productNotInFridge", productNotInFridge);

        return "fridge";
    }
    @RequestMapping("/products")
    public String productPage(Model model){
        List<ProductTable> productTables = fridgeDAO.allProducts();
        model.addAttribute("product", productTables);
        return "products";
    }
    @RequestMapping("/recipe")
    public String recipePage(Model model){
        System.out.println("--- Model in ->>>");
        List<RecipeTable> recipeTables = fridgeDAO.allRecipes();
        RecipeTable recipe = fridgeDAO.findRecipeById(1L);
        List<ProductInRecipe> productTables = fridgeDAO.findProductsForRecipe(1L);

        model.addAttribute("recipe", recipeTables);
        //model.addAttribute("recipeId", 1);
        model.addAttribute("product", new ProductInRecipe());
        model.addAttribute("mainRecipe", recipe);
        model.addAttribute("productTables",productTables);

        return "recipe";
    }

    //@RequestMapping("/recipe/{id}")
    @GetMapping("/recipe/{recipeId}")
    public String findRecipeProducts( @PathVariable Long recipeId, Model model){
        System.out.println("--->>> Recipe ID :"+ recipeId);
        List<RecipeTable> recipes = fridgeDAO.allRecipes();
        List<ProductInRecipe> products = fridgeDAO.findProductsForRecipe(recipeId);
        List<ProductInRecipe> productNotInRecipe = fridgeDAO.findProductNotInRecipe(recipeId);
        model.addAttribute("recipe", recipes);
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("product", new ProductInRecipe());
        model.addAttribute("productTables", products);
        model.addAttribute("productNotInRecipe",productNotInRecipe);
        return "recipe";
    }

    @RequestMapping(value = "/addRecipeProduct", method = RequestMethod.POST)
    public String addRecipeProduct(@ModelAttribute ProductInRecipe product, Model model){
        System.out.println("POST =>>> ");
        System.out.println("Product =>>> "+product);
        fridgeDAO.insertIntoRecipe(product);
        return "index";
    }
    @RequestMapping("/delete")
    public String deleteProduct(@ModelAttribute ProductInRecipe productInRecipe, Model model){
        //fridgeDAO.deleteProduct();
        System.out.println("delete  =>>> " + productInRecipe);
        //System.out.println("recipeID =" + model.getAttribute("recipeId"));
        fridgeDAO.deleteProductFromRecipe(productInRecipe);
        return "index";
    }
    @RequestMapping("/deleteFromFridge")
    public String deleteFromFridge(@ModelAttribute FridgeTable fridgeTable, Model model){
        System.out.println("deleteFromFridge ==>>"+fridgeTable);
        fridgeDAO.deleteProductFromFridge(fridgeTable);
        return "index";
    }
    @RequestMapping("/addProductFridge")
    public String addingProductInFridge(@ModelAttribute FridgeTable fridgeTable, Model model){
        System.out.println("Product ==>>"+ fridgeTable);

        fridgeDAO.insertProductInFridge(fridgeTable);
        return "index";
    }
    @RequestMapping("/order")
    public String orderList(@ModelAttribute RecipeTable recipeTable, Model model){
        System.out.println("Order ==>> "+ recipeTable);

        List<RecipeTable> recipeTables = fridgeDAO.allRecipes();
        model.addAttribute("allRecipe", recipeTables);
        model.addAttribute("recipe",new RecipeTable());
        if (recipeTable!= null){
            List<Order> productTables = fridgeDAO.findProductForOrder(recipeTable.getId());
            model.addAttribute("orderProd", productTables);
        }
        return "order";
    }
}
//
//	SELECT *
//            FROM products
//            WHERE NOT EXISTS
//            (SELECT *
//            FROM product_to_recipe
//            WHERE products.id = product_to_recipe.product_id AND product_to_recipe.recipes_id = 2)
