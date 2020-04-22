package controller;

import interfaces.LoginUserLocal;
import interfaces.ProductLocal;
import interfaces.ShoppingCartLocal;
import model.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    ProductLocal productSession;

    @EJB
    ShoppingCartLocal shoppingCartSession;

    @EJB
    LoginUserLocal loginSession;

    private List<Product> productList;
    private List<Product> filteredProductList;
    private Order shoppingCartOrder = new Order();
    private Product selectedProduct;
    private String searchInput;
    private int productQuantity;
    private double totalOrderAmount = 0;


    @PostConstruct
    public void init() {
        setProductList(productSession.loadAllProducts());
        filteredProductList = productList;
    }

    public void searchProduct() {
        filteredProductList = productSession.searchProduct(searchInput, productList);
    }

    public void addToCart() {
        shoppingCartOrder = shoppingCartSession.add(selectedProduct, productQuantity);
        totalOrderAmount = shoppingCartSession.updateOrderAmount();
        productQuantity = 1;
    }

    public void emptyCart() {
        shoppingCartSession.clear();
        totalOrderAmount = shoppingCartSession.updateOrderAmount();
    }

    public void deleteCartItem(OrderItem orderItem) {
        shoppingCartSession.remove(orderItem);
        totalOrderAmount = shoppingCartSession.updateOrderAmount();
    }

    public void checkout() {
        if (loginSession.isLoggedIn()) {
            shoppingCartOrder = shoppingCartSession.processOrder(loginSession.getUser());
            // TODO Jessie ge oss snygga meddelanden att ordern är genomförd
        } else {
            // TODO Fixa en snygg meddelande att användaren inte är inloggad
        }
    }


    public ProductLocal getProductSession() {
        return productSession;
    }

    public ShoppingCartLocal getShoppingCartSession() {
        return shoppingCartSession;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Order getShoppingCartOrder() {
        return shoppingCartOrder;
    }

    public List<Product> getFilteredProductList() {
        return filteredProductList;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public String getSearchInput() {
        return searchInput;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public double getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setProductSession(ProductLocal productSession) {
        this.productSession = productSession;
    }

    public void setShoppingCartSession(ShoppingCartLocal shoppingCartSession) {
        this.shoppingCartSession = shoppingCartSession;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setShoppingCartOrder(Order shoppingCartOrder) {
        this.shoppingCartOrder = shoppingCartOrder;
    }

    public void setFilteredProductList(List<Product> filteredProductList) {
        this.filteredProductList = filteredProductList;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setTotalOrderAmount(double totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }
}
