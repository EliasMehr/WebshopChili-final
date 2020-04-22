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

@Stateless
public class LoginSessionBean implements LoginUserLocal {

    @Inject
    private UserDAO userDAO;

    private User currentUser;

    public LoginSessionBean() {}

    @Override
    public boolean login(String username, String password) {
        try {
            User user = requestUser(username);
            if (isCorrectPassword(user, password)) {
                currentUser = user;
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
            return false;
    }

    @Override
    public User requestUser(String username) {
        User user = userDAO.findByEmail(username);
        return Stream.of(user).findFirst().orElse(null);
    }

    @Override
    public User getUser() {
        return currentUser;
    }

    @Override
    public boolean isCorrectPassword(User user, String password) {
        return Stream.of(user).anyMatch(userObject -> BCrypt.checkpw(password, user.getPassword()));
    }

    @Override
    public boolean isLoggedIn() {
        return currentUser != null;
    }


}
