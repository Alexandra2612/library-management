package registration.exceptions;

public class BookAlreadyBorrowedException extends Exception {
    public BookAlreadyBorrowedException() {
        super(String.format("Book already borrowed!"));
    }
}
