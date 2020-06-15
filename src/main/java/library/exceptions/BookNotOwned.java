package library.exceptions;

public class BookNotOwned extends Exception {
    public BookNotOwned() {
        super(String.format("The book is not borrowed!"));
    }
}
