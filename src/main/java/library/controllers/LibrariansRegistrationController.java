package library.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.exceptions.UsernameAlreadyExistsException;
import library.exceptions.UsernameFieldEmptyException;
import library.exceptions.PasswordFieldEmptyException;
import library.services.UserService;

import java.io.IOException;

public class LibrariansRegistrationController {

    @FXML
    Text registrationMessage;
    @FXML
    PasswordField passwordField;
    @FXML
    TextField usernameField;
    @FXML
    TextField fullnameField;
    @FXML
    TextField addressField;
    @FXML
    TextField phonenumberField;
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene scene2=new Scene(p,600,500);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Welcome!");
        window.setScene(scene2);
        window.show();
    }
    public void handleRegisterAction() {
        try {
           UserService.addLibrarianUser(usernameField.getText(), passwordField.getText(),fullnameField.getText(),addressField.getText(),phonenumberField.getText());
            registrationMessage.setText("Account created successfully!");
        }  catch(UsernameFieldEmptyException | PasswordFieldEmptyException | UsernameAlreadyExistsException e){
            registrationMessage.setText(e.getMessage());
        }
    }
}
