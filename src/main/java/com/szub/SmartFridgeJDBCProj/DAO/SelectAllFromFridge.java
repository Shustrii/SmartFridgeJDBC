package com.szub.SmartFridgeJDBCProj.DAO;

import com.szub.SmartFridgeJDBCProj.Tables.FridgeTable;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectAllFromFridge extends MappingSqlQuery<FridgeTable> {
    private static final String SQL_SELECT_ALL_FROM_FRIDGE = "SELECT f.id, f.id_of_product as product_id, pr.name as product_name, f.quantity FROM fridge f left join products pr on f.id_of_product = pr.id";

    public SelectAllFromFridge(DataSource dataSource){
        super(dataSource, SQL_SELECT_ALL_FROM_FRIDGE);
    }

    @Override
    protected FridgeTable mapRow(ResultSet resultSet, int i) throws SQLException {
        FridgeTable fridgeTable = new FridgeTable();
        fridgeTable.setId(resultSet.getLong("id"));
        fridgeTable.setProductID(resultSet.getLong("product_id"));
        fridgeTable.setProductName(resultSet.getString("product_name"));
        fridgeTable.setQuantity(resultSet.getLong("quantity"));
        return fridgeTable;
    }
}
