/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author John Lynch
 */
@Entity
@Table(name = "posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p"),
    @NamedQuery(name = "Posts.findByPostId", query = "SELECT p FROM Posts p WHERE p.postId = :postId"),
    @NamedQuery(name = "Posts.findByPostDate", query = "SELECT p FROM Posts p WHERE p.postDate = :postDate"),
    @NamedQuery(name = "Posts.findByPostContent", query = "SELECT p FROM Posts p WHERE p.postContent = :postContent"),
    @NamedQuery(name = "Posts.findByCmntCount", query = "SELECT p FROM Posts p WHERE p.cmntCount = :cmntCount"),
    @NamedQuery(name = "Posts.findByLikeCount", query = "SELECT p FROM Posts p WHERE p.likeCount = :likeCount")})
public class Posts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PostId")
    private Integer postId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PostDate")
    @Temporal(TemporalType.DATE)
    private Date postDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "PostContent")
    private String postContent;
    @Column(name = "CmntCount")
    private Integer cmntCount;
    @Column(name = "LikeCount")
    private Integer likeCount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postId")
    private Collection<Comments> commentsCollection;
    @JoinColumn(name = "AuthorId", referencedColumnName = "UserId")
    @ManyToOne
    private Users authorId;
    @JoinColumn(name = "PageId", referencedColumnName = "PageId")
    @ManyToOne
    private Pages pageId;

    public Posts() {
    }

    public Posts(Integer postId) {
        this.postId = postId;
    }

    public Posts(Integer postId, Date postDate, String postContent) {
        this.postId = postId;
        this.postDate = postDate;
        this.postContent = postContent;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Integer getCmntCount() {
        return cmntCount;
    }

    public void setCmntCount(Integer cmntCount) {
        this.cmntCount = cmntCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    public Users getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Users authorId) {
        this.authorId = authorId;
    }

    public Pages getPageId() {
        return pageId;
    }

    public void setPageId(Pages pageId) {
        this.pageId = pageId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postId != null ? postId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.postId == null && other.postId != null) || (this.postId != null && !this.postId.equals(other.postId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Posts[ postId=" + postId + " ]";
    }
    
}
