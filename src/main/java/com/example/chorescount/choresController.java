package com.example.chorescount;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.action.Action;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class choresController implements Initializable {
    @FXML
    Statement stmt;
    @FXML
    private Button retourButton;
    @FXML
    private javafx.scene.control.MenuItem MenuItem;
    @FXML
    private ComboBox MenuButton;
    @FXML
    private TextField entertask;
    @FXML
    private TextField desxriptionchores;
    @FXML
    private static int nbChores;
    @FXML
    private String userName;
    @FXML
    private TableView<CatChoreTable> categoryTable;

    @FXML
    private TableColumn<CatChoreTable, String> colcatUser;
    @FXML
    private TableColumn<CatChoreTable, String> colcatChoreName;
    @FXML
    private TableColumn<CatChoreTable, String> colcatDone;

    ObservableList<CatChoreTable> oblist = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        settable();
        ObservableList<String> items = FXCollections.observableArrayList();
        MenuButton.setItems(items);
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String url_ = "jdbc:ucanaccess://C:\\Users\\matte\\OneDrive\\Documents\\Application\\Chorecountappli\\ChoresCountDB.accdb";
            Connection connection = DriverManager.getConnection(url_);
            stmt = connection.createStatement();

            String query = "SELECT Users.username\n" +
                    "FROM Users INNER JOIN (FlatSharing INNER JOIN Members ON FlatSharing.[Id_FlatSharing] = Members.[Id_FlatSharing]) ON Users.[username] = Members.[username]\n" +
                    "WHERE Id_FlatSharing = '" + Variables.FlatsharingID + "'";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                items.add(rs.getString("username"));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }
        MenuButton.setItems(items);
    }
    public void settable(){
        Statement stmt;
        int ntr=0;

        try {
            categoryTable.getItems().clear();
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println();

            //Installing Driver and pointing to the DB
            String link = "jdbc:ucanaccess://C:\\Users\\matte\\OneDrive\\Documents\\Application\\Chorecountappli\\ChoresCountDB.accdb";

            //Connecting to DB
            Connection connection = DriverManager.getConnection(link);
            stmt = connection.createStatement();

            String query = "SELECT username, chore_name, isAchieved\n" +
                    "FROM Chore_Category, Chore, Must_Do\n" +
                    "WHERE "+Variables.ChoresgategoryID+" = Chore.Id_Chore_Category\n" +
                    "AND Chore.Id_Chore = Must_Do.Id_Chore AND isAchieved = False;";
            ResultSet rset = stmt.executeQuery(query);

            while (rset.next()) {
                System.out.println(rset.getString("username"));
                oblist.add(new CatChoreTable( rset.getString("username"), rset.getString("chore_name"), rset.getString("isAchieved")));
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        colcatUser.setCellValueFactory(new PropertyValueFactory<>("username"));
        colcatChoreName.setCellValueFactory(new PropertyValueFactory<>("choreName"));
        colcatDone.setCellValueFactory(new PropertyValueFactory<>("done"));

        categoryTable.setItems(oblist);
    }

    public void retourButtonAction(ActionEvent event) throws Exception {
        DBUtils.changetexte(retourButton, "homepage.fxml");
    }


    public void submitButtonAction(ActionEvent event) {
        Statement stmt;
        try {

            String entertaskText = entertask.getText();
            String desxriptionchorestext=desxriptionchores.getText();

            //Contacting the driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println();

            //Installing Driver and pointing to the DB
            String url = "jdbc:ucanaccess://C:\\Users\\matte\\OneDrive\\Documents\\Application\\Chorecountappli\\ChoresCountDB.accdb";
            ChoresCreation.setNbChores();
            //Connecting to DB
            Connection connection = DriverManager.getConnection(url);
            stmt = connection.createStatement();
            String insertChores = "Insert into Chore values ('" + (ChoresCreation.nbChores+1) + "','" + entertaskText
                    + "', '" + desxriptionchorestext + "','" + false + "','" + Variables.ChoresgategoryID+"')";

            String insertmust_do = "Insert into Must_Do values ('" + MenuButton.getValue() + "','" + (ChoresCreation.nbChores+1)
                    +"')";
            stmt.execute(insertChores);
            stmt.execute(insertmust_do);
            categoryTable.getItems().clear();
            settable();
            JOptionPane.showMessageDialog(null, "Added");
            /*RegistrationMessageLabel.setVisible(true);*/
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}
