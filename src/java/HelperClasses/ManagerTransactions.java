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
    private String empType = "rep";
    
    
    private String month;
    private String year;

    private ArrayList<Sales> salesArr = new ArrayList<>();
    private ArrayList<Revenue> revenueArr = new ArrayList<>();
    
    private String txMode;
    private String txItemName;
    private String txUserId;
    
    private String revMode;
    private String revItemName;
    private String revUserName;
    private String revItemType;
    
    /**
     * Creates a new instance of ManagerTransactions
     */
    public ManagerTransactions() {
        empType = "rep";
        txMode = "itemName";
        revMode = "itemName";
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ArrayList<Sales> getSalesArr() {
        return salesArr;
    }

    public void setSalesArr(ArrayList<Sales> salesArr) {
        this.salesArr = salesArr;
    }

    public String getTxItemName() {
        return txItemName;
    }

    public void setTxItemName(String txItemName) {
        this.txItemName = txItemName;
    }

    public String getTxUserId() {
        return txUserId;
    }

    public void setTxUserId(String txUserId) {
        this.txUserId = txUserId;
    }

    public String getTxMode() {
        return txMode;
    }

    public void setTxMode(String txMode) {
        this.txMode = txMode;
    }

    public ArrayList<Revenue> getRevenueArr() {
        return revenueArr;
    }

    public void setRevenueArr(ArrayList<Revenue> revenueArr) {
        this.revenueArr = revenueArr;
    }
    
    public String getRevMode() {
        return revMode;
    }

    public void setRevMode(String revMode) {
        this.revMode = revMode;
    }

    public String getRevItemName() {
        return revItemName;
    }

    public void setRevItemName(String revItemName) {
        this.revItemName = revItemName;
    }

    public String getRevUserName() {
        return revUserName;
    }

    public void setRevUserName(String revUserName) {
        this.revUserName = revUserName;
    }

    public String getRevItemType() {
        return revItemType;
    }

    public void setRevItemType(String revItemType) {
        this.revItemType = revItemType;
    }
    
    public void initEmpUpdate () {
        System.out.println("Inside initEmpUpdate");
        
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM Users U, Employees E WHERE U.UserId = ? AND E.UserId = ?");
            ps.setString(1, empId);
            ps.setString(2, empId);
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
                empType = "rep";
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactoryBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    "SQL Error"));
        }
    }
    
    public String updateEmployeeInfo () {
        System.out.println("Inside updateEmployeeInfo");
                
        if (emailId==null || psswd==null || firstName==null
                || lastName==null || city==null || state==null
                || zipcode==null || telephone==null || ssnNumber==null || startDate==null) {
            System.out.println("Something not entered");
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "All fields marked with red asterisk are mandatory",
                    "Please enter all fields"));
                return "register_emp";
        }

        if (telephone.equals("")) {
            telephone = null;
        }
        
        if (hourlyRate.equals("")) {
            hourlyRate = null;
        }
        
        if (address.equals("")) {
            address = null;
        }
        
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
            
            String employeeType;
            if (empType.equals("rep")) {
                employeeType = "Representative";
            } else {
                employeeType = "Manager";
            }
            
            ps = con.prepareStatement("UPDATE Employees SET SsnNo=?, StartDate=?, HourlyRate=?, EmpType=? WHERE UserId=?");
            ps.setString(1, ssnNumber);
            ps.setString(2, startDate);
            ps.setString(3, hourlyRate);
            ps.setString(4, employeeType);
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
    
    public String populateSalesList () {
        System.out.println("Inside populateSalesList");
        HttpSession session = SessionUtils.getSession();
        salesArr = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement ps = null;
        
        String compare = year + "-" + month + "-%";
        
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from Sales where TransactionDate like ?");
            ps.setString(1, compare);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                String txId = rs.getString("TransactionId");
                String advId = rs.getString("AdvId");
                String accNo = rs.getString("AccountNo");
                String txDate = rs.getString("TransactionDate");
                String units = rs.getString("NoOfUnits");
                System.out.println("No Of Units : " + units);
                Sales sales = new Sales(txId, advId, accNo, txDate, units);
                salesArr.add(sales);
            }
            return "view_sales";
            
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
    
    public String filteredTxList () {
        System.out.println("Inside filteredTxList");
        
        salesArr = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ResultSet rs;
            if (txMode.equals("itemName")) {
                ps = con.prepareStatement("Select TransactionId, S.AdvId, AccountNo, TransactionDate, NoOfUnits from Sales S, Advertisements A where A.ItemName = ? and A.AdvId = S.AdvId");
                ps.setString(1, txItemName);
                rs = ps.executeQuery();
            } else {
                ps = con.prepareStatement("Select TransactionId, S.AdvId, S.AccountNo, TransactionDate, NoOfUnits from Sales S, Accounts A, Users U where U.UserId = ? and A.UserId = U.UserId and A.AccountNo = S.AccountNo;");
                ps.setString(1, txUserId);
                rs = ps.executeQuery();
            }

            while(rs.next())
            {
                String txId = rs.getString("TransactionId");
                String advId = rs.getString("AdvId");
                String accNo = rs.getString("AccountNo");
                String txDate = rs.getString("TransactionDate");
                String units = rs.getString("NoOfUnits");
                System.out.println("No Of Units : " + units);
                Sales sales = new Sales(txId, advId, accNo, txDate, units);
                salesArr.add(sales);
            }
            
            return "view_transactions";
            
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
    
    
    public String filteredRevenueList () {
        System.out.println("Inside filteredRevenueList");
        
        revenueArr.clear();
        
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DataConnect.getConnection();
            ResultSet rs;
            if (txMode.equals("itemName")) {
                ps = con.prepareStatement("Select AD.AdvId, EmployeeId, AdvType, AdvDate, Company, ItemName, Price, UserId, AC.AccountNo, TransactionId, TransactionDate, NoOfUnits, Price*NoOfUnits AS Revenue from Advertisements AD, Accounts AC, Sales S where AD.ItemName = ? and AD.AdvId = S.AdvId and AC.AccountNo = S.AccountNo");
                ps.setString(1, revItemName);
                rs = ps.executeQuery();
            } else if (txMode.equals("userName")) {
                ps = con.prepareStatement("Select AD.AdvId, EmployeeId, AdvType, AdvDate, Company, ItemName, Price, UserId, AC.AccountNo, TransactionId, TransactionDate, NoOfUnits, Price*NoOfUnits AS Revenue from Advertisements AD, Accounts AC, Sales S where AC.UserId = ? and AD.AdvId = S.AdvId and AC.AccountNo = S.AccountNo");
                ps.setString(1, revUserName);
                rs = ps.executeQuery();
            } else {
                ps = con.prepareStatement("Select AD.AdvId, EmployeeId, AdvType, AdvDate, Company, ItemName, Price, UserId, AC.AccountNo, TransactionId, TransactionDate, NoOfUnits, Price*NoOfUnits AS Revenue from Advertisements AD, Accounts AC, Sales S where AD.AdvType = ? and AD.AdvId = S.AdvId and AC.AccountNo = S.AccountNo");
                ps.setString(1, revItemType);
                rs = ps.executeQuery();            
            }

            while(rs.next())
            {
                String advId = rs.getString("AdvId");
                String emplId = rs.getString("EmployeeId");
                String advType = rs.getString("AdvType");
                String advDate = rs.getString("AdvDate");
                String company = rs.getString("Company");
                String itemName = rs.getString("ItemName");
                String price = rs.getString("Price");
                String userId = rs.getString("UserId");
                String accNo = rs.getString("AccountNo");
                String txId = rs.getString("TransactionId");
                String txDate = rs.getString("TransactionDate");
                String units = rs.getString("NoOfUnits");
                String profit = rs.getString("Revenue");

                System.out.println("Profit : " + profit);
                
                Revenue revenue = new Revenue(advId, emplId, advType, advDate, company, itemName, price, userId, accNo, txId, txDate, units, profit);
                revenueArr.add(revenue);
            }
            
            return "view_revenues";
            
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
