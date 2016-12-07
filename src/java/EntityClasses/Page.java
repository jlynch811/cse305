/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import HelperClasses.JoinHelper;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpSession;
import HelperClasses.Posts;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
/**
 *
 * @author John Lynch
 */
@Named(value = "page")
@SessionScoped
public class Page implements Serializable{
    
    private ArrayList<Posts> posts = new ArrayList();
    private String pageId;
    private String pageType;
    private String ownerId;
    private String groupId;
    private String postCount;
    private String ownerName;
    
    private Posts displayedPost;
    
    public Page(String pageId, String pageType, String ownerId, String groupId, String postCount) {
        this.pageId = pageId;
        this.pageType = pageType;
        this.ownerId = ownerId;
        this.groupId = groupId;
        this.postCount = postCount;
    }
    
    public Page() {
    }

    public ArrayList<Posts> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Posts> posts) {
        this.posts = posts;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Posts getDisplayedPost() {
        return displayedPost;
    }

    public void setDisplayedPost(Posts displayedPost) {
        this.displayedPost = displayedPost;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPostCount() {
        return postCount;
    }

    public void setPostCount(String postCount) {
        this.postCount = postCount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    public String initPersonalPage(){
        posts = new ArrayList();
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        
        Connection con = null;
        PreparedStatement ps = null;
        
        //Set the personal page ID
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("SELECT * FROM Pages WHERE OwnerId = ?;");
            ps.setString(1, userId);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                pageId = rs.getString("PageId");
                pageType = rs.getString("PageType");
                ownerId = rs.getString("OwnerId");
                groupId = rs.getString("GroupId");
                postCount = rs.getString("PostCount");
                initOwnerName();
            }
            
            ps = con.prepareStatement("SELECT * FROM Posts WHERE PageId = ? ORDER BY PostDate DESC;");
            ps.setString(1, pageId);

            rs = ps.executeQuery();
            
           while (rs.next()) {
                String id = rs.getString("PostId");
                String authorId = rs.getString("AuthorId");
                String pageId = rs.getString("PageId");
                String postDate = rs.getString("PostDate");
                String postContent = rs.getString("PostContent");
                String cmntCount = rs.getString("CmntCount");
                String likeCount = rs.getString("LikeCount");
                
                Posts post = new Posts(id, authorId, pageId, postDate, postContent, cmntCount, likeCount);
                posts.add(post);
            }
           
           session.setAttribute("userPage", this);
           session.setAttribute("displayedPage", this);
        } catch (SQLException ex) {
            System.out.println("Page Init error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
        
        return "personalpage";
    }
    
    public String initGroupPage(){
        posts = new ArrayList();
        HttpSession session = SessionUtils.getSession();
        String displayedGroupPage = (String)session.getAttribute("displayedGroupPage");
        
        Connection con = null;
        PreparedStatement ps = null;
        
        //Set the personal page ID
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("SELECT * FROM Pages WHERE PageId = ?;");
            ps.setString(1, displayedGroupPage);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                pageId = rs.getString("PageId");
                pageType = rs.getString("PageType");
                ownerId = rs.getString("OwnerId");
                groupId = rs.getString("GroupId");
                postCount = rs.getString("PostCount");
                initOwnerNameGroup();
            }
            
            ps = con.prepareStatement("SELECT * FROM Posts WHERE PageId = ? ORDER BY PostDate DESC;");
            ps.setString(1, pageId);

            rs = ps.executeQuery();
            
           while (rs.next()) {
                String id = rs.getString("PostId");
                String authorId = rs.getString("AuthorId");
                String pageId = rs.getString("PageId");
                String postDate = rs.getString("PostDate");
                String postContent = rs.getString("PostContent");
                String cmntCount = rs.getString("CmntCount");
                String likeCount = rs.getString("LikeCount");
                
                Posts post = new Posts(id, authorId, pageId, postDate, postContent, cmntCount, likeCount);
                posts.add(post);
            }
           
           session.setAttribute("groupPage", this);
           session.setAttribute("displayedPage", this);
        } catch (SQLException ex) {
            System.out.println("Page Init error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
        
        return "displaygrouppage";
    }
    
    public String initOtherPage(){
        posts = new ArrayList();
        HttpSession session = SessionUtils.getSession();
        
        
        Connection con = null;
        PreparedStatement ps = null;
        
        //Set the personal page ID
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("SELECT * FROM Pages WHERE OwnerId = ?;");
            ps.setString(1, ownerId);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                pageId = rs.getString("PageId");
                pageType = rs.getString("PageType");
                ownerId = rs.getString("OwnerId");
                groupId = rs.getString("GroupId");
                postCount = rs.getString("PostCount");
                initOwnerName();
            }
            
            ps = con.prepareStatement("SELECT * FROM Posts WHERE PageId = ? ORDER BY PostDate DESC;");
            ps.setString(1, pageId);

            rs = ps.executeQuery();
            
           while (rs.next()) {
                String id = rs.getString("PostId");
                String authorId = rs.getString("AuthorId");
                String pageId = rs.getString("PageId");
                String postDate = rs.getString("PostDate");
                String postContent = rs.getString("PostContent");
                String cmntCount = rs.getString("CmntCount");
                String likeCount = rs.getString("LikeCount");
                
                Posts post = new Posts(id, authorId, pageId, postDate, postContent, cmntCount, likeCount);
                posts.add(post);
            }
           
           session.setAttribute("displayedPage", this);
        } catch (SQLException ex) {
            System.out.println("Page Init error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
        
        return "otherpersonalpage";
    }
    
    public void refactorPage(){
        posts = new ArrayList();
        HttpSession session = SessionUtils.getSession();
        Page displayedPage = (Page)session.getAttribute("displayedPage");
        
        Connection con = null;
        PreparedStatement ps = null;
        
        //Set the personal page ID
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("SELECT * FROM Pages WHERE PageId = ?;");
            ps.setString(1, displayedPage.getPageId());
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                pageId = rs.getString("PageId");
            }
            
            ps = con.prepareStatement("SELECT * FROM Posts WHERE PageId = ? ORDER BY PostDate DESC;");
            ps.setString(1, pageId);

            rs = ps.executeQuery();
            
           while (rs.next()) {
                String id = rs.getString("PostId");
                String authorId = rs.getString("AuthorId");
                String pageId = rs.getString("PageId");
                String postDate = rs.getString("PostDate");
                String postContent = rs.getString("PostContent");
                String cmntCount = rs.getString("CmntCount");
                String likeCount = rs.getString("LikeCount");
                
                Posts post = new Posts(id, authorId, pageId, postDate, postContent, cmntCount, likeCount);
                posts.add(post);
            }
           
           session.setAttribute("displayedPage", this);
        } catch (SQLException ex) {
            System.out.println("Page Init error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
    }
    
    public boolean amOwner()
    {
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        if(ownerId.equals(userId))
            {
                return true;
            }
        
        String q = "SELECT UserId FROM Users, Group_Members WHERE UserId = MemberId AND MemberType = \"Owner\" AND PageId = " + pageId;
        
        JoinHelper j = new JoinHelper();
        
        String groupOwner = j.selectQuery(q, null);
        
        if(groupOwner!=null && groupOwner.equals(ownerId))
            return true;
        return false;
    }
    
    public void initOwnerName()
    {
        Connection con = null;
        PreparedStatement ps = null;
        
        //Set the personal page ID
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("SELECT * FROM Users WHERE UserId = " + ownerId);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                ownerName = rs.getString("FirstName") + " " + rs.getString("LastName");
            }
            
        } catch (SQLException ex) {
            System.out.println("Page Init error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
    }
    
    public void initOwnerNameGroup()
    {
        Connection con = null;
        PreparedStatement ps = null;
        
        //Set the personal page IDa
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("SELECT * FROM Groups WHERE GroupId = " + groupId);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                ownerName = rs.getString("GroupName");
            }
            
        } catch (SQLException ex) {
            System.out.println("Page Init error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
    }
}
    

