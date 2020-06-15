package library.exceptions;

public class AuthorFieldEmptyException extends Exception{
    public AuthorFieldEmptyException(){
        super(String.format("Add author"));
    }
}
