package mt.grigolo.troops.types;

import mt.grigolo.Globals;
import mt.grigolo.resources.types.Gold;
import mt.grigolo.troops.Troop;

public class Barbarian extends Troop {

    public Barbarian() {
        super(Globals.barbarianCost, Globals.barbarianDamage, new Gold(0, Globals.barbarianMaxInventory), Globals.barbarianSpeed, Globals.barbarianHealth);
    }

}
