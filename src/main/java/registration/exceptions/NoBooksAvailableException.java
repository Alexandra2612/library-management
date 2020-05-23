package registration.exceptions;

public class NoBooksAvailableException extends Exception{
    public NoBooksAvailableException() {
        super(String.format("Nu exista exemplare disponibile"));
    }
}
