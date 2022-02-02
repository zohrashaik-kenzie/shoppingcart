package com.amazon.shoppingcart;

import com.amazon.shoppingcart.model.Product;
import com.amazon.shoppingcart.model.ProductList;
import com.amazon.shoppingcart.model.ShoppingCart;
import com.amazon.shoppingcart.resource.ShoppingCartResource;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartResourceTest {




    @Test
    public void addItemToCartTest() throws Exception {
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
        Assertions.assertEquals(cart.getProductList().size(),1,"The cart size is not 1");
        Assertions.assertEquals(cart.getProductList().get(0).getProductCode(),"A1","Product Code is not correct");

    }

    @Test
    public void addItemToCartThatIsNotInStockTest()
    {
        //GIVEN There is a list of products and the product to be added is not in stock

        List<Product> products= new ArrayList<>();
        products.add(new Product("A1",0));
        ProductList productList = new ProductList(products);
        ShoppingCart cart = new ShoppingCart();

        com.amazon.shoppingcart.resource.ShoppingCartResource resource = new com.amazon.shoppingcart.resource.ShoppingCartResource( productList,cart);

        //WHEN the user adds the product to the cart
        try {
            resource.addItemToCart("A1");
        }
        catch (Exception e){
            assert (e.getMessage().equals("The product is not in stock"));
        }

        //THEN the product should be added to the user's shopping cart
        // THEN the user should get a message
        assert cart.getProductList().size() == 0;

    }

    @Test
    public void addItemToCartThatIsNotInValidTest()
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
            assert (e.getMessage().equals("Invalid Product Code"));
        }

        //THEN the product should not be added to the user's shopping cart
        // THEN the user should get a message that 'Invalid Product Code'
        assert cart.getProductList().size() == 0;

    }

    @Test
    public void shoppingCourtResourceConstructorTest() {

        //GIVEN a product list A
        List<Product> products= new ArrayList<>();
        products.add(new Product("A1",10));
        ProductList productList = new ProductList(products);

        //WHEN ShoppingCartResource class is instantiated with product list A
        ShoppingCartResource resource = new ShoppingCartResource(productList);

        //THEN the resource object should have the product list A
        assert(resource.getProductList().equals(productList));

        //AND it should have a new shopping cart with no products in it
        assert (resource.getCart() != null);
        assert(resource.getCart().getProductList().isEmpty());

    }

    @Test
    public void shoppingCartResourceConstructorWithShoppingCartTest() {

        //GIVEN a product list A
        List<Product> products= new ArrayList<>();
        products.add(new Product("A1",10));
        products.add(new Product("A2",20));

        ProductList productList = new ProductList(products);

        // GIVEN a shopping cart C with product list B
        List<Product> productsh= new ArrayList<>();
        productsh.add(new Product("A1",1));

        ShoppingCart cart = new ShoppingCart();
        cart.setProductList(productsh);

        //WHEN a ShoppingCartResource object is initialized with ProductList A and ShoppingCart C
        ShoppingCartResource resource = new ShoppingCartResource(productList, cart);

        //THEN The ShoppingCartResource should be created with the ProductList A
        Assertions.assertEquals(resource.getProductList(),productList,
                "ShoppingCartResource is not initialized with the correct Product List");

        // and ShoppingCart C
        Assertions.assertEquals(resource.getCart().getProductList(),productsh,
                "ShoppingCartResource is not initialized with the correct Shopping Cart");

    }

}
