package mt.grigolo.clash_of_clubs.troops.types;

import mt.grigolo.clash_of_clubs.resources.types.Gem;
import mt.grigolo.clash_of_clubs.troops.Troop;
import mt.grigolo.clash_of_clubs.utils.Globals;

public class Archer extends Troop {

    public Archer() {
        super(Globals.archerCost, Globals.archerDamage, new Gem(0, Globals.archerMaxInventory), Globals.archerSpeed, Globals.archerHealth);
    }

}
