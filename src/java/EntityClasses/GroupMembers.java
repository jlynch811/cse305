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
@Table(name = "group_members")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupMembers.findAll", query = "SELECT g FROM GroupMembers g"),
    @NamedQuery(name = "GroupMembers.findByMemberId", query = "SELECT g FROM GroupMembers g WHERE g.groupMembersPK.memberId = :memberId"),
    @NamedQuery(name = "GroupMembers.findByGroupId", query = "SELECT g FROM GroupMembers g WHERE g.groupMembersPK.groupId = :groupId"),
    @NamedQuery(name = "GroupMembers.findByMemberType", query = "SELECT g FROM GroupMembers g WHERE g.memberType = :memberType")})
public class GroupMembers implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GroupMembersPK groupMembersPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "MemberType")
    private String memberType;
    @JoinColumn(name = "MemberId", referencedColumnName = "UserId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "GroupId", referencedColumnName = "GroupId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Groups groups;

    public GroupMembers() {
    }

    public GroupMembers(GroupMembersPK groupMembersPK) {
        this.groupMembersPK = groupMembersPK;
    }

    public GroupMembers(GroupMembersPK groupMembersPK, String memberType) {
        this.groupMembersPK = groupMembersPK;
        this.memberType = memberType;
    }

    public GroupMembers(int memberId, int groupId) {
        this.groupMembersPK = new GroupMembersPK(memberId, groupId);
    }

    public GroupMembersPK getGroupMembersPK() {
        return groupMembersPK;
    }

    public void setGroupMembersPK(GroupMembersPK groupMembersPK) {
        this.groupMembersPK = groupMembersPK;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupMembersPK != null ? groupMembersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupMembers)) {
            return false;
        }
        GroupMembers other = (GroupMembers) object;
        if ((this.groupMembersPK == null && other.groupMembersPK != null) || (this.groupMembersPK != null && !this.groupMembersPK.equals(other.groupMembersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.GroupMembers[ groupMembersPK=" + groupMembersPK + " ]";
    }
    
}
