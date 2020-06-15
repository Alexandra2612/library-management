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

public class ReadersRegistrationController {
    @FXML
    public Text registrationMessage2;
    @FXML
    public PasswordField passwordField2;
    @FXML
    public TextField usernameField2;
    @FXML
    public TextField fullnameField2;
    @FXML
    public TextField addressField2;
    @FXML
    public TextField phonenumberField2;

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
