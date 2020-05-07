package controller;

import interfaces.UserManagementLocal;
import model.Role;
import model.User;
import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeRequestContext;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class RegisterController implements Serializable {

    @EJB
    UserManagementLocal userManagement;

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String phone;
    private String email;
    private String password;
    private String verifyPassword;

    public void register() {

        Role role = userManagement.selectRole();
        User user = userManagement.createUser(firstName, lastName, address, city, phone, email, password, role);

        FacesMessage outputMessage = null;

        if (userManagement.submit(user)) {
            outputMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tack, vi har mottagit din registrering", null);

            firstName = "";
            lastName = "";
            address = "";
            city = "";
            phone = "";
            email = "";
            password = "";
            verifyPassword = "";

        } else {
            outputMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Något gick fel med registreringen", null);
        }
        FacesContext.getCurrentInstance().addMessage(null, outputMessage);
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
