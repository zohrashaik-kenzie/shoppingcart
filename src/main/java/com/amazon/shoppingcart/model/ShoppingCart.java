package com.amazon.shoppingcart.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    // A1->1, A1->2
    private Map<String,Integer> cartProducts = new HashMap<>();

    public ShoppingCart() {
    }

    public ShoppingCart(Map<String, Integer> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public Map<String, Integer> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(Map<String, Integer> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public void addProductToCart(String code){
        //check if the product already exists
        if(cartProducts.containsKey(code)){
            //update the cart
            int quantity = cartProducts.get(code);
            cartProducts.replace(code, quantity+1);
        } else {
            cartProducts.put(code,1);
        }
    }

}
