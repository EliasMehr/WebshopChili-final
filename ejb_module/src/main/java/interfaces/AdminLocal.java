package interfaces;

import model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AdminLocal {
    List<User> loadAllCustomers();
}
