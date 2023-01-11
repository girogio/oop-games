package mt.grigolo.clash_of_clubs.exceptions;

public class ArmyAwayException extends Exception {
    public ArmyAwayException() {
        super("The army is not in your village anymore!");
    }
}
