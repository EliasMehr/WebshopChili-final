package interfaces;

import model.Role;
import model.User;

import javax.ejb.Local;

@Local
public interface RegisterUserLocal {

    User createUser(String firstName, String lastName, String address, String city, String phone, String email, String password, Role role);

    Role selectRole();

    boolean post(User user);

}
