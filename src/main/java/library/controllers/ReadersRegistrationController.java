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

public class ReadersRegistrationController {
    @FXML
    Text registrationMessage2;
    @FXML
    PasswordField passwordField2;
    @FXML
    TextField usernameField2;
    @FXML
    TextField fullnameField2;
    @FXML
    TextField addressField2;
    @FXML
    TextField phonenumberField2;

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/librarian.fxml"));
        Scene scene0=new Scene(p,600,500);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Librarian");
        window.setScene(scene0);
        window.show();
    }
    public void handleReadersRegisterAction() {
        try {
            UserService.addReaderUser(usernameField2.getText(), passwordField2.getText(),fullnameField2.getText(),addressField2.getText(),phonenumberField2.getText());
            registrationMessage2.setText("Account created successfully!");
        }  catch(UsernameFieldEmptyException | UsernameAlreadyExistsException | PasswordFieldEmptyException e){
            registrationMessage2.setText(e.getMessage());
        }
    }
}
