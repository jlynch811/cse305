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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John Lynch
 */
@Entity
@Table(name = "employees")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e"),
    @NamedQuery(name = "Employees.findByUserId", query = "SELECT e FROM Employees e WHERE e.userId = :userId"),
    @NamedQuery(name = "Employees.findBySsnNo", query = "SELECT e FROM Employees e WHERE e.ssnNo = :ssnNo"),
    @NamedQuery(name = "Employees.findByStartDate", query = "SELECT e FROM Employees e WHERE e.startDate = :startDate"),
    @NamedQuery(name = "Employees.findByHourlyRate", query = "SELECT e FROM Employees e WHERE e.hourlyRate = :hourlyRate"),
    @NamedQuery(name = "Employees.findByEmpType", query = "SELECT e FROM Employees e WHERE e.empType = :empType")})
public class Employees implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UserId")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SsnNo")
    private int ssnNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "HourlyRate")
    private Integer hourlyRate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "EmpType")
    private String empType;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Users users;

    public Employees() {
    }

    public Employees(Integer userId) {
        this.userId = userId;
    }

    public Employees(Integer userId, int ssnNo, Date startDate, String empType) {
        this.userId = userId;
        this.ssnNo = ssnNo;
        this.startDate = startDate;
        this.empType = empType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getSsnNo() {
        return ssnNo;
    }

    public void setSsnNo(int ssnNo) {
        this.ssnNo = ssnNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Integer hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
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
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Employees[ userId=" + userId + " ]";
    }
    
}
