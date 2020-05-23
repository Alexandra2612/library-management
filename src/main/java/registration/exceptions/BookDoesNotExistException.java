package registration.exceptions;

public class BookDoesNotExistException extends Exception {
    public BookDoesNotExistException() {
        super(String.format("Cartea nu exista in lista"));
    }
}
