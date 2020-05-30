package registration.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import registration.exceptions.BookNotOwned;
import registration.model.Imprumut;
import registration.model.ReaderUser;
import registration.services.UserService;

import static registration.controllers.TableViewReadersController.getSelectedUser;

public class TableviewRefundController implements Initializable {
    @FXML
    private TextField refundField;
    @FXML
    private TableView<Imprumut> tableView;
    @FXML private TableColumn<Imprumut,String> dateColumn;
    @FXML private TableColumn<Imprumut,String> bookColumn;
    @FXML
    private Label refundMessageField;

    @FXML
    private void goBack(ActionEvent actionEvent) {
    }
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
                return new SimpleStringProperty(c.getValue().getBook().getTitle()+" de "+c.getValue().getBook().getAuthor());
            }
        });
        tableView.setItems(this.getImprumut());
    }
    public ObservableList<Imprumut> getImprumut(){
        ReaderUser r;
        r=UserService.getsomeUser(UserService.getConectedUser());
        ArrayList<Imprumut> im= r.getListaimprumuturi();
        ObservableList<Imprumut> imp= FXCollections.observableArrayList();
        if(im==null)
            im=new ArrayList<Imprumut>();
        for(Imprumut i:im)
            imp.add((Imprumut) i);
        return imp;
    }



    @FXML
    private void handleRefundaction(ActionEvent actionEvent) {
        
        int ok=0;
        ReaderUser r;
        r=UserService.getsomeUser(UserService.getConectedUser());
        ArrayList<Imprumut> im= r.getListaimprumuturi();
        for(Imprumut i:im)
            if((i.getBook().getTitle()).equals(refundField.getText())){
                ok=1;
                exceedingtimecheck();
            }
       if(ok==0) try {
            throw new BookNotOwned();
        } catch (BookNotOwned bookNotOwned) {
            refundMessageField.setText(bookNotOwned.getMessage())
;        }
        


    }

    private void exceedingtimecheck() {
        refundMessageField.setText("ok");
    }
}
