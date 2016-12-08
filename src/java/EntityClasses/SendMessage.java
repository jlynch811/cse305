/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.sql.*;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author John Lynch
 */
@Named(value = "sendmessage")
@RequestScoped
public class SendMessage {
    
    private String receiver;
    private String message;
    private String subject;

    /**
     * Creates a new instance of SendMessage
     */
    public SendMessage() {
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String sendMessage(String s)
    {
        Connection con = null;
        PreparedStatement ps = null;
        
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        System.out.println("User ID: " + userId);
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)\n" +
"VALUES(NOW(), ?, ?, ?, ?);");
            ps.setString(1, subject);
            ps.setString(2, message);
            ps.setString(3, userId);
            ps.setString(4, receiver);

            ps.executeUpdate();
            
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Message sent successfully",
                    "Success"));
            
            receiver = null;
            subject = null;
            message = null;
            
            return "sendmessage";
            
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    "SQL Error"));
            return "";
        } finally {
            DataConnect.close(con);
        }
        
    }
    
    
    
}
