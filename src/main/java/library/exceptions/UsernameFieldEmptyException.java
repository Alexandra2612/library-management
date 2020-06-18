package library.exceptions;

public class UsernameFieldEmptyException extends Exception {
    public UsernameFieldEmptyException() {
        super(String.format("The username field is empty!"));
    }
}
