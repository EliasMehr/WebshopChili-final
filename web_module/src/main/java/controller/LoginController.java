package controller;



import interfaces.UserManagementLocal;
import org.primefaces.PrimeFaces;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {
    private String username;
    private String password;
    private FacesMessage outputMessage;

    @EJB
    UserManagementLocal userManagement;

    public void login() {
        boolean isSuccessfullyLoggedIn = false;

        if (userManagement.login(username, password)) {
            isSuccessfullyLoggedIn = true;

            outputMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Välkommen", username);
        }
        else {
            outputMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Fel användarnamn/lösenord");
        }
        FacesContext.getCurrentInstance().addMessage(null, outputMessage);
        PrimeFaces.current().ajax().addCallbackParam("isSuccessfullyLoggedIn", isSuccessfullyLoggedIn);
    }

    public void logOut() {
        userManagement.logOut();

        // TODO
        // Visuell återkoppling att man loggats ut
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

}
