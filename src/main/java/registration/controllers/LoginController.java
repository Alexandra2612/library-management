package registration.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {


    @FXML
    private ChoiceBox RoleField;
    @FXML
    private javafx.scene.control.PasswordField PasswordField;
    @FXML
    private TextField UsernameField;

    @FXML
    private void login_check() {
    }
    @FXML
    public void initialize() { RoleField.getItems().addAll("Bibliotecar", "Cititor");
    }
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene scene2=new Scene(p,500,500);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Bun venit!");
        window.setScene(scene2);
        window.show();
    }
}

