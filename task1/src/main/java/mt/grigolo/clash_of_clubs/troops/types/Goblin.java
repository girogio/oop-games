package mt.grigolo.clash_of_clubs.troops.types;

import mt.grigolo.clash_of_clubs.resources.types.Elixir;
import mt.grigolo.clash_of_clubs.troops.Troop;
import mt.grigolo.clash_of_clubs.utils.Globals;

public class Goblin extends Troop {

    public Goblin() {
        super(Globals.goblinCost, Globals.goblinDamage, new Elixir(0, Globals.goblinMaxInventory), Globals.goblinSpeed, Globals.goblinHealth);
    }

}
