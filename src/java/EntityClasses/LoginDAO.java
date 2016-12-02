package EntityClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public static String validate(String userMail, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        System.out.println("Inside validate. User : " + userMail + " Password : " + password);
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select UserId, count(*) as Count from Users where EmailId = ? and Psswd = ?");
            ps.setString(1, userMail);
            ps.setString(2, password);

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
        
        System.err.println("Entry not found");
        return "";
    }
}