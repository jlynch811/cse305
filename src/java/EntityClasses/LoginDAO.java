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
    
    public static String resolveUserType(String userId) {
        Connection con = null;
        PreparedStatement ps = null;
        System.out.println("Inside resolveUserType. UserId : " + userId);
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select UserType from Users where UserId = ?");
            ps.setString(1, userId);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                System.out.println(rs.getString("UserType"));
                if (rs.getString("UserType").equals("FMUser")) {
                    System.out.println("Success");
                    return "home";
                } else if (rs.getString("UserType").equals("Employee")) {
                    System.out.println("Employee found.");
                    ps = con.prepareStatement("Select EmpType from Employees where UserId = ?");
                    ps.setString(1, userId);
                    
                    rs = ps.executeQuery();
                    
                    if (rs.next()) {
                        System.out.println(rs.getString("EmpType"));
                        if (rs.getString("EmpType").equals("Representative")) {
                            return "home_rep";
                        } else if (rs.getString("EmpType").equals("Manager")) {
                            return "home_manager";
                        }
                    } else {
                        System.out.println("Failure");
                        return "";
                    }
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