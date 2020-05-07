package controller;


import interfaces.UserManagementLocal;
import model.Role;
import org.primefaces.PrimeFaces;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {
    private String username;
    private String password;
    private FacesMessage outputMessage;
    private boolean isSuccessfullyLoggedIn = false;


    @Inject
    private ProductController productController;

    @EJB
    UserManagementLocal userManagement;

    public void login() {

        if (userManagement.login(username, password)) {
            isSuccessfullyLoggedIn = true;

            outputMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Välkommen", username);

            if (userManagement.getUser().getRole().getType() == Role.Type.ADMIN_USER) {
                redirectToAdminPage();
            } else {
                productController.updateProductPricing();
            }

            username = "";
            password = "";

        } else {
            outputMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Fel användarnamn/lösenord");
        }
        FacesContext.getCurrentInstance().addMessage(null, outputMessage);
        PrimeFaces.current().ajax().addCallbackParam("isSuccessfullyLoggedIn", isSuccessfullyLoggedIn);
    }

    private void redirectToAdminPage() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOut() {
        userManagement.logOut();
        isSuccessfullyLoggedIn = false;
        outputMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Du är nu utloggad");
        FacesContext.getCurrentInstance().addMessage(null, outputMessage);

        productController.emptyCart();
        productController.init();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSuccessfullyLoggedIn() {
        return isSuccessfullyLoggedIn;
    }

    public void setSuccessfullyLoggedIn(boolean successfullyLoggedIn) {
        isSuccessfullyLoggedIn = successfullyLoggedIn;
    }

    public String getCustomerName() {
        return isSuccessfullyLoggedIn ? userManagement.getUser().getFirstName() : "";
    }

}
