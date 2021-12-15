package com.szub.SmartFridgeJDBCProj.Tables;

import java.io.Serializable;
import java.util.List;

public class FridgeTable implements Serializable {
    private Long id;
    private Long quantity;
    private Long productID;
    private String productName;
    //List<ProductTable> productTables;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

//    public List<ProductTable> getProductTables() {
//        return productTables;
//    }
//
//    public void setProductTables(List<ProductTable> productTables) {
//        this.productTables = productTables;
//    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "FridgeTable{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", productID=" + productID +
                ", productName='" + productName + '\'' +
                '}';
    }
}
