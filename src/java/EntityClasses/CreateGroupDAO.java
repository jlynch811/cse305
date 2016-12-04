/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alice
 */
public class CreateGroupDAO {
    public static String validate_group_registration(String groupId, String groupName,
                String ownerId, String groupType) {
        Connection con = null;
        PreparedStatement ps = null;
       // System.out.println("Inside validate. User : " + userMail + " Password : " + password);
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select GroupName, count(*) as Count from Groups where groupName = ? and ownerId = ?");
            ps.setString(1, groupName);
            ps.setString(2, ownerId);

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
