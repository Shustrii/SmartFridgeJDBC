package com.szub.SmartFridgeJDBCProj.DAO;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertProductInFridge extends SqlUpdate {
    private static final String sql ="INSERT INTO fridge( id_of_product, quantity) VALUES ( :id_of_product, :quantity);";

    public InsertProductInFridge(DataSource dataSource){
        super(dataSource, sql);
        super.declareParameter(new SqlParameter("id_of_product", Types.INTEGER));
        super.declareParameter(new SqlParameter("quantity", Types.INTEGER));
    }
}
