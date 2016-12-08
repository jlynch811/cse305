/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author John Lynch
 */
public class ResultSetCon {
    
    public ResultSet rs;
    public Connection con;

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    public ResultSetCon(ResultSet rs, Connection con)
    {
        this.rs = rs;
        this.con = con;
    }
}
