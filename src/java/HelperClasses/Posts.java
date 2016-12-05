/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author John Lynch
 */
@Named(value = "posts")
@RequestScoped
public class Posts {
    
    private String id;
    private String authorId;
    private String pageId;
    private String postDate;
    private String postContent;
    private String cmntCount;
    private String likeCount;
    private String authorName;
    
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
    
    
    
    public Posts(String id, String authorId, String pageId, String postDate, String postContent, String cmntCount, String likeCount)
    {
        this.id = id;
        this.authorId = authorId;
        this.pageId = pageId;
        this.postDate = postDate;
        this.postContent = postContent;
        this.cmntCount = cmntCount;
        this.likeCount = likeCount;
        
        //JoinHelper j = new JoinHelper();
        //this.authorName = j.joinQuery("FirstName", "Posts", "Users", "UserId", "OwnerId", "AND  PostId = " + id).get(0);
    }
    
}
