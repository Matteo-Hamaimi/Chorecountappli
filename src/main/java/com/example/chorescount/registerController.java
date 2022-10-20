package com.example.chorescount;

import javafx.application.Platform;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class registerController implements Initializable{
    @FXML
    private Button CloseButton;
    @FXML
    private ImageView SignUpImageView;
    @FXML
    private Button BackButton;
    @FXML
    private ImageView LogoImageView;

    @FXML
    private PasswordField PasswordTextField, ConfirmPasswordTextField;
    @FXML
    private TextField UsernameTextField, FirstnameTextField, LastnameTextField;

    @FXML
    private Label RegistrationMessageLabel, ConfirmPasswordLabel;

    @FXML
    private Button RegisterButton;


    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    public void registerButtonOnAction(ActionEvent event){
        ConfirmPasswordLabel.setVisible(false);
        RegistrationMessageLabel.setVisible(false);
        if(PasswordTextField.getText().equals(ConfirmPasswordTextField.getText())){
            registerUser();
        } else{
            ConfirmPasswordLabel.setVisible(true);
        }
    }

    public void backButtonOnAction(ActionEvent event ) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomeController.class.getResource("Welcome.fxml"));
        Stage loginStage= (Stage) BackButton.getScene().getWindow();
        loginStage.setTitle("ChoresCount");
        loginStage.setScene(new Scene(fxmlLoader.load(),600,400));
        loginStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle ){

        File SignupFile = new File("ChoresCount/registration_logo.png");
        Image SignupImage = new Image(SignupFile.toURI().toString());
        SignUpImageView.setImage(SignupImage);

        File LogoFile = new File("ChoresCount/Logo.png");
        Image LogoImage = new Image(LogoFile.toURI().toString());
        LogoImageView.setImage(LogoImage);

    }
    public void registerUser(){
        Statement stmt;
        try{

            String firstname = FirstnameTextField.getText();
            String lastname = LastnameTextField.getText();
            String username = UsernameTextField.getText();
            String password = PasswordTextField.getText();

            //Contacting the driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println();

            //Installing Driver and pointing to the DB
            String url = "jdbc:ucanaccess://C:\\Users\\grdan\\OneDrive\\Documents\\EFREI\\Etats-Unis\\Cours\\Java\\AppChoresCount\\Chorecountappli\\ChoresCountDB.accdb";

            //Connecting to DB
            Connection connection = DriverManager.getConnection(url);
            stmt = connection.createStatement();
            String insertRegister = "Insert into Users values ('"+ username + "','" + firstname
                    + "', '" + lastname +  "', '" + password + "')";
            stmt.execute(insertRegister);
            System.out.println("Added");
            RegistrationMessageLabel.setVisible(true);
        } catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

}
