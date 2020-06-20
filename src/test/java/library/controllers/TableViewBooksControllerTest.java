package library.controllers;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import library.model.Book;
import library.services.BookService;
import library.services.FileSystemService;
import library.services.UserService;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TableViewBooksControllerTest extends ApplicationTest {
    public static final String TEST_TITLE = "testTitle";
    public static final String TEST_AUTHOR = "testAuthor";
    public static final int TEST_DURATION = 123;
    public static final int TEST_PIECES = 13;
    private TableViewBooksController controller;
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = "test-ADGUB";
        FileSystemService.initApplicationHomeDirIfNeeded();
        UserService.loadUsersFromFile();
        BookService.loadBooksFromFile();
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();
        BookService.loadBooksFromFile();
        controller = new TableViewBooksController();
        controller.titleColumn= new TableColumn<Book,String>();
        controller.authorColumn= new TableColumn<Book,String>();
        controller.durationColumn= new TableColumn<Book,Integer>();
        controller.piecesColumn= new TableColumn<Book,Integer>();
        controller.tableView = new TableView<Book>();
        controller.borrowMessage = new Text();
        controller.titleField=new TextField();
        controller.titleField.setText(TEST_TITLE);
    }
    @Test
    public void testTryBorrowABookActionCode() throws Exception{
        BookService.addBook(TEST_TITLE,TEST_AUTHOR,TEST_DURATION,TEST_PIECES);
        UserService.addReaderUser("testuser","testpassword","testfullname","testaddress","testphonenumber");
        UserService.setConectedUser("testuser");
        controller.handleBorrowAction();
        assertEquals(UserService.getsomeUser(UserService.getConectedUser()).getListaimprumuturi().get(0).getBook(), BookService.getsomeBook(TEST_TITLE));
        assertEquals("Borrowed successfully!", controller.borrowMessage.getText());
    }
    @Test
    public void testBorrowSameBookTwice() throws Exception{
        BookService.addBook(TEST_TITLE,TEST_AUTHOR,TEST_DURATION,TEST_PIECES);
        UserService.addReaderUser("testuser","testpassword","testfullname","testaddress","testphonenumber");
        UserService.setConectedUser("testuser");
        controller.handleBorrowAction();
        assertEquals(UserService.getsomeUser(UserService.getConectedUser()).getListaimprumuturi().get(0).getBook(), BookService.getsomeBook(TEST_TITLE));
        controller.handleBorrowAction();
        assertEquals("Book already borrowed!", controller.borrowMessage.getText());
    }
}
