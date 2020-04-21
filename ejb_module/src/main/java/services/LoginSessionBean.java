package services;

import dao.UserDAO;
import interfaces.LoginUserLocal;
import interfaces.ShoppingCartLocal;
import model.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Stream;

@Stateful
public class LoginSessionBean implements LoginUserLocal {

    @Inject
    private UserDAO userDAO;

    @EJB
    ShoppingCartLocal shoppingCart;

    public LoginSessionBean() {}

    @Override
    public boolean login(String username, String password) {
        try {
            User user = requestUser(username);
            shoppingCart.initializeUser(user);
            return isCorrectPassword(user, password);
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public User requestUser(String username) {
        User user = userDAO.findByEmail(username);
        return Stream.of(user).findFirst().orElse(null);
    }

    @Override
    public boolean isCorrectPassword(User user, String password) {
        return Stream.of(user).anyMatch(userObject -> BCrypt.checkpw(password, user.getPassword()));
    }
}
