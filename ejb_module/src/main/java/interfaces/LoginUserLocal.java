package interfaces;

import model.User;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface LoginUserLocal {


    boolean login(String username, String password);

    User requestUser(String username);

    boolean isCorrectPassword(User user, String password);

}
