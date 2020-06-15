package registration.exceptions;

public class BookDoesNotExistException extends Exception {
    public BookDoesNotExistException() {
        super(String.format("Book does not exist in the list!"));
    }
}
