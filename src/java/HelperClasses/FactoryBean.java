/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import EntityClasses.DataConnect;
import EntityClasses.SessionUtils;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author John Lynch
 */
@Named(value = "factorybean")
@SessionScoped
public class FactoryBean implements Serializable {

    /**
     * Creates a new instance of FactoryBean
     */
    public FactoryBean() {
    }
    
    
    public Users getUserFromId(String uid)
    {
        String q = "SELECT * FROM Users WHERE UserId = " + uid;
        JoinHelper j = new JoinHelper();
        
        Connection con = null;
        
                try{
            ResultSetCon temp = j.selectResultSetQuery(q, con);
            ResultSet rs = temp.getRs();
            con = temp.getCon();
            
            if(rs.next())
            {
                String userId = rs.getString("UserId");
                String emailId = rs.getString("EmailId");
                String psswd = rs.getString("Psswd");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String state = rs.getString("State");
                String zipcode = rs.getString("Zipcode");
                String telephone = rs.getString("Telephone");
                String userType = rs.getString("UserType");
                
                Users user = new Users(userId, emailId, psswd, firstName, lastName, address, city, state, zipcode, telephone, userType);
                return user;
            }
            
        }
        
        
        catch (Exception ex) {
            System.out.println("Init UsersNotInCurrentGroup error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
                
                return null;
    }
    
    public void initGroups()
    {
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        
        ArrayList<Groups> myGroups = new ArrayList();
        JoinHelper j = new JoinHelper();
        String q = "SELECT * FROM Groups, Group_Members WHERE Groups.GroupId = Group_Members.GroupId AND MemberId =" + userId;
        
        Connection con = null;
        ResultSetCon temp = j.selectResultSetQuery(q, con);
        ResultSet rs = temp.getRs();
        con = temp.getCon();
        initAllGroupList();
        initNotMyGroupsList();
        
        try{
            while(rs.next())
            {
                String groupId = rs.getString("GroupId");
                String groupName = rs.getString("GroupName");
                String groupType = rs.getString("GroupType");
                String ownerId = rs.getString("OwnerId");
                
                Groups group = new Groups(groupId, groupName, groupType, ownerId);
                myGroups.add(group);
            }
            session.setAttribute("myGroups", myGroups);
        }
        
        
        catch (SQLException ex) {
            System.out.println("Join error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
        
    }
    
    public void initAllGroupList()
    {
        ArrayList<Groups> allGroups = new ArrayList();
        HttpSession session = SessionUtils.getSession();

        JoinHelper j = new JoinHelper();
        String q = "SELECT * FROM Groups";
        
        Connection con = null;
        ResultSetCon temp = j.selectResultSetQuery(q, con);
        ResultSet rs = temp.getRs();
        con = temp.getCon();
        
                try{
            while(rs.next())
            {
                String groupId = rs.getString("GroupId");
                String groupName = rs.getString("GroupName");
                String groupType = rs.getString("GroupType");
                String ownerId = rs.getString("OwnerId");
                
                Groups group = new Groups(groupId, groupName, groupType, ownerId);
                allGroups.add(group);
            }
            session.setAttribute("allGroups", allGroups);
        }
        
        
        catch (SQLException ex) {
            System.out.println("Init Groups error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

    }
    
    public void initNotMyGroupsList()
    {
        ArrayList<Groups> notMyGroups = new ArrayList();
        HttpSession session = SessionUtils.getSession();

        JoinHelper j = new JoinHelper();
        
        String userId = (String)session.getAttribute("userid");
        String q = "SELECT * FROM Groups g WHERE NOT EXISTS (SELECT * FROM Group_Members g2 WHERE g2.GroupId = g.GroupId AND g2.MemberId = " + userId +")";
        
        Connection con = null;
        ResultSetCon temp = j.selectResultSetQuery(q, con);
        ResultSet rs = temp.getRs();
        con = temp.getCon();
        
                try{
            while(rs.next())
            {
                String groupId = rs.getString("GroupId");
                String groupName = rs.getString("GroupName");
                String groupType = rs.getString("GroupType");
                String ownerId = rs.getString("OwnerId");
                
                Groups group = new Groups(groupId, groupName, groupType, ownerId);
                notMyGroups.add(group);
            }
            session.setAttribute("notMyGroups", notMyGroups);
        }
        
        
        catch (SQLException ex) {
            System.out.println("Init Groups error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

    }
    
    public void initGroupLists()
    {
        initNotInGroupList();
        initInGroupList();
    }
    
    public void initNotInGroupList()
    {
        ArrayList<Users> usersNotInCurrentGroup = new ArrayList();
        HttpSession session = SessionUtils.getSession();
        
        Groups currentGroup = (Groups)session.getAttribute("currentGroup");
        
        /*String q = "SELECT DISTINCT UserId, EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType \n" +
"FROM Group_Members g, Users u \n" +
"WHERE g.MemberId = u.UserId AND NOT EXISTS (SELECT * FROM Group_Members g2 WHERE g2.MemberId =  u.UserId AND g2.GroupId = " + currentGroup.getGroupId() + ")";*/
        
        String q = "SELECT DISTINCT UserId, EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType \n" +
"FROM Users u \n" +
"WHERE NOT EXISTS (SELECT * FROM Group_Members g2, Users u2 WHERE g2.MemberId =  u.UserId AND g2.GroupId = " + currentGroup.getGroupId() + ")";
        
        JoinHelper j = new JoinHelper();
        
        Connection con = null;
        ResultSetCon tempr = j.selectResultSetQuery(q, con);
        ResultSet rs = tempr.getRs();
        con = tempr.getCon();
        
                try{
            while(rs.next())
            {
                String userId = rs.getString("UserId");
                String emailId = rs.getString("EmailId");
                String psswd = rs.getString("Psswd");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String state = rs.getString("State");
                String zipcode = rs.getString("Zipcode");
                String telephone = rs.getString("Telephone");
                String userType = rs.getString("UserType");
                
                Users user = new Users(userId, emailId, psswd, firstName, lastName, address, city, state, zipcode, telephone, userType);
                usersNotInCurrentGroup.add(user);
            }
            session.setAttribute("usersNotInCurrentGroup", usersNotInCurrentGroup);
        }
        
        
        catch (SQLException ex) {
            System.out.println("Init UsersNotInCurrentGroup error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
    }
    
    public void initInGroupList()
    {
        ArrayList<Users> usersInCurrentGroup = new ArrayList();
        HttpSession session = SessionUtils.getSession();
        
        Groups currentGroup = (Groups)session.getAttribute("currentGroup");
        String myUserId = (String)session.getAttribute("userid");
        
        String q = "SELECT DISTINCT UserId, EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType \n" +
                "FROM Users, Group_Members WHERE Users.UserId = Group_Members.MemberId AND Group_Members.GroupId = " + currentGroup.getGroupId() + " AND UserId <> " + myUserId;
        
        JoinHelper j = new JoinHelper();
        
        Connection con = null;
        ResultSetCon temp = j.selectResultSetQuery(q, con);
        ResultSet rs = temp.getRs();
        con = temp.getCon();
        
                try{
            while(rs.next())
            {
                String userId = rs.getString("UserId");
                String emailId = rs.getString("EmailId");
                String psswd = rs.getString("Psswd");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String state = rs.getString("State");
                String zipcode = rs.getString("Zipcode");
                String telephone = rs.getString("Telephone");
                String userType = rs.getString("UserType");
                
                Users user = new Users(userId, emailId, psswd, firstName, lastName, address, city, state, zipcode, telephone, userType);
                usersInCurrentGroup.add(user);
            }
            session.setAttribute("usersInCurrentGroup", usersInCurrentGroup);
        }
        
        
        catch (SQLException ex) {
            System.out.println("Init UsersInCurrentGroup error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
    }
    
    
    public void initUsersList()
    {
        ArrayList<Users> usersList = new ArrayList();
        HttpSession session = SessionUtils.getSession();
        
        String q = "SELECT * FROM Users";
        
        JoinHelper j = new JoinHelper();
        
        Connection con = null;
        ResultSetCon temp = j.selectResultSetQuery(q, con);
        ResultSet rs = temp.getRs();
        con = temp.getCon();
        
                try{
            while(rs.next())
            {
                String userId = rs.getString("UserId");
                String emailId = rs.getString("EmailId");
                String psswd = rs.getString("Psswd");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String state = rs.getString("State");
                String zipcode = rs.getString("Zipcode");
                String telephone = rs.getString("Telephone");
                String userType = rs.getString("UserType");
                
                Users user = new Users(userId, emailId, psswd, firstName, lastName, address, city, state, zipcode, telephone, userType);
                usersList.add(user);
            }
            session.setAttribute("usersList", usersList);
        }
        
        
        catch (SQLException ex) {
            System.out.println("Init UsersList error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
    }
    
    public void initUsersNotMe()
    {
        ArrayList<Users> usersNotMeList = new ArrayList();
        HttpSession session = SessionUtils.getSession();
        String myUserId = (String)session.getAttribute("userid");
        
        String q = "SELECT * FROM Users WHERE UserId <> " + myUserId;
        
        JoinHelper j = new JoinHelper();
        
        Connection con = null;
        ResultSetCon temp = j.selectResultSetQuery(q, con);
        ResultSet rs = temp.getRs();
        con = temp.getCon();
        
                try{
            while(rs.next())
            {
                String userId = rs.getString("UserId");
                String emailId = rs.getString("EmailId");
                String psswd = rs.getString("Psswd");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String state = rs.getString("State");
                String zipcode = rs.getString("Zipcode");
                String telephone = rs.getString("Telephone");
                String userType = rs.getString("UserType");
                
                Users user = new Users(userId, emailId, psswd, firstName, lastName, address, city, state, zipcode, telephone, userType);
                usersNotMeList.add(user);
            }
            session.setAttribute("usersNotMeList", usersNotMeList);
        }
        
        
        catch (SQLException ex) {
            System.out.println("Init UsersList error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
    }
    
    public void initRepresentatives() {
        ArrayList<Employees> representativeList = new ArrayList();
        HttpSession session = SessionUtils.getSession();

        System.out.println("Inside initRepresentatives");
        
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM Users U, Employees E WHERE U.UserId = E.UserId AND E.EmpType = \"Representative\"");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                String userId = rs.getString("UserId");
                String emailId = rs.getString("EmailId");
                String psswd = rs.getString("Psswd");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String state = rs.getString("State");
                String zipcode = rs.getString("Zipcode");
                String telephone = rs.getString("Telephone");
                String userType = rs.getString("UserType");
                String ssnNumber = rs.getString("SsnNo");
                String startDate = rs.getString("StartDate");
                String hourlyRate = rs.getString("HourlyRate");
                String empType = rs.getString("EmpType");
                
                Employees emp = new Employees(userId, emailId, psswd, firstName, lastName, address, city, state, zipcode, telephone, userType, ssnNumber, startDate, hourlyRate, empType);
                representativeList.add(emp);
            }
            session.setAttribute("representativeList", representativeList);
        } catch (SQLException ex) {
            Logger.getLogger(FactoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
