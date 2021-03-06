package library.controllers;

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
    private void signOut(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene scene4=new Scene(p,600,500);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Welcome!");
        window.setScene(scene4);
        window.show();
    }

    @FXML
    private void handleTableViewReadersDataButton(ActionEvent event) throws IOException{
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/tableview_readersdata.fxml"));
        Scene scene3=new Scene(p,600,500);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }
    @FXML
    private void handleReadersRegistrationButton(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/readersregistration.fxml"));
        Scene scene1=new Scene(p,600,500);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Register");
        window.setScene(scene1);
        window.show();
    }
    @FXML
    private void handleAddBookAction(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/fxml/add_books.fxml"));
        Scene scene2 = new Scene(p, 600, 500);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Add a book");
        window.setScene(scene2);
        window.show();
    }

}
