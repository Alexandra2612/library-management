package registration.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import registration.exceptions.AuthorFieldEmptyException;
import registration.exceptions.BookAlreadyExistsException;
import registration.exceptions.TitleFieldEmptyException;
import registration.services.BookService;

public class AddBooksController {
    @FXML
    private TextField TitleField;
    @FXML
    private TextField AuthorField;
    @FXML
    private TextField DurationField;
    @FXML
    private TextField PiecesField;
    @FXML
    private Label ErrorField;


    @FXML
    private void add_books_check() {

            try {

                BookService.addBook(TitleField.getText(), AuthorField.getText(), Integer.parseInt(DurationField.getText()), Integer.parseInt(PiecesField.getText()));
                ErrorField.setText("Carte adaugata cu succes!");
            } catch(java.lang.NumberFormatException ex){
                ErrorField.setText("Adauga datele numerice");
            } catch (TitleFieldEmptyException ex) {
                ErrorField.setText(ex.getMessage());
            } catch (AuthorFieldEmptyException ex) {
                ErrorField.setText(ex.getMessage());
            } catch (BookAlreadyExistsException ex) {
                ErrorField.setText(ex.getMessage());

            }
        }

    @FXML
    private void goBack(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/fxml/librarian.fxml"));
        Scene scene2 = new Scene(p, 500, 500);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Pagina bibliotecar");
        window.setScene(scene2);
        window.show();
    }
}



