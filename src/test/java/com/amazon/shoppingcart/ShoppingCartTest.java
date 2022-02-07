package com.amazon.shoppingcart;

import com.amazon.shoppingcart.model.ShoppingCart;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartTest {

    @Test
    public void addProductToCart_EmptyShoppingCart_ProductAddedToCartWithQuantity1() {

        //GIVEN a Product Code and an empty shopping cart
        String productCode = "A1";
        ShoppingCart cart = new ShoppingCart();

        //WHEN you add this product to the shopping cart
        cart.addProductToCart(productCode);
        //THEN the product is added to the cart with Quantity 1
        Assertions.assertEquals(cart.getCartProducts().size(),1,
                "The shopping cart does not size 1");

        Assertions.assertEquals(cart.getCartProducts().containsKey(productCode),true,
                "The shopping cart does not have the correct product");

        Assertions.assertEquals(cart.getCartProducts().get(productCode),1,
                "The shopping cart does not have the correct quantity of the product");
    }
    
    @Test
    public void addProductToCart_shoppingcartHastheproductweareadding_productAddedToCartWithQuantityIncrementedBy1(){
        //GIVEN we have a shopping that is instantiated with product A and quantity 2

        Map<String,Integer> cartProducts= new HashMap<>();
        cartProducts.put("A1",2);
        cartProducts.put("A2",1);

        ShoppingCart cart = new ShoppingCart(cartProducts);

        //WHEN you add  product A to the shopping cart
        cart.addProductToCart("A1");
        //THEN The shopping cart should update the quantity of product A to 3
        Assertions.assertEquals(cart.getCartProducts().get("A1"),3,
                "The quantity was not updated correctly");
        // and the number of products should remain the same as before
        Assertions.assertEquals(cart.getCartProducts().size(), 2,
                "There are extra products in the cart");
        // and they should be the same products and quantities as before
        Assertions.assertEquals(cart.getCartProducts().get("A2"),1,
                "The cart has different products than before");

    }

    @Test
    public void addProductToCart_shoppingCartDoesNotHaveThisProductButOtherProducts_productAddedToCartWithQuantity1(){
        //GIVEN we have a shopping that is instantiated with some products
        Map<String,Integer> cartProducts= new HashMap<>();
        cartProducts.put("A1",2);
        cartProducts.put("A2",1);
        ShoppingCart cart = new ShoppingCart();
        cart.setCartProducts(cartProducts);

        //WHEN you add a new product A to the shopping cart
        cart.addProductToCart("A3");

        //THEN The shopping cart should add the product A with Quantity 1
        Assertions.assertEquals(cart.getCartProducts().get("A3"),1,
                "The quantity was not added correctly");

        //AND should have the size of the productlist incremented by 1
        Assertions.assertEquals(cart.getCartProducts().size(), 3,
                "There are incorrect number of products in the cart");

        //AND should have the original products and quantities as before
        Assertions.assertEquals(cart.getCartProducts().get("A1"),2,
                "The cart has different products and/or quantities than before");

        Assertions.assertEquals(cart.getCartProducts().get("A2"),1,
                "The cart has different products and/or quantities than before");

    }
}
