package com.example.chorescount;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class WelcomeController  implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Button signButton;

    @FXML
    private ImageView LogoViewsImage;
    @FXML
    private ImageView LockViewsImage;

    @Override
    public void initialize(URL url,ResourceBundle resourceBundle ){
        File logoFile = new File("ChoresCount/Logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        LogoViewsImage.setImage(logoImage);

    }


    public void loginButtonAction(ActionEvent event ) throws Exception {
        DBUtils.changetexte(loginButton,"login.fxml");}
    

    public void signButtonAction(ActionEvent event  ) throws Exception{
        DBUtils.changetexte(signButton,"register.fxml");}


}