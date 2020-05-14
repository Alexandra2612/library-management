package registration.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import registration.exceptions.PasswordFieldEmptyException;
import registration.exceptions.UsernameAlreadyExistsException;
import registration.exceptions.UsernameFieldEmptyException;
import registration.model.Book;
import registration.model.LibrarianUser;

public class BookService {
    private static List<Book> books;

    public static void loadBooksFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        books= objectMapper.readValue(Paths.get("src/main/java/registration/services/config/books.json").toFile(), new TypeReference<List<Book>>() {
        });
    }


}
