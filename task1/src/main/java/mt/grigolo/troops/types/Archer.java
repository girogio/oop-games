package mt.grigolo.troops.types;

import mt.grigolo.resources.types.Gem;
import mt.grigolo.troops.Troop;

public class Archer extends Troop {

    public Archer() {
        super(100, 100, new Gem(0, 10), 10, 100);
    }

}
