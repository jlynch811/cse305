/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John Lynch
 */
@Entity
@Table(name = "friends")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Friends.findAll", query = "SELECT f FROM Friends f"),
    @NamedQuery(name = "Friends.findByFriend1Id", query = "SELECT f FROM Friends f WHERE f.friendsPK.friend1Id = :friend1Id"),
    @NamedQuery(name = "Friends.findByFriend2Id", query = "SELECT f FROM Friends f WHERE f.friendsPK.friend2Id = :friend2Id"),
    @NamedQuery(name = "Friends.findByRequestStatus", query = "SELECT f FROM Friends f WHERE f.requestStatus = :requestStatus")})
public class Friends implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FriendsPK friendsPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "RequestStatus")
    private String requestStatus;
    @JoinColumn(name = "Friend1_Id", referencedColumnName = "UserId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "Friend2_Id", referencedColumnName = "UserId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users1;

    public Friends() {
    }

    public Friends(FriendsPK friendsPK) {
        this.friendsPK = friendsPK;
    }

    public Friends(FriendsPK friendsPK, String requestStatus) {
        this.friendsPK = friendsPK;
        this.requestStatus = requestStatus;
    }

    public Friends(int friend1Id, int friend2Id) {
        this.friendsPK = new FriendsPK(friend1Id, friend2Id);
    }

    public FriendsPK getFriendsPK() {
        return friendsPK;
    }

    public void setFriendsPK(FriendsPK friendsPK) {
        this.friendsPK = friendsPK;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Users getUsers1() {
        return users1;
    }

    public void setUsers1(Users users1) {
        this.users1 = users1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (friendsPK != null ? friendsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Friends)) {
            return false;
        }
        Friends other = (Friends) object;
        if ((this.friendsPK == null && other.friendsPK != null) || (this.friendsPK != null && !this.friendsPK.equals(other.friendsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Friends[ friendsPK=" + friendsPK + " ]";
    }
    
}
