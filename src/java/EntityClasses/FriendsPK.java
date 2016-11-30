/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author John Lynch
 */
@Embeddable
public class FriendsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Friend1_Id")
    private int friend1Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Friend2_Id")
    private int friend2Id;

    public FriendsPK() {
    }

    public FriendsPK(int friend1Id, int friend2Id) {
        this.friend1Id = friend1Id;
        this.friend2Id = friend2Id;
    }

    public int getFriend1Id() {
        return friend1Id;
    }

    public void setFriend1Id(int friend1Id) {
        this.friend1Id = friend1Id;
    }

    public int getFriend2Id() {
        return friend2Id;
    }

    public void setFriend2Id(int friend2Id) {
        this.friend2Id = friend2Id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) friend1Id;
        hash += (int) friend2Id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendsPK)) {
            return false;
        }
        FriendsPK other = (FriendsPK) object;
        if (this.friend1Id != other.friend1Id) {
            return false;
        }
        if (this.friend2Id != other.friend2Id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.FriendsPK[ friend1Id=" + friend1Id + ", friend2Id=" + friend2Id + " ]";
    }
    
}
