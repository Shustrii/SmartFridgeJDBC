package com.szub.SmartFridgeJDBCProj.DAO;

import com.szub.SmartFridgeJDBCProj.Tables.RecipeTable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeRowMapper implements RowMapper<RecipeTable> {
    @Override
    public RecipeTable mapRow(ResultSet rs, int i) throws SQLException {
        RecipeTable recipeTable = new RecipeTable();
        recipeTable.setId(rs.getLong("id"));
        recipeTable.setRecipe(rs.getString("recipe"));
        return recipeTable;
    }
}
