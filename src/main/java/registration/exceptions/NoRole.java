package registration.exceptions;

public class NoRole extends Exception {
    public NoRole() { super(String.format("Add a role"));
    }
}
