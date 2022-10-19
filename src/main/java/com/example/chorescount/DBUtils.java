package com.example.chorescount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DBUtils {
    public static void changetexte (Button namebutton, String filefxml){
            FXMLLoader fxmlLoader = new FXMLLoader(WelcomeController.class.getResource(filefxml));
            Stage signupStage= (Stage) namebutton.getScene().getWindow();
            signupStage.setTitle("ChoresCount");
        try {
            signupStage.setScene(new Scene(fxmlLoader.load(),600,400));
        } catch (IOException e) {
            e.printStackTrace();
        }
        signupStage.show();}
    }

