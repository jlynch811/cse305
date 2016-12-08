/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import EntityClasses.DataConnect;
import EntityClasses.SessionUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tarun
 */
@Named(value = "managerTransactions")
@SessionScoped
public class ManagerTransactions implements Serializable {

    private String empId;
    private String emailId;
    private String psswd;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String telephone;
    private String userType;
    private String ssnNumber;
    private String startDate;
    private String hourlyRate;
    private String empType;
    
    /**
     * Creates a new instance of ManagerTransactions
     */
    public ManagerTransactions() {
        
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPsswd() {
        return psswd;
    }

    public void setPsswd(String psswd) {
        this.psswd = psswd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSsnNumber() {
        return ssnNumber;
    }

    public void setSsnNumber(String ssnNumber) {
        this.ssnNumber = ssnNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(String hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }
    
    
    
    public String initEmpUpdate () {
        System.out.println("Inside initEmpUpdate");
        
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM Users U, Employees E WHERE U.UserId = ? AND E.UserId = ?");
            ps.setString(1, empId);
            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                emailId = rs.getString("EmailId");
                psswd = rs.getString("Psswd");
                firstName = rs.getString("FirstName");
                lastName = rs.getString("LastName");
                address = rs.getString("Address");
                city = rs.getString("City");
                state = rs.getString("State");
                zipcode = rs.getString("Zipcode");
                telephone = rs.getString("Telephone");
                userType = rs.getString("UserType");
                ssnNumber = rs.getString("SsnNo");
                startDate = rs.getString("StartDate");
                hourlyRate = rs.getString("HourlyRate");
                empType = rs.getString("EmpType");
            }
            return "update_emp_info";
        } catch (SQLException ex) {
            Logger.getLogger(FactoryBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
    public String updateEmployeeInfo () {
        System.out.println("Inside updateEmployeeInfo");
        
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("UPDATE Users SET EmailId=?, Psswd=?, FirstName=?, LastName=?, Address=?, City=?, State=?, Zipcode=?, Telephone=? WHERE UserId=?");
            ps.setString(1, emailId);
            ps.setString(2, psswd);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, address);
            ps.setString(6, city);
            ps.setString(7, state);
            ps.setString(8, zipcode);
            ps.setString(9, telephone);
            ps.setString(10, empId);
            ps.executeUpdate();
            
            ps = con.prepareStatement("UPDATE Employee SET SsnNo=?, StartDate=?, HourlyRate=?, EmpType=? WHERE UserId=?");
            ps.setString(1, ssnNumber);
            ps.setString(2, startDate);
            ps.setString(3, hourlyRate);
            ps.setString(4, empType);
            ps.setString(5, empId);
            ps.executeUpdate();
            
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Successfully updated",
                    "SQL Error"));
            
            return "manage_emp";
        } catch (SQLException ex) {
            Logger.getLogger(FactoryBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    "SQL Error"));
            return "";
        }
    }
}
