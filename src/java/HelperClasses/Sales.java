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
@Named(value = "sales")
@SessionScoped
public class Sales implements Serializable {
                
    private String TxId, AdvId, AccountNo, TransactionDate, NoOfUnits;
    /**
     * Creates a new instance of Sales
     */
    public Sales() {
    }
    
    public Sales (String TxId, String AdvId, String AccountNo, String TransactionDate, String NoOfUnits) {
        this.TxId = TxId;
        this.AdvId = AdvId;
        this.AccountNo = AccountNo;
        this.TransactionDate = TransactionDate;
        this.NoOfUnits = NoOfUnits;
    }

    public String getTxId() {
        return TxId;
    }

    public void setTxId(String TxId) {
        this.TxId = TxId;
    }

    public String getAdvId() {
        return AdvId;
    }

    public void setAdvId(String AdvId) {
        this.AdvId = AdvId;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(String TransactionDate) {
        this.TransactionDate = TransactionDate;
    }

    public String getNoOfUnits() {
        return NoOfUnits;
    }

    public void setNoOfUnits(String NoOfUnits) {
        this.NoOfUnits = NoOfUnits;
    }
    
    
}
