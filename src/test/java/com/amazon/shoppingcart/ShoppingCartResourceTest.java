package com.amazon.shoppingcart;

import com.amazon.shoppingcart.model.Product;
import com.amazon.shoppingcart.model.ProductList;
import com.amazon.shoppingcart.model.ShoppingCart;
import com.amazon.shoppingcart.resource.ShoppingCartResource;
import org.junit.Test;

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
        assert cart.getProductList().size() == 1;
        assert cart.getProductList().get(0).getProductCode().equals("A1");
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
            resource.addItemToCart("");
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

        //WHEN ShoppingCartResource class is instantiated
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
        // GIVEN a shopping cart C with product list B
        //WHEN a ShoppingCartResource object is initialized with ProductList A and ShoppingCart C
        //THEN The ShoppingCartResource should be created with the ProductList A and ShoppingCart C






    }
}
