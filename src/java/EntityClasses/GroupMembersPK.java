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
public class GroupMembersPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "MemberId")
    private int memberId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GroupId")
    private int groupId;

    public GroupMembersPK() {
    }

    public GroupMembersPK(int memberId, int groupId) {
        this.memberId = memberId;
        this.groupId = groupId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) memberId;
        hash += (int) groupId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupMembersPK)) {
            return false;
        }
        GroupMembersPK other = (GroupMembersPK) object;
        if (this.memberId != other.memberId) {
            return false;
        }
        if (this.groupId != other.groupId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.GroupMembersPK[ memberId=" + memberId + ", groupId=" + groupId + " ]";
    }
    
}
