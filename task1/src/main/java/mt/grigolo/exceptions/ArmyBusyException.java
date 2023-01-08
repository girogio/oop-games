package mt.grigolo.exceptions;

public class ArmyBusyException extends Exception {
    public ArmyBusyException() {
        super("Army is currently busy.");
    }
}
