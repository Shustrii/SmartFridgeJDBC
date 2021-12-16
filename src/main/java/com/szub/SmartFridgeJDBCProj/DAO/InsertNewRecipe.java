package com.szub.SmartFridgeJDBCProj.DAO;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertNewRecipe extends SqlUpdate {
    private static final String sql = "INSERT INTO public.recipes( recipe)VALUES ( :recipes)";

    public InsertNewRecipe(DataSource dataSource){
        super(dataSource, sql);
        super.declareParameter(new SqlParameter("recipes", Types.VARCHAR));

    }
}
