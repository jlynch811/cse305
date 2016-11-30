/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John Lynch
 */
@Entity
@Table(name = "fmplususers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fmplususers.findAll", query = "SELECT f FROM Fmplususers f"),
    @NamedQuery(name = "Fmplususers.findByUserId", query = "SELECT f FROM Fmplususers f WHERE f.userId = :userId"),
    @NamedQuery(name = "Fmplususers.findByCreationDate", query = "SELECT f FROM Fmplususers f WHERE f.creationDate = :creationDate"),
    @NamedQuery(name = "Fmplususers.findByCreditCardNumber", query = "SELECT f FROM Fmplususers f WHERE f.creditCardNumber = :creditCardNumber"),
    @NamedQuery(name = "Fmplususers.findByRating", query = "SELECT f FROM Fmplususers f WHERE f.rating = :rating")})
public class Fmplususers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UserId")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreationDate")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "CreditCardNumber")
    private BigInteger creditCardNumber;
    @Column(name = "Rating")
    private Integer rating;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Users users;

    public Fmplususers() {
    }

    public Fmplususers(Integer userId) {
        this.userId = userId;
    }

    public Fmplususers(Integer userId, Date creationDate) {
        this.userId = userId;
        this.creationDate = creationDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigInteger getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(BigInteger creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fmplususers)) {
            return false;
        }
        Fmplususers other = (Fmplususers) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Fmplususers[ userId=" + userId + " ]";
    }
    
}
