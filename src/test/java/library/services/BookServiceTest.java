package library.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import library.controllers.AddBooksController;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javafx.scene.control.TextField;
import library.exceptions.AuthorFieldEmptyException;
import library.exceptions.BookAlreadyExistsException;
import library.exceptions.PasswordFieldEmptyException;
import library.exceptions.TitleFieldEmptyException;
import library.model.Book;
import library.services.BookService;
import library.services.FileSystemService;
import library.services.UserService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BookServiceTest {
    public String titletest = "AAA";
    public String authortest = "bbb";
    public String durationtest = "5";
    public String piecestest = "4";
    private AddBooksController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = "test-ADGUB";
        FileSystemService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        if(BookService.getBooks()!=null) {
            BookService.removeBooks();
           // BookService.persistBooks();
        }
    }

    @Test

    public void testCopyDefaultFileIfNotExists() throws Exception {
        BookService.loadBooksFromFile();
        assertTrue(Files.exists(BookService.BOOKS_PATH));
    }
    @Test
    public void testLoadBooksFromFile() throws Exception {
        BookService.loadBooksFromFile();
        assertNotNull(BookService.books);
        assertEquals(0, BookService.books.size());
    }
    @Test
    public void testAddOneBook() throws Exception {
        BookService.loadBooksFromFile();
        BookService.addBook("test", "testPass", 2,4);
        assertNotNull(BookService.books);
        assertEquals(1, BookService.books.size());
    }

    @Test
    public void testAddTwoBooks() throws Exception {
        BookService.loadBooksFromFile();
        BookService.addBook("test1", "testPass1", 1,1);
        BookService.addBook("test2", "testPass1", 1,1);
        assertNotNull(BookService.books);
        assertEquals(2, BookService.books.size());
    }

    @Test (expected =BookAlreadyExistsException.class)
    public void testAddBookAlreadyExists() throws Exception {
        BookService.loadBooksFromFile();
        BookService.addBook("test1", "testPass1", 1,1);
        assertNotNull(BookService.books);
        //BookService.checkBookExistsAvailableAndNotOwnedInList("test1");
        BookService.addBook("test1","testPass1",1,1);
    }

    @Test
    public void testAddOneBookIsPersisted() throws Exception {
        BookService.loadBooksFromFile();
        BookService.addBook("test", "testPass", 1,1);
        List<Book> books = new ObjectMapper().readValue(BookService.BOOKS_PATH.toFile(), new TypeReference<List<Book>>() {
        });
        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    public void testAddTwoUserArePersisted() throws Exception {
        BookService.loadBooksFromFile();
        BookService.addBook("test", "testPass", 1,1);
        BookService.addBook("test2", "testPass2", 1,1);
        List<Book> books = new ObjectMapper().readValue(BookService.BOOKS_PATH.toFile(), new TypeReference<List<Book>>() {
        });
        assertNotNull(books);
        assertEquals(2, books.size());
        BookService.removeBooks();
    }
   @Test
    public void testsubstract_pieces() throws IOException, TitleFieldEmptyException, BookAlreadyExistsException, AuthorFieldEmptyException {
       BookService.loadBooksFromFile();
       BookService.addBook("test", "testPass", 2,4);
       BookService.addBook("test2", "testPass2", 1,1);
       BookService.subtractPiece("test");
       assertEquals(3, BookService.getsomeBook("test").getPieces());
      BookService.removeBooks();
   }
    @Test
    public void testadd_pieces() throws IOException, TitleFieldEmptyException, BookAlreadyExistsException, AuthorFieldEmptyException {
        BookService.loadBooksFromFile();
        BookService.addBook("test", "testPass", 2,4);
        BookService.addBook("test2", "testPass2", 1,1);
        BookService.addPiece("test");
        assertEquals(5, BookService.getsomeBook("test").getPieces());
        BookService.removeBooks();
        BookService.persistBooks();
    }
    @Test
    public void testget_some_book() throws IOException, TitleFieldEmptyException, BookAlreadyExistsException, AuthorFieldEmptyException {
        BookService.loadBooksFromFile();
        BookService.addBook("test", "testPass", 2,4);
        assertEquals(BookService.getsomeBook("test").getAuthor(),"testPass");

    }

    @Test (expected =TitleFieldEmptyException.class)
    public void test_check_titlefield() throws IOException, TitleFieldEmptyException, BookAlreadyExistsException, AuthorFieldEmptyException {
        BookService.loadBooksFromFile();
        BookService.addBook("", "testPass", 2,4);
    }
    @Test (expected = AuthorFieldEmptyException.class)
    public void test_check_authorfield() throws IOException, TitleFieldEmptyException, BookAlreadyExistsException, AuthorFieldEmptyException {
        BookService.loadBooksFromFile();
        BookService.addBook("test", "", 2,4);
    }
    @Test
    public void test_removeAll() throws IOException, TitleFieldEmptyException, BookAlreadyExistsException, AuthorFieldEmptyException {
        BookService.loadBooksFromFile();
        BookService.addBook("test", "kjuh", 2,4);
        BookService.removeBooks();
        assertEquals(BookService.getBooks().size(),0);

    }

}