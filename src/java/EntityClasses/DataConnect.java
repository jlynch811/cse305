/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Tarun
 */
public class DataConnect {
    public static Connection getConnection() {
        try {
            System.out.println("Inside getConnection");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/cse305", "root", "root");
            if (con == null) {
                System.out.println("Connection null");
            }
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->"
                            + ex.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
            System.out.println("Exception in DataConnect.close()");
        }
    }
}
