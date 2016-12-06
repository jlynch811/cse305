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
        String q = "SELECT * FROM Groups WHERE OwnerId = " + userId;
        
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
    
}
