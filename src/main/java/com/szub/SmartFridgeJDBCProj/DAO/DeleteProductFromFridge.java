package com.szub.SmartFridgeJDBCProj.DAO;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class DeleteProductFromFridge extends SqlUpdate {
    private static final String sql = "DELETE FROM fridge WHERE id= :pId;";

    public DeleteProductFromFridge(DataSource dataSource){
        super(dataSource, sql);
        super.declareParameter(new SqlParameter("pId", Types.INTEGER));
    }

}
