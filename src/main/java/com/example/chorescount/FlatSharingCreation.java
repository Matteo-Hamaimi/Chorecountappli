package com.example.chorescount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FlatSharingCreation {
    public static int nbFlatsharing;
    private Statement stmt;

    public static void setNbFlatsharing() {
        try{
            Statement stmt;
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String url = "jdbc:ucanaccess://C:\\Users\\Mrics\\Documents\\ChoresCountDB.accdb";
            Connection connection = DriverManager.getConnection(url);
            stmt = connection.createStatement();
            String query = "SELECT COUNT(*) AS result FROM Flatsharing";
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next()) {
                //read data from the actual row
                //automatically will try to forward 1 row
                nbFlatsharing = Integer.parseInt(rset.getString("result"));
            }


        } catch (Exception ex)
        {
            System.out.println("ERROR " + ex.toString());
        }
    }

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtFlatsharingName;

    @FXML
    private TextField txtState;

    @FXML
    private TextField txtZipcode;

    @FXML
    private Button btnSubmit;
    public void SubmitButtonOnAction(ActionEvent e){
        if(!txtFlatsharingName.getText().isBlank()){
            AddFlatSharing();
        }
        else{
            JOptionPane.showMessageDialog(null, "Please enter a name");
        }
    }

    @FXML
    private Button btnClose;
    public void btnCloseOnAction(ActionEvent e){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }



    public void AddFlatSharing(){
        try{
            //Contacting the driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println();

            //Installing Driver and pointing to the DB
            String url = "jdbc:ucanaccess://C:\\Users\\Mrics\\Documents\\ChoresCountDB.accdb";

            //Connecting to DB
            Connection connection = DriverManager.getConnection(url);
            setNbFlatsharing();
            stmt = connection.createStatement();
            String addFlatsharing = "Insert into FlatSharing values ('"+ (FlatSharingCreation.nbFlatsharing + 1) + "','" + txtFlatsharingName.getText()
                    + "', '" + txtAddress.getText() +  "', '" + txtCity.getText() + "', '" + txtState.getText() + "', '" + txtZipcode.getText() + "')";
            stmt.execute(addFlatsharing);
            System.out.println("Added");
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }
    }

}