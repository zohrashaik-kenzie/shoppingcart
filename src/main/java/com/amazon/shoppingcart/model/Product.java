package com.amazon.shoppingcart.model;

public class Product implements ProductInterface{

    String productCode;
    int productQuantity;

    public Product() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Product(String productCode, int productQuantity) {
        this.productCode = productCode;
        this.productQuantity = productQuantity;
    }


    @Override
    public void addProduct(String productCode, int quantity) {

    }

    @Override
    public void removeProduct(String productCode) {

    }

    @Override
    public boolean isProductInStock(String productCode) {
        return false;
    }
}
