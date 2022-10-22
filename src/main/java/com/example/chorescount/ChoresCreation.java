package com.example.chorescount;

import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChoresCreation {
    @FXML
    public static int nbChores;
    public static void setNbChores() {
        try{
            Statement stmt;
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String url = "jdbc:ucanaccess://C:\\Users\\matte\\OneDrive\\Documents\\Application\\Chorecountappli\\ChoresCountDB.accdb";
            Connection connection = DriverManager.getConnection(url);
            stmt = connection.createStatement();
            String query = "SELECT COUNT(*) AS result FROM Chore";
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next()) {
                //read data from the actual row
                //automatically will try to forward 1 row
                nbChores = Integer.parseInt(rset.getString("result"));
            }

        } catch (Exception ex)
        {
            System.out.println("ERROR " + ex.toString());
        }
    }


}
