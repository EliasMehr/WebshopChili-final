package controller;

import interfaces.ProductLocal;
import model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    ProductLocal productSession;

    private List<Product> productList;
    private Product currentProduct;
    private int currentProductCount;

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

    @PostConstruct
    public void init() {
        setProductList(productSession.loadAllProducts());
    }

    public void modal(Product product) {
        System.out.println("CLICKED PRODUCT = " + product.getName());
        setCurrentProduct(product);
    }
}
