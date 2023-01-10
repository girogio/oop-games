package mt.grigolo.exceptions;

public class ArmyEmptyException extends Exception{
    public ArmyEmptyException() {
        super("Army is empty. Train some troops first.");
    }
}
