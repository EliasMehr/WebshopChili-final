package controller;

import interfaces.ProductLocal;
import model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    ProductLocal productSession;

    private List<Product> productList, cartList = new ArrayList<>();
    private Product currentProduct;
    private int currentProductCount;



    @PostConstruct
    public void init() {
        setProductList(productSession.loadAllProducts());

    }

    public void modal(Product product) {
        System.out.println("CLICKED PRODUCT = " + product.getName());
        setCurrentProduct(product);
    }

    // Testar mest för frontend
    public void addToCart(Long id) {
        Product cartProd;
        System.out.println(id);
        cartProd = productList.stream().filter(p -> p.getId().equals(id)).findAny().orElse(null);
        System.out.println(cartProd.getName());
        cartList.add(cartProd);
    }

    public void emptyCart() {
        cartList.clear();
    }

    public void deleteFromCart(Product product) {
        cartList.remove(product);
    }


    public int getCurrentProductCount() {
        return currentProductCount;
    }

    public void setCurrentProductCount(int currentProductCount) {
        this.currentProductCount = currentProductCount;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }

    public List<Product> getCartList() {
        return cartList;
    }

    public void setCartList(List<Product> cartList) {
        this.cartList = cartList;
    }
}
