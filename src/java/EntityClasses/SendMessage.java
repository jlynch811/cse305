package EntityClasses;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author John
 */
@Named(value = "sendmessage")
@SessionScoped
public class SendMessage implements Serializable {
    
    private String sender;
    private String message;
    private String subject;

    /**
     * Creates a new instance of Homepage
     */
    public SendMessage() 
    {
        
    }
    
    public void setSender(String sender)
    {
        this.sender = sender;
    }
    
    public String getSender()
    {
        return sender;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public String getMessage(String message)
    {
        return message;
    }
    
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    
    public String getSubject(String subject)
    {
        return subject;
    }
    
    public String sendMessage()
    {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("INSERT INTO Messages(SentDate, MsgSubject, MsgContent, SenderId, ReceiverId)\n" +
"VALUES(CURDATE(), ?, ?, ?, ?);");
            ps.setString(1, subject);
            ps.setString(2, message);
            ps.setString(3, sender);
            //ps.setString(4, receiver);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                System.out.println(rs.getString("Count"));
                if(rs.getString("Count").equals("1")) {
                    System.out.println("Success");
                    return rs.getString("UserId");
                } else {
                    System.out.println("Failure");
                    return "";
                }
                //result found, means valid inputs
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return "";
        } finally {
            DataConnect.close(con);
        }
        return "sendmessage";
        
    }
    
    
    
}