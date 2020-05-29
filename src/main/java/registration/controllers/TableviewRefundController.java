package registration.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import registration.model.Imprumut;

public class TableviewRefundController {
    @FXML
    private TextField refundField;
    @FXML
    private TableColumn<Imprumut,String> bookColumn;
    @FXML
    private TableColumn<Imprumut,String> dateColumn;
    @FXML
    private TableView<Imprumut> tableView;

    @FXML
    private void goBack(ActionEvent actionEvent) {
    }


}
