package registration.exceptions;

public class AuthorFieldEmptyException extends Exception{
    public AuthorFieldEmptyException(){
        super(String.format("Adauga autor"));
    }
}
