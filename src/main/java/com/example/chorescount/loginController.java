package com.example.chorescount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginController {

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
            //loginMessageLabel.setText("You try to login");
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

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) from users where username = '" + usernameTextField.getText()
                + "' and password = '" +passwordPasswordField.getText() +"'";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Welcome");
                }else{
                    loginMessageLabel.setText("Please try again");
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
