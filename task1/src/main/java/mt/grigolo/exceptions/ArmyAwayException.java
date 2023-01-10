package mt.grigolo.exceptions;

public class ArmyAwayException extends Exception {
    public ArmyAwayException() {
        super("The army is not in your village anymore!");
    }
}
