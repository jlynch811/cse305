/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Tarun
 */
public class RegisterDAO {
    //String creationDate, String ccNumber, String rating, String ssnNumber, String startDate, String hourlyRate, String employeeType
    public static boolean register_fmuser(String userMail, String password,
                String firstName, String lastName, String address, String city, String state,
                String zipcode, String telephone, String ccNumber, String preferences) {
        Connection con = null;
        PreparedStatement ps = null;
        System.out.println("Inside register_fmuser");
        
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, \"FMUser\")");
            ps.setString(1, userMail);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, address);
            ps.setString(6, city);
            ps.setString(7, state);
            ps.setString(8, zipcode);
            ps.setString(9, telephone);
            ps.executeUpdate();
            
            Date date = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(date);

            ps = con.prepareStatement("INSERT INTO FMPlusUsers() VALUES (LAST_INSERT_ID(), ?, ?, 0)");
            ps.setString(1, currentTime);
            ps.setString(2, ccNumber);
            ps.executeUpdate();
            
            if (preferences != null) {
                String[] pref_arr = preferences.split(",");
                
                for (String pref : pref_arr) {
                    ps = con.prepareStatement("INSERT INTO Preferences VALUES (LAST_INSERT_ID(), ?)");
                    ps.setString(1, pref.trim());
                    ps.executeUpdate();
                }
            }
            
            ps = con.prepareStatement("INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES (\"Personal\", LAST_INSERT_ID(), NULL, 0)");
            ps.executeUpdate();
            
            System.out.println("Success");
            return true;
            
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    "SQL Error"));
            return false;
        } finally {
            DataConnect.close(con);
        }
    }
    
    public static boolean register_emp(String userMail, String password,
                String firstName, String lastName, String address, String city, String state,
                String zipcode, String telephone, String ssnNumber, String hourlyRate, String empType) {
        
        Connection con = null;
        PreparedStatement ps = null;
        System.out.println("Inside register_emp");
        
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("INSERT INTO Users(EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, \"Employee\")");
            ps.setString(1, userMail);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, address);
            ps.setString(6, city);
            ps.setString(7, state);
            ps.setString(8, zipcode);
            ps.setString(9, telephone);
            ps.executeUpdate();
            
            Date date = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(date);

            String employeeType;
            if (empType.equals("rep")) {
                employeeType = "Representative";
            } else {
                employeeType = "Manager";
            }
            
            ps = con.prepareStatement("INSERT INTO Employees VALUES (LAST_INSERT_ID(), ?, ?, ?, ?)");
            ps.setString(1, ssnNumber);
            ps.setString(2, currentTime);
            ps.setString(3, hourlyRate);
            ps.setString(4, employeeType);
            ps.executeUpdate();
            
            System.out.println("Success");
            return true;
            
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    "SQL Error"));
            return false;
        } finally {
            DataConnect.close(con);
        }
    }
}
