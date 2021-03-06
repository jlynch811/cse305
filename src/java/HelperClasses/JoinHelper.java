/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import EntityClasses.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author John Lynch
 */
public class JoinHelper {
    
    public JoinHelper(){
    }
    
    public ResultSetCon selectResultSetQuery(String q, Connection con)
    {
        PreparedStatement ps = null;
        
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement(q);
           
            ResultSet rs = ps.executeQuery();
            ResultSetCon rsc = new ResultSetCon(rs, con);
            return rsc;
            
        } catch (Exception ex) {
            System.out.println("Join error -->" + ex.getMessage());
        } finally {
            
        }     
        return null;
    }
    
    public String selectQuery(String q,String arg)
    {
        Connection con = null;
        PreparedStatement ps = null;
        
        
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement(q);
           
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return rs.getString(arg);
            }
            
        } catch (Exception ex) {
            System.out.println("Join error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
        return null;
    }
    
    public ArrayList<String> selectMultipleQuery(String q,String arg)
    {
        Connection con = null;
        PreparedStatement ps = null;
        
        ArrayList<String> ret = new ArrayList();
        
        
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement(q);
           
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                ret.add(rs.getString(arg));
            }
            return ret;
            
        } catch (Exception ex) {
            System.out.println("Join error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
        return null;
    }
    
    public void insertQuery(String q)
    {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement(q);
           
            ps.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("Insert error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
    }
    
    public void deleteQuery(String q)
    {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement(q);
           
            ps.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("Insert error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
    }
    
    public ArrayList<String> joinQuery(String result, String table1, String table2, String join1, String join2, String and)
    {
        ArrayList<String> returnList = new ArrayList();
        
        Connection con = null;
        PreparedStatement ps = null;
        
        
        try {
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("SELECT DISTINCT ? FROM ?, ? WHERE ? = ? ?;");
            ps.setString(1, result);
            ps.setString(2, table1);
            ps.setString(3, table2);
            ps.setString(4, join1);
            ps.setString(5, join2);
            ps.setString(6, and); 
            ResultSet rs = ps.executeQuery();
            
           while (rs.next()) {
                String res = rs.getString(result);
                returnList.add(res);
                
            }
        } catch (Exception ex) {
            System.out.println("Join error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }     
        
        return returnList;
    }
}
