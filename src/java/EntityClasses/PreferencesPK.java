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
import javax.validation.constraints.Size;

/**
 *
 * @author John Lynch
 */
@Embeddable
public class PreferencesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "UserId")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "PrefCategory")
    private String prefCategory;

    public PreferencesPK() {
    }

    public PreferencesPK(int userId, String prefCategory) {
        this.userId = userId;
        this.prefCategory = prefCategory;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPrefCategory() {
        return prefCategory;
    }

    public void setPrefCategory(String prefCategory) {
        this.prefCategory = prefCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (prefCategory != null ? prefCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreferencesPK)) {
            return false;
        }
        PreferencesPK other = (PreferencesPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if ((this.prefCategory == null && other.prefCategory != null) || (this.prefCategory != null && !this.prefCategory.equals(other.prefCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.PreferencesPK[ userId=" + userId + ", prefCategory=" + prefCategory + " ]";
    }
    
}
