package library.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.exceptions.IncorrectPassword;
import library.exceptions.UserDoesNotExist;
import library.model.LibrarianUser;
import library.model.ReaderUser;
import library.services.UserService;

public class LoginController {


    @FXML
    private javafx.scene.control.PasswordField PasswordField;
    @FXML
    private TextField UsernameField;
    @FXML
    private Label login_test;

    @FXML
    private void login_check() {
    }


    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene scene2 = new Scene(p, 600, 500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Welcome!");
        window.setScene(scene2);
        window.show();
    }


    @FXML
    private void login_check(ActionEvent actionEvent) throws IOException {

        int count = 0;
        LibrarianUser lib;
        try {
            lib = UserService.checkLibrarian(UsernameField.getText(), PasswordField.getText());
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/fxml/librarian.fxml"));
            Scene scene = new Scene(root,600,500);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Librarian");
            stage.setScene(scene);
            stage.show();

        } catch (UserDoesNotExist e) {
            count++;
            // login_test.setText(e.getMessage());
        } catch (IncorrectPassword e) {
            login_test.setText(e.getMessage());
        }


        ReaderUser read;
        try {
            read = UserService.checkReaders(UsernameField.getText(), PasswordField.getText());
            UserService.setConectedUser(UsernameField.getText());
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/fxml/reader.fxml"));
            Scene scene = new Scene(root,600,500);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Reader");
            stage.setScene(scene);
            stage.show();

        } catch (UserDoesNotExist e) {
            count++;
        } catch (IncorrectPassword e) {
            login_test.setText(e.getMessage());
        }

        try {
            if (count == 2) throw new UserDoesNotExist();
        } catch (UserDoesNotExist e) {
            login_test.setText(e.getMessage());
        }


    }
}

