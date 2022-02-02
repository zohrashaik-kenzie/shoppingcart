package com.amazon.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> cartProducts = new ArrayList<>();

    public List<Product> getProductList() {
        return cartProducts;
    }

    public void setProductList(List<Product> productList) {
        this.cartProducts = productList;
    }

    public void addProductToCart(String code){
        Product product = new Product(code,1);
        cartProducts.add(product);
    }

    public ShoppingCart() {
    }

}
