package registration.exceptions;

public class PasswordFieldEmptyException extends Exception {
    public PasswordFieldEmptyException(){
        super(String.format("The password field is empty!"));
    }
}
