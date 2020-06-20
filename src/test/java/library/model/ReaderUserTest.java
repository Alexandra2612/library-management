package library.model;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import library.controllers.TableViewBooksController;
import library.controllers.TableViewBorrowController;
import library.services.BookService;
import library.services.FileSystemService;
import library.services.UserService;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class ReaderUserTest{
    private ReaderUser user;
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
        user = new ReaderUser();
        user.username= new String();
        user.password= new String();
        user.fullname= new String();
        user.address= new String();
        user.phonenumber = new String();
        user.listaimprumuturi=new ArrayList<Imprumut>();
    }
    @Test
    public void testTryAddABookInTheBorrowedBooksListActionCode() throws Exception{
        BookService.addBook("TEST_TITLE","TEST_AUTHOR",123,12);
        UserService.addReaderUser("testuser","testpassword","testfullname","testaddress","testphonenumber");
        UserService.setConectedUser("testuser");
        user=new ReaderUser();
        user.adaugaImprumut(new Imprumut(BookService.getsomeBook("TEST_TITLE")));
        assertEquals(1,user.getListaimprumuturi().size());
    }
}
