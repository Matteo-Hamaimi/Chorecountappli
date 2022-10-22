package com.example.chorescount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class homeController {

    @FXML
    private Button kitchenButton;
    @FXML
    private Button restroomButton;
    @FXML
    private Button garageButton;
    @FXML
    private Button bathroomButton;
    @FXML
    private Button otherButton;
    @FXML
    private Button retourButton;

    public void retourButtonAction(ActionEvent event  ) throws Exception{
        DBUtils.changetexte(retourButton,"Welcome.fxml");}

    public void kitchenButtonAction(ActionEvent event  ) throws Exception{
        Variables.ChoresgategoryID=1;
        DBUtils.changetexte(kitchenButton,"kitchen.fxml");
        }

    public void otherButtonAction(ActionEvent event  ) throws Exception{
        Variables.ChoresgategoryID=5;
        DBUtils.changetexte(otherButton,"other.fxml");
        }

    public void restroomButtonAction(ActionEvent event  ) {
        Variables.ChoresgategoryID=2;
        DBUtils.changetexte(restroomButton,"restrooms.fxml");
        }

    public void bathroomButtonAction(ActionEvent event  ) {
        Variables.ChoresgategoryID=4;
        DBUtils.changetexte(bathroomButton,"bathroom.fxml");}

    public void garageButtonAction(ActionEvent event  ) {
        Variables.ChoresgategoryID=3;
        DBUtils.changetexte(garageButton,"garage.fxml");


}}
