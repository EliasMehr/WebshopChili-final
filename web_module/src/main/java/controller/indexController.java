package controller;

import interfaces.IndexLocal;
import model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class indexController implements Serializable {

    @EJB
    IndexLocal indexSession;

    private String search;
    private Product selectedProductItem;
    private List<Product> products;
    private List<Product> filteredProductList;

    @PostConstruct
    void init() {

        try {
            products = indexSession.fetchProducts();
            filteredProductList = indexSession.searchProduct(search, products);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void modal(Product product) {
        System.out.println("CLICKED PRODUCT = " + product.getName());
        setSelectedProductItem(product);
    }

    public List<Product> getFilteredProductList() {
        return filteredProductList;
    }

    public void setFilteredProductList(List<Product> filteredProductList) {
        this.filteredProductList = filteredProductList;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public IndexLocal getIndexSession() {
        return indexSession;
    }

    public void setIndexSession(IndexLocal indexSession) {
        this.indexSession = indexSession;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Product getSelectedProductItem() {
        return selectedProductItem;
    }

    public void setSelectedProductItem(Product selectedProductItem) {
        this.selectedProductItem = selectedProductItem;
    }
}
