package registration.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import registration.exceptions.*;
import registration.model.Book;

public class BookService {
    private static List<Book> books;

    public static void loadBooksFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        books= objectMapper.readValue(Paths.get("src/main/java/registration/services/config/books.json").toFile(), new TypeReference<List<Book>>() {
        });
    }
    public static void addBook(String title, String author, int duration,int pieces) throws BookAlreadyExistsException, AuthorFieldEmptyException, TitleFieldEmptyException {
        checkTitleFieldIsNotEmpty(title);
        checkAuthorFieldIsNotEmpty(author);

        checkBookDoesNotAlreadyExist(title);

        books.add(new Book(title, author,duration,pieces));
        persistBooks();
    }

    public List<Book> getBooks() {
        return books;
    }

    private static void checkBookDoesNotAlreadyExist(String title) throws BookAlreadyExistsException{
        for (Book book : books) {
            if (Objects.equals(title, book.getTitle()))
                throw new BookAlreadyExistsException();
        }
    }
    public static void checkBookExistsAndAvailableInList(String title) throws BookDoesNotExistException, NoBooksAvailableException {
        int exista=0;
        int existabucati=0;
        for (Book book : books)
            if (Objects.equals(title, book.getTitle())){
                  exista=1;
                  if(book.getPieces()>0)
                      existabucati=1;
            }

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

    private static void persistBooks() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get("src/main/java/registration/services/config/books.json").toFile(), books);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

}
