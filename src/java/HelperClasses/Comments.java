/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import EntityClasses.Page;
import EntityClasses.SessionUtils;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author John Lynch
 */
@Named(value = "comments")
@SessionScoped
public class Comments implements Serializable{
    
    private String id;
    private String authorId;
    private String postId;
    private String cmntDate;
    private String message;
    private String likeCount;
    private String authorName;
    
    public Comments()
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

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCmntDate() {
        return cmntDate;
    }

    public void setCmntDate(String cmntDate) {
        this.cmntDate = cmntDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
    
    
    
    public Comments(String id, String authorId,String postId,String cmntDate,String message,String likeCount)
    {
        this.id = id;
        this.authorId = authorId;
        this.postId = postId;
        this.cmntDate = cmntDate;
        this.message = message;
        this.likeCount = likeCount;
        
        JoinHelper j = new JoinHelper();
        String q = "SELECT * FROM Comments, Users WHERE UserId = AuthorId AND CommentId = " + id + ";";
        this.authorName =  j.selectQuery(q, "FirstName") + " ";
        this.authorName = this.authorName + j.selectQuery(q, "LastName");
    }  
    
    
    public void createNewComment()
    {
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        Posts currentPost = (Posts)session.getAttribute("displayedPost");
        
        String q = "INSERT INTO Comments(AuthorId, PostId, CmntDate, cmntContent) VALUES(" + userId + "," + currentPost.getId() + ", Now(), " + "\"" + message + "\")";
        JoinHelper j = new JoinHelper();
        j.insertQuery(q);
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
        String q = "DELETE FROM Comments WHERE CommentId = " + id;
        j.deleteQuery(q);
    }
    
    public String prepForModify()
    {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("displayedComment", this);
        return "modifycomment";
    }
    
    public String modifySelf()
    {
        
        String q = "UPDATE Comments SET CmntContent = \"" + message +"\" " + "WHERE CommentId = " + id;
        JoinHelper j = new JoinHelper();
        
        j.insertQuery(q);
        return "displaycomments";
    }
    
    public void like()
    {
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        
        String q = "INSERT INTO CommentLikes(UserId, CommentId) VALUES(" + userId + ", " + id + ")";
        JoinHelper j = new JoinHelper();
        j.insertQuery(q);
    }
    
    public void unLike()
    {
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        
        String q = "DELETE FROM CommentLikes WHERE UserId = " + userId + " AND CommentId = " + id;
        JoinHelper j = new JoinHelper();
        j.deleteQuery(q);
    }
    
    public boolean doILike()
    {
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        
        String q = "SELECT * FROM CommentLikes WHERE UserId = " + userId + " AND CommentId = " + id;
        
        JoinHelper j = new JoinHelper();
        String r = j.selectQuery(q,"UserId");
        
        if(r==null)
            return false;
        return true;
    }
    
    public int calcLikeCount()
    {
        String q = "SELECT COUNT(UserId) as count FROM CommentLikes WHERE CommentId = " + id;
        JoinHelper j = new JoinHelper();
        likeCount = j.selectQuery(q,"count");
        
        return Integer.parseInt(likeCount);
        
    }
}
