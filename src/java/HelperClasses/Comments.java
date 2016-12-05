/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

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
    
    public Comments(String id, String authorId,String postId,String cmntDate,String message,String likeCount)
    {
        this.id = id;
        this.authorId = authorId;
        this.postId = postId;
        this.cmntDate = cmntDate;
        this.message = message;
        this.likeCount = likeCount;
    }  
    
    public String like()
    {
        return "displaycomments";
    }
    
}
