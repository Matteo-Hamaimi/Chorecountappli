package com.example.chorescount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginController {
    public static boolean logged;

    @FXML
    private Button cancelButton;

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Label loginMessageLabel;

    @FXML
    private Button loginButton;
    public void loginButtonOnAction(ActionEvent e){
        if(!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            validateLogin();
        }
        else{
            loginMessageLabel.setText("Please enter username and password");
        }
    }


    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private TextField usernameTextField;

    private Statement stmt;


    public void validateLogin(){
        try{
            //Contacting the driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            //JOptionPane.showMessageDialog(null, "Driver Loaded");

            //Installing Driver and pointing to the DB
            String url = "jdbc:ucanaccess://C:\\Users\\Mrics\\Documents\\ChoresCountDB.accdb";

            //Connecting to DB
            Connection connection = DriverManager.getConnection(url);
            //JOptionPane.showMessageDialog(null, "Database Connected");
            stmt = connection.createStatement();
            String verifyLogin = "SELECT count(1) from users where username = '" + usernameTextField.getText()
                    + "' and password = '" +passwordPasswordField.getText() +"'";
            ResultSet queryResult = stmt.executeQuery(verifyLogin);
            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    logged = true;
                }else{
                    logged = false;
                }
            }
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }
    }
}
