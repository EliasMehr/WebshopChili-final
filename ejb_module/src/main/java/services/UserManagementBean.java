package services;

import dao.UserDAO;
import interfaces.UserManagementLocal;
import model.Role;
import model.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.stream.Stream;

@Stateless
public class UserManagementBean implements UserManagementLocal {

    @Inject
    private UserDAO userDAO;

    private User currentUser;

    @Override
    public User createUser(String firstName, String lastName, String address, String city, String phone, String email, String password, Role role) {
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setCity(city);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(10)));
        user.setRole(role);

        return user;
    }

    @Override
    public Role selectRole() {
        Role role = new Role();
        role.setType(Role.Type.REGULAR_USER);
        role.setId((long) 1);
        return role;
    }

    @Override
    public boolean submit(User user) {
        try {
            userDAO.create(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        try {
            User user = requestUser(username);
            if (isCorrectPassword(user, password)) {
                currentUser = user;
                return true;
            }
        } catch (Exception e) {
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
    public boolean isCorrectPassword(User user, String password) {
        return Stream.of(user).anyMatch(userObject -> BCrypt.checkpw(password, user.getPassword()));
    }

    @Override
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    @Override
    public User getUser() {
        return currentUser;
    }



}
