/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Tarun
 */
@Named(value = "advertisement")
@SessionScoped
public class Advertisement implements Serializable {
        
    String advId, empId, advType, advDate, company, itemName, price, content, unitsAv;

    /**
     * Creates a new instance of Advertisement
     */
    public Advertisement() {
        
    }
    
    public Advertisement (String advId, String empId, String advType, String advDate, String company, String itemName, String price, String content, String unitsAv) {
        this.advId = advId;
        this.empId = empId;
        this.advType = advType;
        this.advDate = advDate;
        this.company = company;
        this.itemName = itemName;
        this.price = price;
        this.content = content;
        this.unitsAv = unitsAv;
    }

    public String getAdvId() {
        return advId;
    }

    public void setAdvId(String advId) {
        this.advId = advId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getAdvType() {
        return advType;
    }

    public void setAdvType(String advType) {
        this.advType = advType;
    }

    public String getAdvDate() {
        return advDate;
    }

    public void setAdvDate(String advDate) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUnitsAv() {
        return unitsAv;
    }

    public void setUnitsAv(String unitsAv) {
        this.unitsAv = unitsAv;
    }
    
}
