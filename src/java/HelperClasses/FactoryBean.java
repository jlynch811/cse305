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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public void initGroups()
    {
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        
        ArrayList<Groups> myGroups = new ArrayList();
        JoinHelper j = new JoinHelper();
        String q = "SELECT * FROM Groups, Group_Members WHERE Groups.GroupId = Group_Members.GroupId AND MemberId =" + userId;
        
        Connection con = null;
        ResultSet rs = j.selectResultSetQuery(q, con);
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
        ResultSet rs = j.selectResultSetQuery(q, con);
        
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
        String q = "SELECT * FROM Groups, Group_Members g WHERE Groups.GroupId = g.GroupId AND NOT EXISTS (SELECT * FROM Group_Members g2 WHERE g2.GroupId = g.GroupId AND g2.MemberId =" + userId +")";
        
        Connection con = null;
        ResultSet rs = j.selectResultSetQuery(q, con);
        
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
        
        String q = "SELECT DISTINCT UserId, EmailId, Psswd, FirstName, LastName, Address, City, State, Zipcode, Telephone, UserType \n" +
"FROM Group_Members g, Users u \n" +
"WHERE g.MemberId = u.UserId AND NOT EXISTS (SELECT * FROM Group_Members g2 WHERE g2.MemberId =  u.UserId AND g2.GroupId = " + currentGroup.getGroupId() + ")";
        
        JoinHelper j = new JoinHelper();
        
        Connection con = null;
        ResultSet rs = j.selectResultSetQuery(q, con);
        ResultSet temp;
        
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
        ResultSet rs = j.selectResultSetQuery(q, con);
        
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
        ResultSet rs = j.selectResultSetQuery(q, con);
        
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
        ResultSet rs = j.selectResultSetQuery(q, con);
        
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
    
}
