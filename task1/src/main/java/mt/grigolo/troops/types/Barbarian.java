package mt.grigolo.troops.types;

import mt.grigolo.resources.types.Gold;
import mt.grigolo.troops.Troop;

public class Barbarian extends Troop {

    public Barbarian() {
        super(100, 150, new Gold(0, 50), 5, 150);
    }

}
