package library.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import library.exceptions.*;
import library.model.Book;
import library.model.Imprumut;

public class BookService {
    private static List<Book> books;

    private static final Path BOOKS_PATH = FileSystemService.getPathToFile("config" ,"books.json");

    public static void loadBooksFromFile() throws IOException {
        if (!Files.exists(BOOKS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("books.json"), BOOKS_PATH.toFile());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        books= objectMapper.readValue(BOOKS_PATH.toFile(), new TypeReference<List<Book>>() {
        });
    }
    public static void addBook(String title, String author, int duration,int pieces) throws BookAlreadyExistsException, AuthorFieldEmptyException, TitleFieldEmptyException {
        checkTitleFieldIsNotEmpty(title);
        checkAuthorFieldIsNotEmpty(author);

        checkBookDoesNotAlreadyExist(title);

        books.add(new Book(title, author,duration,pieces));
        persistBooks();
    }
    public static void subtractPiece(String title){
        for (Book book : books) {
            if (Objects.equals(title, book.getTitle())&&book.getPieces()>0)
                book.setPieces(book.getPieces()-1);
        }
        persistBooks();
    }
    public static void addPiece(String title){
        for (Book book : books) {
            if (Objects.equals(title, book.getTitle())&&book.getPieces()>0)
                book.setPieces(book.getPieces()+1);
        }
        persistBooks();
    }
    public static List<Book> getBooks() {
        return books;
    }

    public static Book getsomeBook(String title){
        for (Book book : books) {
            if (Objects.equals(title, book.getTitle()))
               return book;
        }
        return null;
    }

    private static void checkBookDoesNotAlreadyExist(String title) throws BookAlreadyExistsException{
        for (Book book : books) {
            if (Objects.equals(title, book.getTitle()))
                throw new BookAlreadyExistsException();
        }
    }
    public static void checkBookExistsAvailableAndNotOwnedInList(String title) throws BookDoesNotExistException, NoBooksAvailableException, BookAlreadyBorrowedException {
        int exista=0;
        int existabucati=0;
        for (Book book : books)
            if (Objects.equals(title, book.getTitle())){
                  exista=1;
                  if(book.getPieces()>0)
                      existabucati=1;
            }
        for(Imprumut i:UserService.getsomeUser(UserService.getConectedUser()).getListaimprumuturi())
            if(Objects.equals(title,i.getBook().getTitle()))
                throw new BookAlreadyBorrowedException();
        if(exista==0)
            throw new BookDoesNotExistException();
        if(existabucati==0)
            throw new NoBooksAvailableException();
    }

    private static void checkTitleFieldIsNotEmpty(String title) throws TitleFieldEmptyException{
        if(title.equals(""))
            throw new TitleFieldEmptyException();
    }
    private static void checkAuthorFieldIsNotEmpty(String author) throws AuthorFieldEmptyException{
        if(author.equals(""))
            throw new AuthorFieldEmptyException();
    }

    public static void persistBooks() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(BOOKS_PATH.toFile(), books);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    public static void removeBooks() {
        books.removeAll(books);
    }
}
