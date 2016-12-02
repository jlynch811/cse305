/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tarun
 */

@ManagedBean
public class Login {
    
    private String userMail;
    private String userPass;

    public Login() {
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    
    //validate login
    public String validateUsernamePassword() {
        System.out.println("Inside validateUsernamePassword");
        String userId = LoginDAO.validate(userMail, userPass);
        System.out.println("UserID : " + userId);
        if (!userId.equals("")) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("userid", userId);
            return "home";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Incorrect Username and Passowrd",
                    "Please enter correct username and Password"));
            return "index";
        }
    }
        
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index";
    }   
}
