package com.amazon.shoppingcart.resource;


import com.amazon.shoppingcart.model.Product;
import com.amazon.shoppingcart.model.ProductList;
import com.amazon.shoppingcart.model.ShoppingCart;
import java.util.ArrayList;
import java.util.List;


public class ShoppingCartResource {

    private  ProductList productList;
    ShoppingCart cart;

    public ShoppingCartResource(ProductList productList) {
        this.productList = productList;
        cart = new ShoppingCart();
    }

    public ShoppingCartResource(ProductList productList, ShoppingCart cart) {
        this.productList = productList;
        this.cart = cart;
    }

    public void addItemToCart(String code) throws Exception {
        if (code.isEmpty() || code == null){
            throw new Exception("You cannot have an empty product code");
        }
        if (productList.isProductInStock(code) == 1){
            cart.addProductToCart(code);
        } else if (productList.isProductInStock(code) == 0){
            throw new Exception("The product is not in stock");
        } else {
            throw new Exception("Invalid Product Code");
        }
    }

    public ProductList getProductList() {
        return productList;
    }

    public ShoppingCart getCart() {
        return cart;
    }
}
