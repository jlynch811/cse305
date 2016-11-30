/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John Lynch
 */
@Entity
@Table(name = "preferences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preferences.findAll", query = "SELECT p FROM Preferences p"),
    @NamedQuery(name = "Preferences.findByUserId", query = "SELECT p FROM Preferences p WHERE p.preferencesPK.userId = :userId"),
    @NamedQuery(name = "Preferences.findByPrefCategory", query = "SELECT p FROM Preferences p WHERE p.preferencesPK.prefCategory = :prefCategory")})
public class Preferences implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PreferencesPK preferencesPK;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Preferences() {
    }

    public Preferences(PreferencesPK preferencesPK) {
        this.preferencesPK = preferencesPK;
    }

    public Preferences(int userId, String prefCategory) {
        this.preferencesPK = new PreferencesPK(userId, prefCategory);
    }

    public PreferencesPK getPreferencesPK() {
        return preferencesPK;
    }

    public void setPreferencesPK(PreferencesPK preferencesPK) {
        this.preferencesPK = preferencesPK;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preferencesPK != null ? preferencesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preferences)) {
            return false;
        }
        Preferences other = (Preferences) object;
        if ((this.preferencesPK == null && other.preferencesPK != null) || (this.preferencesPK != null && !this.preferencesPK.equals(other.preferencesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Preferences[ preferencesPK=" + preferencesPK + " ]";
    }
    
}
