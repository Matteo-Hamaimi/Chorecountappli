package com.example.chorescount;


import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FlatsharingEdition implements Initializable{

    public int getFlatsharingID(){
        int res=0;
        String res_str = "";
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String url = "jdbc:ucanaccess://C:\\Users\\Mrics\\Documents\\ChoresCountDB.accdb";
            Connection connection = DriverManager.getConnection(url);
            stmt = connection.createStatement();
            String query = "SELECT Id_Flatsharing from Flatsharing where Flatsharing_name = '" + Variables.FlatsharingName + "'";
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next()){
                res_str = rset.getString(1);
            }
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }
        res = Integer.parseInt(res_str);
        return res;
    }

    @FXML
    private Button btnAdd;
    public void btnAddOnAction(ActionEvent e){
        System.out.println(getFlatsharingID());
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String url_ = "jdbc:ucanaccess://C:\\Users\\Mrics\\Documents\\ChoresCountDB.accdb";
            Connection connection = DriverManager.getConnection(url_);
            stmt = connection.createStatement();

            String verifyUsername = "SELECT count(1) from users where username = '" + txtUsername.getText() + "'";
            ResultSet queryResult = stmt.executeQuery(verifyUsername);



            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    table.getItems().clear();
                    stmt.execute("INSERT INTO Members values ('" + txtUsername.getText() + "', " + getFlatsharingID() + ")");

                    String query = "SELECT Users.username, Users.Firstname, Users.Lastname\n" +
                            "FROM Users INNER JOIN (FlatSharing INNER JOIN Members ON FlatSharing.[Id_FlatSharing] = Members.[Id_FlatSharing]) ON Users.[username] = Members.[username]\n" +
                            "WHERE Flatsharing_name = '" + Variables.FlatsharingName + "'";

                    ResultSet rs = stmt.executeQuery(query);

                    while(rs.next()){
                        oblist.add(new User(rs.getString("username"), rs.getString("firstname"), rs.getString("lastname")));
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Wrong Username");
                }
            }
        }catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));


        txtUsername.setText("");
        table.setItems(oblist);
    }

    @FXML
    private Button btnDelete;
    public void btnDeleteOnAction(ActionEvent e){
        System.out.println(getFlatsharingID());
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String url_ = "jdbc:ucanaccess://C:\\Users\\Mrics\\Documents\\ChoresCountDB.accdb";
            Connection connection = DriverManager.getConnection(url_);
            stmt = connection.createStatement();

            String verifyUsername = "SELECT count(1) from members where (username = '" + txtUsername.getText() + "' AND " +
                    "Id_Flatsharing = '" + getFlatsharingID() + "')";
            ResultSet queryResult = stmt.executeQuery(verifyUsername);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    table.getItems().clear();
                    stmt.execute("DELETE FROM Members where username = '" + txtUsername.getText() + "'");

                    String query = "SELECT Users.username, Users.Firstname, Users.Lastname\n" +
                            "FROM Users INNER JOIN (FlatSharing INNER JOIN Members ON FlatSharing.[Id_FlatSharing] = Members.[Id_FlatSharing]) ON Users.[username] = Members.[username]\n" +
                            "WHERE Flatsharing_name = '" + Variables.FlatsharingName + "'";

                    ResultSet rs = stmt.executeQuery(query);

                    while(rs.next()){
                        oblist.add(new User(rs.getString("username"), rs.getString("firstname"), rs.getString("lastname")));
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Wrong Username");
                }
            }
        }catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        table.setItems(oblist);
        txtUsername.setText("");
    }

    @FXML
    private Label txtFlatsharingName;
    @FXML
    private TextField txtUsername;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> firstnameColumn;
    @FXML
    private TableColumn<User, String> lastnameColumn;

    Statement stmt;
    public FlatsharingEdition(){

    }

    ObservableList<User> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtFlatsharingName.setText(Variables.FlatsharingName);
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String url_ = "jdbc:ucanaccess://C:\\Users\\Mrics\\Documents\\ChoresCountDB.accdb";
            Connection connection = DriverManager.getConnection(url_);
            stmt = connection.createStatement();

            String query = "SELECT Users.username, Users.Firstname, Users.Lastname\n" +
                    "FROM Users INNER JOIN (FlatSharing INNER JOIN Members ON FlatSharing.[Id_FlatSharing] = Members.[Id_FlatSharing]) ON Users.[username] = Members.[username]\n" +
                    "WHERE Flatsharing_name = '" + Variables.FlatsharingName + "'";

            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                oblist.add(new User(rs.getString("username"), rs.getString("firstname"), rs.getString("lastname")));
            }
        }catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        table.setItems(oblist);
    }
}
