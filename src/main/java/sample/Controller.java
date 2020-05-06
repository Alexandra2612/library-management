package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {
    @FXML
    private void handleRegistrationButton(ActionEvent event) throws IOException {
       Parent p=FXMLLoader.load(getClass().getResource("/fxml/registration.fxml"));
       Scene scene2=new Scene(p,500,500);
       Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
       window.setTitle("Register");
       window.setScene(scene2);
       window.show();
    }

}
