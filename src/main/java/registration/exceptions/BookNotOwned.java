package registration.exceptions;

public class BookNotOwned extends Exception {
    public BookNotOwned() {
        super(String.format("Cartea nu e imprumutata"));
    }
}
