package interfaces;

import model.User;

import javax.ejb.Local;

@Local
public interface LoginUserLocal {


    User requestUser(String username);

    User getUser();

    boolean login(String username, String password);

    boolean isCorrectPassword(User user, String password);

    boolean isLoggedIn();

}
