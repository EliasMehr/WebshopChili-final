package interfaces;

import model.User;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;

@Local
public interface LoginUserLocal {


    boolean login(String username, String password);

    List<User> requestUser(String username);

    boolean isCorrectPassword(User user, String password);

}
