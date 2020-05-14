package registration.exceptions;

public class BookAlreadyExistsException extends Exception{

    public BookAlreadyExistsException() {
        super(String.format("Cartea exista"));
    }
}
