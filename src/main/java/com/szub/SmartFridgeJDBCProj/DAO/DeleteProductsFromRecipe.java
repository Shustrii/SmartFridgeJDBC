package com.szub.SmartFridgeJDBCProj.DAO;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class DeleteProductsFromRecipe extends SqlUpdate {
    private static final String sql = "DELETE FROM product_to_recipe WHERE product_id= :pId AND recipes_id = :rId";

    public DeleteProductsFromRecipe(DataSource dataSource){
        super(dataSource, sql);
        super.declareParameter(new SqlParameter("pId", Types.INTEGER));
        super.declareParameter(new SqlParameter("rId", Types.INTEGER));
    }
}
