package registration.exceptions;

public class TitleFieldEmptyException extends Exception {
    public TitleFieldEmptyException(){
        super(String.format("Add a title"));
    }
}
