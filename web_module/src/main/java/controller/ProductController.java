package controller;

import interfaces.ProductLocal;
import interfaces.ShoppingCartLocal;
import interfaces.UserManagementLocal;
import model.*;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    ProductLocal productSession;

    @EJB
    ShoppingCartLocal shoppingCartSession;

    @EJB
    UserManagementLocal userManagement;

    private List<Product> productList;
    private List<Product> filteredProductList;
    private Order shoppingCartOrder = new Order();
    private Product selectedProduct;
    private String searchInput;
    private int productQuantity;
    private double totalOrderAmount = 0;
    private FacesMessage outputMessage;


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

        outputMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
                selectedProduct.getName() , " Tillagd i varukorg");
        FacesContext.getCurrentInstance().addMessage(null, outputMessage);

    }

    public void emptyCart() {
        shoppingCartSession.clear();
        totalOrderAmount = shoppingCartSession.updateOrderAmount();
    }

    public void deleteCartItem(OrderItem orderItem) {
        shoppingCartSession.remove(orderItem);
        totalOrderAmount = shoppingCartSession.updateOrderAmount();
        outputMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, orderItem.getProduct().getName() , "Togs bort");
        FacesContext.getCurrentInstance().addMessage(null, outputMessage);

    }

    public void incrementOrderItemQuantiy(OrderItem item) {
       shoppingCartOrder = shoppingCartSession.incrementItemQuantity(item);
        totalOrderAmount = shoppingCartSession.updateOrderAmount();
    }

    public void decrementOrderItemQuantity(OrderItem item) {
        shoppingCartOrder = shoppingCartSession.decrementItemQuantity(item);
        totalOrderAmount = shoppingCartSession.updateOrderAmount();
    }

    public void checkout() {
        if (userManagement.isLoggedIn()) {
            if (shoppingCartOrder.getOrderItems().size() > 0) {
            shoppingCartOrder = shoppingCartSession.processOrder(userManagement.getUser());
              PrimeFaces current = PrimeFaces.current();
            outputMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Beställning lyckades" , null);
            current.executeScript("PF('cartDialog').hide()");
            totalOrderAmount = 0;
            } else {
                outputMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Du kan inte ha en tom order" , null);
            }
        } else {

            outputMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Måste logga in!" , null);
        }

        FacesContext.getCurrentInstance().addMessage(null, outputMessage);

    }

    public void updateProductPricing() {
        filteredProductList = productSession.updateProductPricing(productList);

        if (!shoppingCartOrder.getOrderItems().isEmpty()) {
            shoppingCartSession.updateOrderPricing(userManagement.getUser().getRole().getDiscountMultiplier());
            totalOrderAmount = shoppingCartSession.updateOrderAmount();
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
