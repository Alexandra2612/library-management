package registration.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import registration.exceptions.UsernameAlreadyExistsException;
import registration.exceptions.UsernameFieldEmptyException;
import registration.exceptions.PasswordFieldEmptyException;
import registration.services.UserService;

public class RegistrationController {

    public Text registrationMessage;
    public PasswordField passwordField;
    public TextField usernameField;
    public TextField fullnameField;
    public TextField addressField;
    public TextField phonenumberField;
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(),fullnameField.getText(),addressField.getText(),phonenumberField.getText());
            registrationMessage.setText("Account created successfully!");
        }  catch(UsernameFieldEmptyException e){
            registrationMessage.setText(e.getMessage());
        } catch(PasswordFieldEmptyException e){
            registrationMessage.setText(e.getMessage());
        }catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }
}
