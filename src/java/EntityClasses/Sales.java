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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John Lynch
 */
@Entity
@Table(name = "sales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sales.findAll", query = "SELECT s FROM Sales s"),
    @NamedQuery(name = "Sales.findByTransactionId", query = "SELECT s FROM Sales s WHERE s.transactionId = :transactionId"),
    @NamedQuery(name = "Sales.findByTransactionDate", query = "SELECT s FROM Sales s WHERE s.transactionDate = :transactionDate"),
    @NamedQuery(name = "Sales.findByNoOfUnits", query = "SELECT s FROM Sales s WHERE s.noOfUnits = :noOfUnits")})
public class Sales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TransactionId")
    private Integer transactionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TransactionDate")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NoOfUnits")
    private int noOfUnits;
    @JoinColumn(name = "AdvId", referencedColumnName = "AdvId")
    @ManyToOne(optional = false)
    private Advertisements advId;
    @JoinColumn(name = "AccountNo", referencedColumnName = "AccountNo")
    @ManyToOne(optional = false)
    private Accounts accountNo;

    public Sales() {
    }

    public Sales(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Sales(Integer transactionId, Date transactionDate, int noOfUnits) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.noOfUnits = noOfUnits;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getNoOfUnits() {
        return noOfUnits;
    }

    public void setNoOfUnits(int noOfUnits) {
        this.noOfUnits = noOfUnits;
    }

    public Advertisements getAdvId() {
        return advId;
    }

    public void setAdvId(Advertisements advId) {
        this.advId = advId;
    }

    public Accounts getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Accounts accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sales)) {
            return false;
        }
        Sales other = (Sales) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Sales[ transactionId=" + transactionId + " ]";
    }
    
}
