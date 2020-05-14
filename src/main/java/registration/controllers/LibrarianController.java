package registration.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LibrarianController {
    @FXML
    private void handleReadersRegistrationButton(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/readersregistration.fxml"));
        Scene scene1=new Scene(p,500,500);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Register");
        window.setScene(scene1);
        window.show();
    }
    @FXML
    private void handleAddBookAction(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/fxml/add_books.fxml"));
        Scene scene2 = new Scene(p, 500, 500);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Adauga carte!");
        window.setScene(scene2);
        window.show();
    }

}
