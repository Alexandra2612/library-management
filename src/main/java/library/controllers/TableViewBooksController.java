package library.controllers;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.exceptions.BookAlreadyBorrowedException;
import library.exceptions.BookDoesNotExistException;
import library.exceptions.NoBooksAvailableException;
import library.model.Book;
import library.model.Imprumut;
import library.model.ReaderUser;
import library.services.BookService;
import library.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableViewBooksController implements Initializable{
    @FXML
    TableView<Book> tableView;
    @FXML
    TableColumn<Book,String> titleColumn;
    @FXML
    TableColumn<Book,String> authorColumn;
    @FXML
    TableColumn<Book,Integer> durationColumn;
    @FXML
    TableColumn<Book,Integer> piecesColumn;
    @FXML
    public Text borrowMessage;
    @FXML
    public TextField titleField;

    @FXML
    public void handleBorrowAction() {
        try {
            BookService.checkBookExistsAvailableAndNotOwnedInList(titleField.getText());
            Book b=BookService.getsomeBook(titleField.getText());
            Imprumut imp=new Imprumut(b);
            ReaderUser ru=UserService.getsomeUser(UserService.getConectedUser());
            ru.adaugaImprumut(imp);
            UserService.persistReaders();
            BookService.subtractPiece(titleField.getText());
            borrowMessage.setText("Borrowed successfully!");
        }  catch(BookDoesNotExistException | NoBooksAvailableException | BookAlreadyBorrowedException e){
            borrowMessage.setText(e.getMessage());
        }
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent p= FXMLLoader.load(getClass().getResource("/fxml/reader.fxml"));
        Scene scene1=new Scene(p,600,500);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Reader");
        window.setScene(scene1);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("duration"));
        piecesColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("pieces"));

        tableView.setItems(getBooks());

    }
    public ObservableList<Book> getBooks(){
        BookService bs=new BookService();
        ObservableList<Book> books=FXCollections.observableArrayList();
        for(Book b:bs.getBooks())
            books.add((Book) b);
        return books;
    }
}
