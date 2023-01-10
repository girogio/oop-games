package mt.grigolo.troops.types;

import mt.grigolo.utils.Globals;
import mt.grigolo.resources.types.Elixir;
import mt.grigolo.troops.Troop;

public class Goblin extends Troop {

    public Goblin() {
        super(Globals.goblinCost, Globals.goblinDamage, new Elixir(0, Globals.goblinMaxInventory), Globals.goblinSpeed, Globals.goblinHealth);
    }

}
