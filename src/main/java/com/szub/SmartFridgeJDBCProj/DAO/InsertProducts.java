package com.szub.SmartFridgeJDBCProj.DAO;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertProducts extends SqlUpdate {
    private static final String sql = "INSERT INTO product_to_recipe (product_id, quantity, recipes_id) VALUES (:product_id, :quantity, :recipes_id)";

    public InsertProducts(DataSource dataSource){
        super(dataSource, sql);
        super.declareParameter(new SqlParameter("product_id", Types.INTEGER));
        super.declareParameter(new SqlParameter("quantity", Types.INTEGER));
        super.declareParameter(new SqlParameter("recipes_id", Types.INTEGER));
    }
}
