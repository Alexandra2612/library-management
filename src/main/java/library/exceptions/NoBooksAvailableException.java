package library.exceptions;

public class NoBooksAvailableException extends Exception{
    public NoBooksAvailableException() {
        super(String.format("No copies available!"));
    }
}
