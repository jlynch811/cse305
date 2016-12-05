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
        String q = "SELECT * FROM Pages, Users WHERE UserId = OwnerId AND PageId = " + this.pageId + ";";
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
        return "displaycomments";
    }
    
    public String like()
    {
        return "personalpage";
    }
    
    public String createNewPost()
    { 
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        Page userPage = (Page)session.getAttribute("userPage");
        
        String q = "INSERT INTO Posts(AuthorId, PageId, PostDate, PostContent) VALUES(" + userId + "," + userPage.getPageId() + ", CURDATE(), " + "\"" + postContent + "\")";
        JoinHelper j = new JoinHelper();
        j.insertQuery(q);
        
        return "personalpage";
    }
}
