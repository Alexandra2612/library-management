package registration.controllers;

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
import registration.exceptions.UsernameAlreadyExistsException;
import registration.exceptions.UsernameFieldEmptyException;
import registration.exceptions.PasswordFieldEmptyException;
import registration.services.UserService;

import java.io.IOException;

public class LibrariansRegistrationController {

    @FXML
    public Text registrationMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public TextField fullnameField;
    @FXML
    public TextField addressField;
    @FXML
    public TextField phonenumberField;
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene scene2=new Scene(p,500,500);
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
