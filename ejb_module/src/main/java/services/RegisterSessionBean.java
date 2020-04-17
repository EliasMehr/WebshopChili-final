package services;

import dao.UserDAO;
import interfaces.RegisterUserLocal;
import model.Role;
import model.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RegisterSessionBean implements RegisterUserLocal {

    @Inject
    private UserDAO userDAO;


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
        return role;
    }

    @Override
    public boolean post(User user) {
        try {
            userDAO.create(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
