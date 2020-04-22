package controller;

import interfaces.AdminLocal;
import model.Order;
import model.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Named
@SessionScoped
public class AdminController implements Serializable{

    @EJB
    AdminLocal adminSession;

    private List<User> customerList;
    private List<User> filteredCustomer;
    private Order clickedOrder;

    public void fetchCustomers() {
        setCustomerList(adminSession.loadAllCustomers());
    }


    public double getPrice(Order order) {
        return order.getOrderItems()
                .stream()
                .mapToDouble(o -> o.getQuantity() * o.getPrice())
                .sum();

    }

    public List<User> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<User> customerList) {
        this.customerList = customerList;
    }

    public List<User> getFilteredCustomer() {
        return filteredCustomer;
    }

    public void setFilteredCustomer(List<User> filteredCustomer) {
        this.filteredCustomer = filteredCustomer;
    }

    public Order getClickedOrder() {
        return clickedOrder;
    }

    public void setClickedOrder(Order clickedOrder) {
        this.clickedOrder = clickedOrder;
    }
}
