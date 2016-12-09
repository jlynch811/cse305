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

    String itemName, itemType;
    
    /**
     * Creates a new instance of Item
     */
    public Item() {
    }
    
    public Item(String itemName, String itemtype) {
        this.itemName = itemName;
        this.itemType = itemtype;
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
    
}
