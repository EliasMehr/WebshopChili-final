package interfaces;

import model.Role;
import model.User;

import javax.ejb.Local;

@Local
public interface UserManagementLocal {

    // Manages Register procedures
    User createUser(String firstName, String lastName, String address, String city, String phone, String email, String password, Role role);

    Role selectRole();

    boolean submit(User user);


    // Manages Login procedures
    User requestUser(String username);

    User getUser();

    boolean login(String username, String password);

    boolean isCorrectPassword(User user, String password);

    boolean isLoggedIn();

    void logOut();


}
