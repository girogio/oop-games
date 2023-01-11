package mt.grigolo.clash_of_clubs.exceptions;

public class ArmyEmptyException extends Exception{
    public ArmyEmptyException() {
        super("Army is empty. Train some troops first.");
    }
}
