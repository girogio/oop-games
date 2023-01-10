package mt.grigolo.troops.types;

import mt.grigolo.utils.Globals;
import mt.grigolo.resources.types.Gem;
import mt.grigolo.troops.Troop;

public class Archer extends Troop {

    public Archer() {
        super(Globals.archerCost, Globals.archerDamage, new Gem(0, Globals.archerMaxInventory), Globals.archerSpeed, Globals.archerHealth);
    }

}
