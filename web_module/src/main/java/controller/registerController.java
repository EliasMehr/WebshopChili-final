package controller;

import model.Role;
import model.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class registerController implements Serializable {

    private String fName;
    private String lName;
    private String address;
    private String city;
    private String phone;
    private String email;
    private String password;
    private String password2;


    private EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("pUnit");



    public String registerUser() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        User user = new User();
        Role role = new Role();
        role.setId((long)1);
        role.setType(Role.Type.REGULAR_USER);
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setAddress(address);
        user.setCity(city);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(10)));
        user.setRole(role);

        try {
                entityManager.persist(user);
                entityTransaction.commit();
            } catch (Exception e) {
                entityTransaction.rollback();
            } finally {
                entityManager.close();
        }

        return "index";
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
