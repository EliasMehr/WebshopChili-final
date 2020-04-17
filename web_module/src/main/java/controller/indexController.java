package controller;

import model.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class indexController implements Serializable {

    private EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("pUnit");

    private List<Product> productList = new ArrayList<>();


    private String search;

    private Product currentProduct;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Product> typedQuery = entityManager.createQuery("" +
                "select p from Product p", Product.class);
        productList = typedQuery.getResultList();
        entityManager.close();
//        typedQuery.setParameter("email", search);

//        User u = null;
//        try {
//            apa = typedQuery.getResultList();
//        } catch (NoResultException | NonUniqueResultException e) {
//            e.printStackTrace();
//        }


    }

    public void modal(Product product) {
        System.out.println("CLICKED PRODUCT = " + product.getName());
        setCurrentProduct(product);
    }

}
