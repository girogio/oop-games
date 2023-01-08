package mt.grigolo.troops.types;

import mt.grigolo.resources.types.ElixirStorage;
import mt.grigolo.troops.Troop;

public class Goblin extends Troop {

    public Goblin() {
        super(50, 50, new ElixirStorage(0, 100), 7, 75);
    }

}
