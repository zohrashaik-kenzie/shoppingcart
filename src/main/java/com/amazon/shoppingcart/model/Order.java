package com.amazon.shoppingcart.model;

public class Order {
    private int orderId;
    private ShoppingCart cart;

    public Order(ShoppingCart cart) {
        this.cart = cart;
    }
}
