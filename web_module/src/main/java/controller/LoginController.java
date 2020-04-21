package controller;


import interfaces.LoginUserLocal;
import interfaces.ShoppingCartLocal;
import model.User;
import org.primefaces.PrimeFaces;
import org.springframework.core.env.SystemEnvironmentPropertySource;

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
    LoginUserLocal loginSession;

    public void login() {
        boolean isSuccessfullyLoggedIn = false;

        if (loginSession.login(username, password)) {
            isSuccessfullyLoggedIn = true;

            outputMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Välkommen", username);
        }
        else {
            outputMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Fel användarnamn/lösenord");
        }
        FacesContext.getCurrentInstance().addMessage(null, outputMessage);
        PrimeFaces.current().ajax().addCallbackParam("isSuccessfullyLoggedIn", isSuccessfullyLoggedIn);
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

    public void setLoginSession(LoginUserLocal loginSession) {
        this.loginSession = loginSession;
    }

}
