package controller;

import interfaces.ProductLocal;
import interfaces.ShoppingCartLocal;
import model.Order;
import model.OrderItem;
import model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.MethodExpression;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    ProductLocal productSession;
    @EJB
    ShoppingCartLocal shoppingCartSession;

    private List<Product> productList;
    private Order shoppingCartOrder = new Order();
    private List<Product> filteredProductList;
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
      shoppingCartOrder = shoppingCartSession.add(shoppingCartOrder, selectedProduct, productQuantity);
      totalOrderAmount = shoppingCartSession.updateOrderAmount(shoppingCartOrder);
    }

    public void emptyCart() {
        shoppingCartSession.clear(shoppingCartOrder);
        totalOrderAmount = shoppingCartSession.updateOrderAmount(shoppingCartOrder);
    }

    public void deleteCartItem(OrderItem orderItem) {
        shoppingCartSession.remove(shoppingCartOrder, orderItem);
        totalOrderAmount = shoppingCartSession.updateOrderAmount(shoppingCartOrder);
    }

    public void processOrder(){
        shoppingCartSession.processOrder(shoppingCartOrder);
        shoppingCartOrder = new Order();
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
