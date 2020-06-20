package library.controllers;
import library.exceptions.BookNotOwned;
import library.exceptions.TitleFieldEmptyException;
import library.model.ReaderUser;


import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javax.jws.soap.SOAPBinding;

import javafx.scene.control.TextField;
import library.exceptions.PasswordFieldEmptyException;
import library.exceptions.UsernameAlreadyExistsException;
import library.exceptions.UsernameFieldEmptyException;
import library.model.Book;
import library.model.Imprumut;
import library.model.ReaderUser;
import library.services.BookService;
import library.services.FileSystemService;
import library.services.UserService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TableviewRefundControllerTest extends ApplicationTest {
    public   String refundFieldtest="a";
    public   String refundMessagetest;
    public String refundMessage1test;

    private TableviewRefundController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        //BookService.loadBooksFromFile();
        UserService.loadUsersFromFile();
        UserService.addReaderUser("nume","parola","dsggfs","Dsff","2585");
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());

        BookService.loadBooksFromFile();
        UserService.loadUsersFromFile();
        BookService.removeBooks();

        controller = new TableviewRefundController();
        controller.refundMessageField1 = new javafx.scene.control.Label();
        controller.refundMessageField = new javafx.scene.control.Label();
        controller.refundField=new TextField();
        controller.refundField.setText(refundFieldtest);
    }
   @Test
    public void test_refund_action_ok() throws PasswordFieldEmptyException, UsernameFieldEmptyException, UsernameAlreadyExistsException {
       //UserService.addReaderUser("nume","parola","dsggfs","Dsff","2585");
       UserService.setConectedUser("nume");
       Book b=new Book("a","a",4,6);
       BookService.books.add(b);
       BookService.persistBooks();
       Imprumut i=new Imprumut(b);
       ReaderUser r=   UserService.getsomeUser(UserService.getConectedUser());
       r.adaugaImprumut(i);
       assertEquals(r.getListaimprumuturi().size(),1);
       controller.handleRefundaction();
       assertEquals(r.getListaimprumuturi().size(),0);
   }

    @Test
    public void test_refund_action_book_not_owned() throws PasswordFieldEmptyException, UsernameFieldEmptyException, UsernameAlreadyExistsException {
        //UserService.addReaderUser("nume","parola","dsggfs","Dsff","2585");
        UserService.setConectedUser("nume");
        Book b=new Book("ab","a",4,6);
        BookService.books.add(b);
        BookService.persistBooks();
        Imprumut i=new Imprumut(b);
        ReaderUser r=   UserService.getsomeUser(UserService.getConectedUser());
        r.adaugaImprumut(i);
        controller.handleRefundaction();
        assertEquals(r.getListaimprumuturi().size(),1);
    }

    @Test
    public void test_exceedingtime() throws PasswordFieldEmptyException, UsernameFieldEmptyException, UsernameAlreadyExistsException {
        //UserService.addReaderUser("nume","parola","dsggfs","Dsff","2585");
        UserService.setConectedUser("nume");
        Book b=new Book("ab","a",-2,6);
        BookService.books.add(b);
        BookService.persistBooks();
        Imprumut i=new Imprumut(b);
        ReaderUser r=   UserService.getsomeUser(UserService.getConectedUser());
        r.adaugaImprumut(i);
        controller.handleRefundaction();
        assertNotEquals(controller.refundMessageField1,null);
    }

}
