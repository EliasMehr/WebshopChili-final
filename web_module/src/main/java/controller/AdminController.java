package controller;

import interfaces.AdminLocal;
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

    public void fetchCustomers() {
        setCustomerList(adminSession.loadAllCustomers());
    }

    public boolean globalFilterFunction(Object customer, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        User user = (User) customer;
        return user.getFirstName().toLowerCase().contains(filterText);

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
}
