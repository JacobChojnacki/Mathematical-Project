package com.example.mnprojekt.register;

import com.example.mnprojekt.Connector.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class RegisterController{

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private Button rCancelButton;

    @FXML
    private PasswordField rPasswordTextField;

    @FXML
    private TextField rUsernameTextField;

    @FXML
    private Label registerMessageLabel;

    public void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) rCancelButton.getScene().getWindow();
        stage.close();
    }
    public void registerButtonAction(ActionEvent event) {
        registerInterface();
    }

    public void registerInterface(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String username = rUsernameTextField.getText();
        String password = rPasswordTextField.getText();

        String insertFields = "INSERT into useraccounts(firstname, lastname, username, password) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "')";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registerMessageLabel.setText("User has been registered");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


}
