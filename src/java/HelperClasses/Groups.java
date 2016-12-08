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
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author John Lynch
 */
@Named(value = "groups")
@SessionScoped
public class Groups implements Serializable{
    
    private String groupId;
    private String groupName;
    private String groupType;
    private String ownerId;
    private String userToAdd;
    private String ownerName;

    /**
     * Creates a new instance of Groups
     */
    public Groups(String groupId, String groupName, String groupType, String ownerId) {
        
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupType = groupType;
        this.ownerId = ownerId;
        
        FactoryBean b = new FactoryBean();
        
        Users owner = b.getUserFromId(ownerId);
        
        if(owner==null)
        {
            b.getUserFromId(ownerId);
            System.out.println("NullOwner");
        }
        this.ownerName = owner.getFirstName() + " " + owner.getLastName();
        
    }
    
    public Groups()
    {
        
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getUserToAdd() {
        return userToAdd;
    }

    public void setUserToAdd(String userToAdd) {
        this.userToAdd = userToAdd;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    
    
    
    
    public String displayGroupPage()
    {
        
        JoinHelper j = new JoinHelper();
        String q = "SELECT * FROM Pages WHERE GroupId = "+groupId;
        String id=j.selectQuery(q, "PageId");
        
        HttpSession session = SessionUtils.getSession();
        
        session.setAttribute("displayedGroupPage", id);
        session.setAttribute("currentPage", id);
        
        session.setAttribute("currentGroup", this);
        return "displaygrouppage";
    }
    
    
    
    public String createGroup()
    {
        
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        
        Connection con = null;
        PreparedStatement ps = null;
        
        
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("INSERT INTO Groups(GroupName, GroupType, OwnerId)\n" +
"VALUES(?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, groupName);
            ps.setString(2, groupType);
            ps.setInt(3, Integer.parseInt(userId));
            
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            
            if(keys.next())
                groupId = keys.getInt(1) +"";
            
            ps = con.prepareStatement("INSERT INTO Group_Members(MemberId, GroupId, MemberType)\n" +
"VALUES(?, ?, \"Owner\");");
            ps.setString(1, userId);
            ps.setString(2, groupId);
            
            ps.executeUpdate();
            
            ps = con.prepareStatement("INSERT INTO Pages(PageType, OwnerId, GroupId, PostCount) VALUES(\"Group\", NULL, ?, 0)");
            ps.setString(1, groupId);
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Groups error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
        
        return "groups";
      
    }
    
    public boolean amOwner()
    {
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        if(ownerId.equals(userId))
            {
                return true;
            }
        return false;
    }
    
    public void deleteSelf()
    {
        JoinHelper j = new JoinHelper();
        
        String q = "DELETE FROM Groups WHERE GroupId = " + groupId;
        j.deleteQuery(q);
        //String q = "DELETE FROM Comments, Posts, "
        //q = "DELETE FROM Groups WHERE GroupId = " + groupId;
        //j.deleteQuery(q);
        
    }
    
    public void leave()
    {
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        JoinHelper j = new JoinHelper();
        
        String q = "DELETE FROM Group_Members WHERE GroupId = " + groupId + " AND MemberId = " + userId;
        j.deleteQuery(q);
        //String q = "DELETE FROM Comments, Posts, "
        //q = "DELETE FROM Groups WHERE GroupId = " + groupId;
        //j.deleteQuery(q);
        
    }
    
    public String joinGroup()
    {
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        
        JoinHelper j = new JoinHelper();
        String q = "INSERT INTO Group_Members(MemberId, GroupId, MemberType) VALUES(" + userId + ", \"" + groupId + "\", \"Member\")";
        
        j.insertQuery(q);
        return "groups";
    }
    
    public void addUser()
    {
        JoinHelper j = new JoinHelper();
        String q = "INSERT INTO Group_Members(MemberId, GroupId, MemberType) VALUES(" + userToAdd + ", \"" + groupId + "\", \"Member\")";
        
        j.insertQuery(q);
    }
    
    public void removeUser()
    {
        JoinHelper j = new JoinHelper();
        String q = "DELETE FROM Group_Members WHERE MemberId = " + userToAdd + " AND GroupId = " + groupId;
        
        j.deleteQuery(q);
    }
    
    public void rename()
    {
        String q = "UPDATE Groups SET GroupName = \"" + groupName +"\" " + "WHERE Groupid = " + groupId;
        JoinHelper j = new JoinHelper();
        
        j.insertQuery(q);
    }
}
