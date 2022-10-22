package com.example.chorescount;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Variables {
    public static String FlatsharingName = "Irvine Semester Flatsharing";
    public static int FlatsharingID = 1;
    public static String UserConnected = "emerick_944";
    public static String url_db = "jdbc:ucanaccess://C:\\Users\\Mrics\\Documents\\ChoresCountDB.accdb";
    public static boolean logged;
    public static int ChoresgategoryID;

    public static String getFirstname(){
        Statement stmt;
        String res = "";
        try{

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String url = url_db;
            Connection connection = DriverManager.getConnection(url);
            stmt = connection.createStatement();
            String query = "SELECT Firstname from Users where username = '" + Variables.UserConnected + "'";
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next()){
                res = rset.getString(1);
            }
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }
        return res;
    }


    public static int getChoresgategory(){
        Statement stmt;
        int res=0;
        String res_str = "";
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String url = url_db;
            Connection connection = DriverManager.getConnection(url);
            stmt = connection.createStatement();
            String query = "SELECT Id_Chore_Category from Chore_Category where Flatsharing_name = '" + Variables.FlatsharingName + "'";
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next()){
                res_str = rset.getString(1);
            }
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }
        res = Integer.parseInt(res_str);
        FlatsharingID = res;
        return res;

    };


    public static int getFlatsharingID(){
        Statement stmt;
        int res=0;
        String res_str = "";
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String url = url_db;
            Connection connection = DriverManager.getConnection(url);
            stmt = connection.createStatement();
            String query = "SELECT Id_Flatsharing from Flatsharing where Flatsharing_name = '" + Variables.FlatsharingName + "'";
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next()){
                res_str = rset.getString(1);
            }
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }
        res = Integer.parseInt(res_str);
        FlatsharingID = res;
        return res;
    }

    public static void setFlatsharing(String name){
        FlatsharingName = name;
        getFlatsharingID();
    }
}