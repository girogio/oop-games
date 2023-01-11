package mt.grigolo.clash_of_clubs.exceptions;

public class InsufficientResourceException extends Exception {

    public InsufficientResourceException(int requiredResourceAmount) {
        super("Insufficient resource. You need " + requiredResourceAmount + " more.");
    }

}
