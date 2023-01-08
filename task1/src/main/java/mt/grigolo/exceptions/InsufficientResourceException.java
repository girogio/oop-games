package mt.grigolo.exceptions;

public class InsufficientResourceException extends Exception {
    public InsufficientResourceException() {
        super("Insufficient resource to perform this action");
    }

}
