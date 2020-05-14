package registration.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        }



