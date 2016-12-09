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
@Named(value = "revenue")
@SessionScoped
public class Revenue implements Serializable {
    
    String advId, empId, advType, advDate, company, itemName, price, custId, accountNo, txId, txDate, unitsSold, profit;

    /**
     * Creates a new instance of Revenue
     */
    
    public Revenue() {
        
    }
    
    public Revenue(String advId, String empId, String advType, String advDate, String company, String itemName, String price, String custId, String accountNo, String txId, String txDate, String unitsSold, String profit) {
        this.advId = advId;
        this.empId = empId;
        this.advType = advType;
        this.advDate = advDate;
        this.company = company;
        this.itemName = itemName;
        this.price = price;
        this.custId = custId;
        this.accountNo = accountNo;
        this.txId = txId;
        this.txDate = txDate;
        this.unitsSold = unitsSold;
        this.profit = profit;
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

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getTxDate() {
        return txDate;
    }

    public void setTxDate(String txDate) {
        this.txDate = txDate;
    }

    public String getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(String unitsSold) {
        this.unitsSold = unitsSold;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

}
