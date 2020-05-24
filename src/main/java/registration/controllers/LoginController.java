package registration.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import registration.exceptions.IncorrectPassword;
import registration.exceptions.NoRole;
import registration.exceptions.UserDoesNotExist;
import registration.model.LibrarianUser;
import registration.model.ReaderUser;
import registration.services.UserService;

import javax.jws.soap.SOAPBinding;

public class LoginController {


    @FXML
    private ChoiceBox RoleField;
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
    public void initialize() {
        RoleField.getItems().addAll("Bibliotecar", "Cititor");
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene scene2 = new Scene(p, 500, 500);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Bun venit!");
        window.setScene(scene2);
        window.show();
    }
    public void check_role()throws NoRole {
            if(RoleField.getValue()==null)
                throw new NoRole();
    }

    @FXML
    private void login_check(ActionEvent actionEvent) throws IOException {
        try {
            check_role();
            if (RoleField.getValue() == "Bibliotecar") {
                LibrarianUser lib;
                try {
                    lib = UserService.checkLibrarian(UsernameField.getText(), PasswordField.getText());
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("/fxml/librarian.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    stage.setTitle("Pagina bibliotecar");
                    stage.setScene(scene);
                    stage.show();

                } catch (UserDoesNotExist e) {
                    login_test.setText(e.getMessage());
                } catch (IncorrectPassword e) {
                    login_test.setText(e.getMessage());
                }

            }
            if (RoleField.getValue() == "Cititor") {
                    ReaderUser read;
                    try {
                        read = UserService.checkReaders(UsernameField.getText(), PasswordField.getText());
                        UserService.setConectedUser(UsernameField.getText());
                        Parent root;
                        root = FXMLLoader.load(getClass().getResource("/fxml/reader.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                        stage.setTitle("Pagina cititor");
                        stage.setScene(scene);
                        stage.show();

                    } catch (UserDoesNotExist e) {
                        login_test.setText(e.getMessage());
                    } catch (IncorrectPassword e) {
                        login_test.setText(e.getMessage());
                    }
            }

        } catch (NoRole e){
        login_test.setText(e.getMessage());}
    }
}

