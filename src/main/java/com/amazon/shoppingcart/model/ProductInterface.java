package com.amazon.shoppingcart.model;

public interface ProductInterface {

    public void addProduct(String productCode, int quantity);
    public void removeProduct(String productCode);
    public boolean isProductInStock(String productCode);
}
