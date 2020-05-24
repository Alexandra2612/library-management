package registration.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReaderController {
    @FXML
    private void signOut(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene scene2=new Scene(p,500,500);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Bun venit!");
        window.setScene(scene2);
        window.show();
    }
    @FXML
    public void handleBorrowButton(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/tableview_booksdata.fxml"));
        Scene scene1=new Scene(p);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }
}
