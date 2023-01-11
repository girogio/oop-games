package mt.grigolo.clash_of_clubs.exceptions;

public class ArmyBusyException extends Exception {
    public ArmyBusyException() {
        super("Army is currently busy.");
    }
}
