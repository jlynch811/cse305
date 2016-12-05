/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author John Lynch
 */
@Named(value = "receiveMessage")
@SessionScoped
public class ReceiveMessage implements Serializable {
    
    private ArrayList<String> messages = new ArrayList();
    private String idToDelete;
    /**
     * Creates a new instance of ReceiveMessage
     */
    public ReceiveMessage() {
    }

    public ArrayList getMessages() {
        return messages;
    }

    public void setMessages(ArrayList messages) {
        this.messages = messages;
    }

    public String getIdToDelete() {
        return idToDelete;
    }

    public void setIdToDelete(String idToDelete) {
        this.idToDelete = idToDelete;
    }
    
    public String displayMessages(){
        Connection con = null;
        PreparedStatement ps = null;
        
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        System.out.println("User ID: " + userId);
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM Messages WHERE ReceiverId = ?;");
            ps.setString(1, userId);

            ResultSet rs = ps.executeQuery();
            messages = new ArrayList();
            while (rs.next()) {
                messages.add("(" + rs.getString("MessageId") + ") FROM: " + rs.getString("SenderId"));
                messages.add("SUBJECT: " + rs.getString("MsgSubject"));
                messages.add("MESSAGE: " + rs.getString("ReceiverId"));
                messages.add("------");
                
            }
       
            return "receivemessage";
            
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    "SQL Error"));
            return "receivemessage";
        } finally {
            DataConnect.close(con);
        }
        
    }
    
    public String deleteMessage(){
        Connection con = null;
        PreparedStatement ps = null;
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("DELETE FROM Messages WHERE MessageId = ? AND ReceiverId = ?;");
            ps.setString(1, idToDelete);
            ps.setString(2, userId);

            int result = ps.executeUpdate();
            
            
            if(result>0)
            {
                FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Message deleted successfully",
                    "Success"));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No Messages Deleted",
                    "Failure"));
            }
            
            idToDelete = null;
            
            return displayMessages();
            
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    "SQL Error"));
            return displayMessages();
        } finally {
            DataConnect.close(con);
        }
        
    }
    
}
