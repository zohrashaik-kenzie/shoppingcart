package com.amazon.shoppingcart;

import com.amazon.shoppingcart.model.Product;
import com.amazon.shoppingcart.model.ProductList;
import com.amazon.shoppingcart.model.ShoppingCart;
import com.amazon.shoppingcart.resource.ShoppingCartResource;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartResourceTest {
    @Test
    public void addItemToCartT_addProductInStock_productAddedToCart() throws Exception {
        //GIVEN THere is a product list and
        // GIVEN There is a list of Products and the product to be added is in stock

        List<Product> products= new ArrayList<>();
        products.add(new Product("A1",20));
        ProductList productList = new ProductList(products);
        ShoppingCart cart = new ShoppingCart();

        ShoppingCartResource resource = new ShoppingCartResource( productList,cart);

        //WHEN the user adds the product to the cart
        resource.addItemToCart("A1");

        //THEN the product should be added to the user's shopping cart
        Assertions.assertEquals(cart.getCartProducts().size(),1,"The cart size is not 1");
        Assertions.assertEquals(cart.getCartProducts().containsKey("A1"),true,"Product Code is not correct");

    }

    @Test
    public void addItemToCart_productNotInStock_productNotAddedToCartThrowException()
    {
        //GIVEN There is a list of products and the product to be added is not in stock
        List<Product> products= new ArrayList<>();
        products.add(new Product("A1",0));
        ProductList productList = new ProductList(products);
        ShoppingCart cart = new ShoppingCart();

        ShoppingCartResource resource = new ShoppingCartResource(productList,cart);

        //WHEN the user adds the product to the cart
        try {
            resource.addItemToCart("A1");
        }
        catch (Exception e){
           Assertions.assertEquals (e.getMessage(),"The product is not in stock",
                   "Wrong Exception thrown for item added that is not in stock");
        }

        //THEN the product should be added to the user's shopping cart
        // THEN the user should get a message
        Assertions.assertEquals(cart.getCartProducts().size(),0,
                "Item was added to cart even though it is not in stock");

    }

    @Test
    public void addItemToCart_invalidProdutCode_productNotAddedToCartThrowException()
    {
        //GIVEN There is a list of products and the product to be added is not on the list

        List<Product> products= new ArrayList<>();
        products.add(new Product("A1",0));
        ProductList productList = new ProductList(products);
        ShoppingCart cart = new ShoppingCart();

        ShoppingCartResource resource = new ShoppingCartResource( productList,cart);

        //WHEN the user adds the product to the cart
        try {
            resource.addItemToCart("A2");
        }
        catch (Exception e){
            Assertions.assertEquals (e.getMessage(),"Invalid Product Code",
                    "Wrong Exception thrown for item added that is not valid");
        }

        //THEN the product should not be added to the user's shopping cart
        // THEN the user should get a message that 'Invalid Product Code'
        Assertions.assertEquals(cart.getCartProducts().size(),0,
                "Item was added to cart even though it is not a valid product");

    }

    @Test
    public void shoppingCourtResourceConstructor_passProductList_instanceHasProductListEmptyCart()
    {

        //GIVEN a product list A
        List<Product> products= new ArrayList<>();
        products.add(new Product("A1",10));
        ProductList productList = new ProductList(products);

        //WHEN ShoppingCartResource class is instantiated with product list A
        ShoppingCartResource resource = new ShoppingCartResource(productList);

        //THEN the resource object should have the product list A
        Assertions.assertEquals(resource.getProductList(), productList,
                "The Product list in the constructor does not have the correct product list passed to it");

        //AND it should have a new shopping cart with no products in it
        Assertions.assertNotEquals (resource.getCart(), null,
                "The shopping cart resource does not have a shopping cart");
        Assertions.assertEquals(resource.getCart().getCartProducts().size(),0,
                "The Shopping cart resource has a shopping cart with products in it");

    }

    @Test
    public void shoppingCartResourceConstructorWithShoppingCartTest() {

        //GIVEN a product list A
        List<Product> products= new ArrayList<>();
        products.add(new Product("A1",10));
        products.add(new Product("A2",20));

        ProductList productList = new ProductList(products);

        // GIVEN a shopping cart C with product list B
        Map<String,Integer> cartProducts= new HashMap<>();
        cartProducts.put("A1",0);

        ShoppingCart cart = new ShoppingCart(cartProducts);

        //WHEN a ShoppingCartResource object is initialized with ProductList A and ShoppingCart C
        ShoppingCartResource resource = new ShoppingCartResource(productList, cart);

        //THEN The ShoppingCartResource should be created with the ProductList A
        Assertions.assertEquals(resource.getProductList(),productList,
                "ShoppingCartResource is not initialized with the correct Product List");

        // and ShoppingCart C
        Assertions.assertEquals(resource.getCart().getCartProducts(),cartProducts,
                "ShoppingCartResource is not initialized with the correct Shopping Cart");

    }

}
