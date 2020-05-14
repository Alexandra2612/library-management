package registration.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import registration.exceptions.PasswordFieldEmptyException;
import registration.exceptions.UsernameAlreadyExistsException;
import registration.exceptions.UsernameFieldEmptyException;
import registration.services.UserService;

public class AddBooksController {
    @FXML
    private TextField TitleField;
    @FXML
    private TextField AuthorField;
    @FXML
    private TextField DurationField;
    @FXML
    private TextField PiecesField;
    @FXML
    private Label ErrorField;

    @FXML
    private void add_books_check(ActionEvent actionEvent) {
    }


    /*@FXML
    private void add_books_check(ActionEvent actionEvent) {
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
    }*/
}
