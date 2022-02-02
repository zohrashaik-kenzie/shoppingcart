package com.amazon.shoppingcart.model;

import java.util.List;

public class ProductList {

    private List<Product> productList;

    public ProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setItemList(List<Product> productList) {
        this.productList = productList;
    }

    public int isProductInStock(String code) {
        for(Product product : productList){
            if (product.getProductCode().equalsIgnoreCase(code)){
                if (product.getProductQuantity() > 0)
                    return 1;
                else
                    return 0;
            }
        }
        return -1;
    }
}
