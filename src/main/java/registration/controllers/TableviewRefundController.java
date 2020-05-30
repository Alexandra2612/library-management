package registration.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private void goBack(ActionEvent actionEvent) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/reader.fxml"));
        Scene scene1=new Scene(p);
        Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Pagina cititor");
        window.setScene(scene1);
        window.show();
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
                exceedingtimecheck(i);
            }
       if(ok==0) try {
            throw new BookNotOwned();
        } catch (BookNotOwned bookNotOwned) {
            refundMessageField.setText(bookNotOwned.getMessage())
;        }
        


    }

    private void exceedingtimecheck(Imprumut i) {
        Date currentdate=new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(i.getDate());
        c.add(Calendar.DATE, i.getBook().getDuration()); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        // convert calendar to date
        Date currentDatePlus = c.getTime();
        if(currentdate.after(currentDatePlus))
            refundMessageField.setText("Limita de timp depasita");
        else
            refundMessageField.setText("se poate restitui");
    }
}
