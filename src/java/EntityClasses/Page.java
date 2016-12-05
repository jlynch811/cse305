/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpSession;
import HelperClasses.Posts;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author John Lynch
 */
@Named(value = "page")
@RequestScoped
public class Page {
    
    private ArrayList<Posts> posts = new ArrayList();
    private String pageId;
    
    
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
    
    
    
    public String initPersonalPage(){
        HttpSession session = SessionUtils.getSession();
        String userId = (String)session.getAttribute("userid");
        
        Connection con = null;
        PreparedStatement ps = null;
        
        
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("SELECT * FROM Pages WHERE OwnerId = ?;");
            ps.setString(1, userId);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                pageId = rs.getString("PageId");
            }
            
            ps = con.prepareStatement("SELECT * FROM Posts WHERE PageId = ?;");
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
        } catch (SQLException ex) {
            System.out.println("Page Init error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
        
        return "personalpage";
    }
}
    

