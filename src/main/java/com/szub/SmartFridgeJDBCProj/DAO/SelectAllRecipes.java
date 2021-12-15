package com.szub.SmartFridgeJDBCProj.DAO;

import com.szub.SmartFridgeJDBCProj.Tables.ProductTable;
import com.szub.SmartFridgeJDBCProj.Tables.RecipeTable;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectAllRecipes extends MappingSqlQuery<RecipeTable> {
    private static final String SQL_SELECT_ALL_RECIPES = "SELECT * FROM recipes";

    public SelectAllRecipes(DataSource dataSource){
        super(dataSource, SQL_SELECT_ALL_RECIPES);
    }

    protected RecipeTable mapRow(ResultSet rs, int rowNum)throws SQLException {
        RecipeTable recipeTable = new RecipeTable();
        recipeTable.setId(rs.getLong("id"));
        recipeTable.setRecipe(rs.getString("recipe"));
        return recipeTable;
    }
}
