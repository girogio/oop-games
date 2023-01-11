package mt.grigolo.clash_of_clubs.troops.types;

import mt.grigolo.clash_of_clubs.resources.types.Gold;
import mt.grigolo.clash_of_clubs.troops.Troop;
import mt.grigolo.clash_of_clubs.utils.Globals;

public class Barbarian extends Troop {

    public Barbarian() {
        super(Globals.barbarianCost, Globals.barbarianDamage, new Gold(0, Globals.barbarianMaxInventory), Globals.barbarianSpeed, Globals.barbarianHealth);
    }

}
