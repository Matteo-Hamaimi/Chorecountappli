package com.example.chorescount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class choresController {
    @FXML
    private Button retourButton;

    public void retourButtonAction(ActionEvent event  ) throws Exception{
        DBUtils.changetexte(retourButton,"homepage.fxml");}

}
