package library.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import library.model.Imprumut;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static library.controllers.TableViewReadersController.getSelectedUser;

public class TableViewBorrowController implements Initializable{
    @FXML
    private TableView<Imprumut> tableView;
    @FXML private TableColumn<Imprumut,String> dateColumn;
    @FXML private TableColumn<Imprumut,String> bookColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Imprumut,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Imprumut,String> c) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                return new SimpleStringProperty(df.format(c.getValue().getDate()));
            }
        });
        bookColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Imprumut,String>, ObservableValue<String>>() {
            @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Imprumut,String> c) {
                return new SimpleStringProperty(c.getValue().getBook().getTitle()+" by "+c.getValue().getBook().getAuthor());
            }
        });
        tableView.setItems(getImprumut());
    }
    public ObservableList<Imprumut> getImprumut(){
        tableView.refresh();
        ArrayList<Imprumut> im=getSelectedUser().getListaimprumuturi();
        ObservableList<Imprumut> imp= FXCollections.observableArrayList();
        if(im==null)
            im=new ArrayList<Imprumut>();
        for(Imprumut i:im)
            imp.add((Imprumut) i);
        return imp;
    }
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/tableview_readersdata.fxml"));
        Scene scene1=new Scene(p,600,500);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Librarian");
        window.setScene(scene1);
        window.show();
    }


}
