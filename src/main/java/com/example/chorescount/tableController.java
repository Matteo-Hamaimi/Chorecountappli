package com.example.chorescount;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class tableController implements Initializable {

    @FXML
    private TableView<ModelTable> choreTable;
    @FXML
    private TableColumn<ModelTable, String> colIdChore;
    @FXML
    private TableColumn<ModelTable, String> colUsername;
    @FXML
    private TableColumn<ModelTable, String> colChoreName;
    @FXML
    private TableColumn<ModelTable, String> colCategory;
    @FXML
    private TableColumn<ModelTable, String> colDescription;
    @FXML
    private TableColumn<ModelTable, String> colDone;
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableinit();
    }

    @FXML
    private TextField IdChoreTextField;
    @FXML
    private Label IdErrorLabel;
    public void tableinit(){
        Statement stmt;

        try {

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println();

            //Installing Driver and pointing to the DB
            String link = "jdbc:ucanaccess://C:\\Users\\matte\\OneDrive\\Documents\\Application\\Chorecountappli\\ChoresCountDB.accdb";

            //Connecting to DB
            Connection connection = DriverManager.getConnection(link);
            stmt = connection.createStatement();

            String query = "SELECT Chore.Id_Chore, username, chore_name, name_category, chore_description, isAchieved\n" +
                    "FROM Chore_Category, Chore, Must_Do\n" +
                    "WHERE Chore_Category.Id_Chore_Category = Chore.Id_Chore_Category\n" +
                    "AND Chore.Id_Chore = Must_Do.Id_Chore AND isAchieved = False;";
            ResultSet rset = stmt.executeQuery(query);

            while (rset.next()) {
                oblist.add(new ModelTable(rset.getString("Id_Chore"), rset.getString("username"),
                        rset.getString("chore_name"), rset.getString("name_category"),
                        rset.getString("chore_description"), rset.getString("isAchieved")));
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        colIdChore.setCellValueFactory(new PropertyValueFactory<>("idChore"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colChoreName.setCellValueFactory(new PropertyValueFactory<>("choreName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDone.setCellValueFactory(new PropertyValueFactory<>("done"));

        choreTable.setItems(oblist);

    }

    public void deleteChoreButtonOnAction(ActionEvent actionEvent) {
        int IDChore = Integer.parseInt(IdChoreTextField.getText());
        Boolean str;
        Statement stmt;
        try{

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            //Installing Driver and pointing to the DB
            String link = "jdbc:ucanaccess://C:\\Users\\matte\\OneDrive\\Documents\\Application\\Chorecountappli\\ChoresCountDB.accdb";
            //Connecting to DB
            Connection connection = DriverManager.getConnection(link);
            stmt = connection.createStatement();
            String query = "SELECT ID_Chore FROM Chore;";
            /*String query2="UPDATE isAchieved=TRUE FROM CHORE WHERE Id_Chore="+IDChore+"";*/
            ResultSet rset = stmt.executeQuery(query);
            /*ResultSet rset1 = stmt.executeQuery(query2);*/
            IdErrorLabel.setVisible(true);
            while (rset.next()) {
                if (Integer.parseInt(rset.getString("Id_Chore"))==IDChore){
                    IdErrorLabel.setVisible(false);
                    String query2= "UPDATE Chore SET isAchieved = true WHERE ID_Chore="+IDChore+";";
                    stmt.execute(query2);
                    choreTable.getItems().clear();
                    tableinit();
                }
            };
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }



    }
}
