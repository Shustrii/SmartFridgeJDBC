package com.szub.SmartFridgeJDBCProj.Tables;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class ProductTable implements Serializable {
    private Long id;
    private String name;
    private String measure;
    private Long cost;
    //private List<ProductTable> productTables;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }


    //    public List<ProductTable> getProductTables() {
//        return productTables;
//    }
//    public void setProductTables(List<ProductTable> productTables) {
//        this.productTables = productTables;
//    }

    @Override
    public String toString() {
        return "ProductTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", measure='" + measure + '\'' +
                ", cost=" + cost +
                '}';
    }
}
