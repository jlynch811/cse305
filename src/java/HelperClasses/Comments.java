/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

/**
 *
 * @author John Lynch
 */
public class Comments {
    
    public String id;
    public String authorId;
    public String postId;
    public String cmntDate;
    public String message;
    public String likeCount;
    
    public Comments(String id, String authorId,String postId,String cmntDate,String message,String likeCount)
    {
        this.id = id;
        this.authorId = authorId;
        this.postId = postId;
        this.cmntDate = cmntDate;
        this.message = message;
        this.likeCount = likeCount;
    }
    
}
