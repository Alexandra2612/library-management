package library.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.beans.property.SimpleStringProperty;
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
import library.exceptions.BookNotOwned;
import library.model.Imprumut;
import library.model.ReaderUser;
import library.services.BookService;
import library.services.UserService;

public class TableviewRefundController implements Initializable {
    @FXML
     TextField refundField;
    @FXML
    TableView<Imprumut> tableView;
    @FXML TableColumn<Imprumut,String> dateColumn;
    @FXML TableColumn<Imprumut,String> bookColumn;
    @FXML
     Label refundMessageField;
    @FXML  Label refundMessageField1;

    @FXML
    private void goBack(ActionEvent actionEvent) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/reader.fxml"));
        Scene scene1=new Scene(p,600,500);
        Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Reader");
        window.setScene(scene1);
        window.show();
    }
    public void initialize(URL location, ResourceBundle resources){
        dateColumn.setCellValueFactory(c -> {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date date=c.getValue().getDate();
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);

            // manipulate date

            ca.add(Calendar.DATE, c.getValue().getBook().getDuration()); //same with c.add(Calendar.DAY_OF_MONTH, 1);
            // convert calendar to date
            Date currentDatePlus = ca.getTime();
            return new SimpleStringProperty(df.format(currentDatePlus));
        });
        bookColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBook().getTitle()+" de "+c.getValue().getBook().getAuthor()));
        tableView.setItems(this.getImprumut());
    }
    public ObservableList<Imprumut> getImprumut(){
        ReaderUser r;
        r=UserService.getsomeUser(UserService.getConectedUser());
        assert r != null;
        ArrayList<Imprumut> im= r.getListaimprumuturi();
        ObservableList<Imprumut> imp= FXCollections.observableArrayList();
        if(im==null)
            im= new ArrayList<>();
        imp.addAll(im);
        return imp;
    }



    @FXML
    public void handleRefundaction() {
        refundMessageField.setText("");
        refundMessageField1.setText("");
        int ok=0;
        ReaderUser r;
        r=UserService.getsomeUser(UserService.getConectedUser());
        assert r != null;
        ArrayList<Imprumut> im= r.getListaimprumuturi();
        try {
            for (Imprumut i : im)
                if ((i.getBook().getTitle()).equals(refundField.getText())) {
                    ok = 1;
                    exceedingtimecheck(i);
                    ArrayList<Imprumut> imp = r.getListaimprumuturi();
                    imp.remove(i);
                    r.setListaimprumuturi(imp);
                    tableView.refresh();
                    UserService.persistReaders();
                    tableView.refresh();
                    BookService.addPiece(i.getBook().getTitle());
                    tableView.refresh();
                    refundMessageField.setText("Book returned");

                }
        }catch(Exception ignored){}
       if(ok==0) try {
            throw new BookNotOwned();
        } catch (BookNotOwned bookNotOwned) {
            refundMessageField.setText(bookNotOwned.getMessage());
       }

    }

    private void exceedingtimecheck(Imprumut i) {
        Date currentdate=new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(i.getDate());
        c.add(Calendar.DATE, i.getBook().getDuration()); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        // convert calendar to date

        Date currentDatePlus = c.getTime();

        if(currentdate.after(currentDatePlus)) {
            long diff = currentdate.getTime() - currentDatePlus.getTime();
            refundMessageField1.setText("Time limit exceeded, cost:"+TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+" lei");

        }

    }


}
