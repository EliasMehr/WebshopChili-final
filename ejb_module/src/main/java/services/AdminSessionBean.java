package services;

import dao.UserDAO;
import interfaces.AdminLocal;
import model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class AdminSessionBean implements AdminLocal {


    @Inject
    private UserDAO userDAO;

    @Override
    public List<User> loadAllCustomers() {
        return userDAO.findAllCustomers();
    }


}
