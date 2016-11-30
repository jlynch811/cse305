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
@Table(name = "advertisements")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Advertisements.findAll", query = "SELECT a FROM Advertisements a"),
    @NamedQuery(name = "Advertisements.findByAdvId", query = "SELECT a FROM Advertisements a WHERE a.advId = :advId"),
    @NamedQuery(name = "Advertisements.findByAdvType", query = "SELECT a FROM Advertisements a WHERE a.advType = :advType"),
    @NamedQuery(name = "Advertisements.findByAdvDate", query = "SELECT a FROM Advertisements a WHERE a.advDate = :advDate"),
    @NamedQuery(name = "Advertisements.findByCompany", query = "SELECT a FROM Advertisements a WHERE a.company = :company"),
    @NamedQuery(name = "Advertisements.findByItemName", query = "SELECT a FROM Advertisements a WHERE a.itemName = :itemName"),
    @NamedQuery(name = "Advertisements.findByPrice", query = "SELECT a FROM Advertisements a WHERE a.price = :price"),
    @NamedQuery(name = "Advertisements.findByContent", query = "SELECT a FROM Advertisements a WHERE a.content = :content"),
    @NamedQuery(name = "Advertisements.findByUnitsAvailable", query = "SELECT a FROM Advertisements a WHERE a.unitsAvailable = :unitsAvailable")})
public class Advertisements implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AdvId")
    private Integer advId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "AdvType")
    private String advType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AdvDate")
    @Temporal(TemporalType.DATE)
    private Date advDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Company")
    private String company;
    @Size(max = 60)
    @Column(name = "ItemName")
    private String itemName;
    @Column(name = "Price")
    private Integer price;
    @Size(max = 250)
    @Column(name = "Content")
    private String content;
    @Column(name = "UnitsAvailable")
    private Integer unitsAvailable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "advId")
    private Collection<Sales> salesCollection;
    @JoinColumn(name = "EmployeeId", referencedColumnName = "UserId")
    @ManyToOne
    private Users employeeId;

    public Advertisements() {
    }

    public Advertisements(Integer advId) {
        this.advId = advId;
    }

    public Advertisements(Integer advId, String advType, Date advDate, String company) {
        this.advId = advId;
        this.advType = advType;
        this.advDate = advDate;
        this.company = company;
    }

    public Integer getAdvId() {
        return advId;
    }

    public void setAdvId(Integer advId) {
        this.advId = advId;
    }

    public String getAdvType() {
        return advType;
    }

    public void setAdvType(String advType) {
        this.advType = advType;
    }

    public Date getAdvDate() {
        return advDate;
    }

    public void setAdvDate(Date advDate) {
        this.advDate = advDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUnitsAvailable() {
        return unitsAvailable;
    }

    public void setUnitsAvailable(Integer unitsAvailable) {
        this.unitsAvailable = unitsAvailable;
    }

    @XmlTransient
    public Collection<Sales> getSalesCollection() {
        return salesCollection;
    }

    public void setSalesCollection(Collection<Sales> salesCollection) {
        this.salesCollection = salesCollection;
    }

    public Users getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Users employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (advId != null ? advId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Advertisements)) {
            return false;
        }
        Advertisements other = (Advertisements) object;
        if ((this.advId == null && other.advId != null) || (this.advId != null && !this.advId.equals(other.advId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Advertisements[ advId=" + advId + " ]";
    }
    
}
