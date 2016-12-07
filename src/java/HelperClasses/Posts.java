/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import EntityClasses.DataConnect;
import EntityClasses.Page;
import EntityClasses.SessionUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author John Lynch
 */
@Named(value = "posts")
@SessionScoped
public class Posts implements Serializable{
    
    private String id;
    private String authorId;
    private String pageId;
    private String postDate;
    private String postContent;
    private String cmntCount;
    private String likeCount;
    private String authorName;
    private ArrayList<Comments> comments = new ArrayList();
    
    public Posts()
    {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getCmntCount() {
        return cmntCount;
    }

    public void setCmntCount(String cmntCount) {
        this.cmntCount = cmntCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public ArrayList<Comments> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comments> comments) {
        this.comments = comments;
    }
    
    
    
    public Posts(String id, String authorId, String pageId, String postDate, String postContent, String cmntCount, String likeCount)
    {
        this.id = id;
        this.authorId = authorId;
        this.pageId = pageId;
        this.postDate = postDate;
        this.postContent = postContent;
        this.cmntCount = cmntCount;
        this.likeCount = likeCount;
        
        JoinHelper j = new JoinHelper();
        String q = "SELECT * FROM Posts, Users WHERE UserId = AuthorId AND PostId = " + id + ";";
        this.authorName =  j.selectQuery(q, "FirstName") + " ";
        this.authorName = this.authorName + j.selectQuery(q, "LastName");
        
        
        q = "SELECT * FROM Comments WHERE PostId = " + this.id + ";";
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement(q);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String cid = rs.getString("CommentId");
                String cauthorId = rs.getString("AuthorId");
                String cpostId = rs.getString("PostId");
                String cmntDate = rs.getString("CmntDate");
                String cmessage = rs.getString("CmntContent");
                String clikeCount = rs.getString("LikeCount");
                
                Comments comment = new Comments(cid, cauthorId, cpostId, cmntDate, cmessage, clikeCount);
                this.comments.add(comment);
            }
        } catch (SQLException ex) {
            System.out.println("Post Init error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
    }
    
    public String displayPostComments()
    {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("displayedPost", this);
        session.setAttribute("currentPost", this);
        return "displaycomments";
    }
    
    public String like()
    {
        return "personalpage";
    }
    
    public void createNewPost()
    { 
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        Page userPage = (Page)session.getAttribute("displayedPage");
        
        String q = "INSERT INTO Posts(AuthorId, PageId, PostDate, PostContent) VALUES(" + userId + "," + userPage.getPageId() + ", CURDATE(), " + "\"" + postContent + "\")";
        JoinHelper j = new JoinHelper();
        j.insertQuery(q);
        
    }
    
    public void createNewGroupPost()
    { 
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        Page page = (Page)session.getAttribute("displayedGroupPage");
        
        String q = "INSERT INTO Posts(AuthorId, PageId, PostDate, PostContent) VALUES(" + userId + "," + page.getPageId() + ", CURDATE(), " + "\"" + postContent + "\")";
        JoinHelper j = new JoinHelper();
        j.insertQuery(q);
        
    }
    
    public void refactorPost()
    {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DataConnect.getConnection();
            
            
            ps = con.prepareStatement("SELECT * FROM Posts WHERE PostId = ? ORDER BY PostDate DESC;");
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            
           while (rs.next()) {
                String nid = rs.getString("PostId");
                String nauthorId = rs.getString("AuthorId");
                String npageId = rs.getString("PageId");
                String npostDate = rs.getString("PostDate");
                String npostContent = rs.getString("PostContent");
                String ncmntCount = rs.getString("CmntCount");
                String nlikeCount = rs.getString("LikeCount");
                
                Posts refactoredPost = new Posts(nid, nauthorId, npageId, npostDate, npostContent, ncmntCount, nlikeCount);
             this.id = nid;
             this.authorId = nauthorId;
             this.pageId = npageId;
             this.postDate = npostDate;
             this.postContent = npostContent;
             this.cmntCount = ncmntCount;
             this.likeCount = nlikeCount;
             
             this.comments = refactoredPost.getComments();
            }
           
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
        if(authorId.equals(userId))
            {
                return true;
            }
        return false;
    }
    
    public void deleteSelf()
    {
        JoinHelper j = new JoinHelper();
        String q = "DELETE FROM Posts WHERE PostId = " + id;
        j.deleteQuery(q);
    }
    
    public String prepForModify(String pageToGoTo)
    {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("displayedPost", this);
        session.setAttribute("pageToGoTo", pageToGoTo);
        return "modifypost";
    }
    
    public String modifySelf()
    {
        HttpSession session = SessionUtils.getSession();
        JoinHelper j = new JoinHelper();
        String q = "UPDATE Posts SET PostContent = \"" + postContent +"\" " + "WHERE PostId = " + id;
        j.insertQuery(q);
        return (String)session.getAttribute("pageToGoTo");
    }
    
    
    
    
}
