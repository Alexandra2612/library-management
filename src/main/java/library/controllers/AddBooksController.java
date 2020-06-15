package library.controllers;

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
import library.exceptions.AuthorFieldEmptyException;
import library.exceptions.BookAlreadyExistsException;
import library.exceptions.TitleFieldEmptyException;
import library.services.BookService;

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
                ErrorField.setText("Book added successfully!");
            } catch(java.lang.NumberFormatException ex){
                ErrorField.setText("Add numeric data");
            } catch (TitleFieldEmptyException | AuthorFieldEmptyException ex) {
                ErrorField.setText(ex.getMessage());
            } catch (BookAlreadyExistsException ex) {
                ErrorField.setText(ex.getMessage());

            }
        }

    @FXML
    private void goBack(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/fxml/librarian.fxml"));
        Scene scene2 = new Scene(p, 600, 500);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Librarian");
        window.setScene(scene2);
        window.show();
    }
}



