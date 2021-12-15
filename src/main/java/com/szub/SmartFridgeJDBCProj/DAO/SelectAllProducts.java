package com.szub.SmartFridgeJDBCProj.DAO;

import com.szub.SmartFridgeJDBCProj.Tables.ProductTable;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectAllProducts extends MappingSqlQuery<ProductTable> {
    private static final String SQL_SELECT_ALL_PRODUCTS="SELECT * FROM products";

    public SelectAllProducts(DataSource dataSource){
        super(dataSource, SQL_SELECT_ALL_PRODUCTS);
    }
    @Override
    protected ProductTable mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ProductTable productTable = new ProductTable();
        productTable.setId(resultSet.getLong("id"));
        productTable.setName(resultSet.getString("name"));
        productTable.setMeasure(resultSet.getString("measure"));
        productTable.setCost(resultSet.getLong("cost"));
        return productTable;
    }
}
