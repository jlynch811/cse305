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
@Named(value = "item")
@SessionScoped
public class Item implements Serializable {

    String itemName, itemType, company, price, unitsAvailable;
    
    /**
     * Creates a new instance of Item
     */
    public Item() {
    }
    
    public Item(String companyName) {
        this.itemName = null;
        this.itemType = null;
        this.company = companyName;
        this.price = null;
        this.unitsAvailable = null;
    }
    
    public Item(String itemName, String itemType, String price, String units) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.company = null;
        this.price = price;
        this.unitsAvailable = units;
    }
    
    public Item(String itemName, String itemtype) {
        this.itemName = itemName;
        this.itemType = itemtype;
        this.company = null;
        this.price = null;
        this.unitsAvailable = null;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnitsAvailable() {
        return unitsAvailable;
    }

    public void setUnitsAvailable(String unitsAvailable) {
        this.unitsAvailable = unitsAvailable;
    }
    
}
