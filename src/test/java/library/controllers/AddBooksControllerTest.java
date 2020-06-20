package library.controllers;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.control.TextField;
import library.services.BookService;
import library.services.FileSystemService;

import static org.junit.Assert.assertEquals;


public class AddBooksControllerTest extends ApplicationTest {
    public   String titletest="AAA";
    public   String authortest="bbb";
    public   String durationtest="5";
    public   String piecestest="4";
    private AddBooksController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = "test-ADGUB";
        FileSystemService.initApplicationHomeDirIfNeeded();
        BookService.loadBooksFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());

        BookService.loadBooksFromFile();
        if(BookService.getBooks()!=null) {
            BookService.removeBooks();
            BookService.persistBooks();
        }
        controller = new AddBooksController();
        controller.AuthorField = new TextField();
        controller.TitleField = new TextField();
        controller.DurationField=new TextField();
        controller.PiecesField=new TextField();
        controller.TitleField.setText(titletest);
        controller.AuthorField.setText(authortest);
        controller.PiecesField.setText(piecestest);
        controller.DurationField.setText(durationtest);
        controller.ErrorField= new javafx.scene.control.Label();
    }

    @Test
    public void test_Add_one_bookTest() {
        controller.add_books_check();
        assertEquals(1, BookService.getBooks().size());
        assertEquals("Book added successfully!", controller.ErrorField.getText());

    }

    @Test
    public void test_numeric_exceptionTest() {

        controller.DurationField.setText("aaa");
        controller.add_books_check();

        assertEquals("Add numeric data", controller.ErrorField.getText());
        assertEquals(0, BookService.getBooks().size());
        BookService.subtractPiece(this.titletest);

    }
    @Test
    public void test_title_field_emptyTest() {
        controller.TitleField.setText("");
        controller.add_books_check();


        assertEquals(0, BookService.getBooks().size());
        assertEquals("Add a title", controller.ErrorField.getText());
    }
    @Test
    public void test_author_field_emptyTest(){

        controller.AuthorField = new TextField();
        controller.AuthorField.setText("");
        controller.add_books_check();
        assertEquals(0, BookService.getBooks().size());
        assertEquals("Add author", controller.ErrorField.getText());

    }
    @Test
    public void test_add_same_book_twiceTest(){

        controller.add_books_check();
        controller.add_books_check();
        assertEquals("Book already exist!", controller.ErrorField.getText());
        BookService.subtractPiece(this.titletest);



    }

}
