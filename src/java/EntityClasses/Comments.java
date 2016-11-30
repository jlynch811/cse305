/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John Lynch
 */
@Entity
@Table(name = "comments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c"),
    @NamedQuery(name = "Comments.findByCommentId", query = "SELECT c FROM Comments c WHERE c.commentId = :commentId"),
    @NamedQuery(name = "Comments.findByCmntDate", query = "SELECT c FROM Comments c WHERE c.cmntDate = :cmntDate"),
    @NamedQuery(name = "Comments.findByCmntContent", query = "SELECT c FROM Comments c WHERE c.cmntContent = :cmntContent"),
    @NamedQuery(name = "Comments.findByLikeCount", query = "SELECT c FROM Comments c WHERE c.likeCount = :likeCount")})
public class Comments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CommentId")
    private Integer commentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CmntDate")
    @Temporal(TemporalType.DATE)
    private Date cmntDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "CmntContent")
    private String cmntContent;
    @Column(name = "LikeCount")
    private Integer likeCount;
    @JoinColumn(name = "AuthorId", referencedColumnName = "UserId")
    @ManyToOne
    private Users authorId;
    @JoinColumn(name = "PostId", referencedColumnName = "PostId")
    @ManyToOne(optional = false)
    private Posts postId;

    public Comments() {
    }

    public Comments(Integer commentId) {
        this.commentId = commentId;
    }

    public Comments(Integer commentId, Date cmntDate, String cmntContent) {
        this.commentId = commentId;
        this.cmntDate = cmntDate;
        this.cmntContent = cmntContent;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Date getCmntDate() {
        return cmntDate;
    }

    public void setCmntDate(Date cmntDate) {
        this.cmntDate = cmntDate;
    }

    public String getCmntContent() {
        return cmntContent;
    }

    public void setCmntContent(String cmntContent) {
        this.cmntContent = cmntContent;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Users getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Users authorId) {
        this.authorId = authorId;
    }

    public Posts getPostId() {
        return postId;
    }

    public void setPostId(Posts postId) {
        this.postId = postId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentId != null ? commentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.commentId == null && other.commentId != null) || (this.commentId != null && !this.commentId.equals(other.commentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Comments[ commentId=" + commentId + " ]";
    }
    
}
