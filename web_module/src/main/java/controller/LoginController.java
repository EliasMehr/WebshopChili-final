package controller;


import interfaces.LoginUserLocal;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {
    private String username;
    private String password;
    private String outputMessage;

    @EJB
    LoginUserLocal loginSession;

    public String login() {

        if (loginSession.login(username, password)) {
            outputMessage = "Login successfully";
            return "index";
        }
        return null;

    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getOutputMessage() {
        return outputMessage;
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

    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }
}
