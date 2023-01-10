package mt.grigolo.buildings.troops.types;

import mt.grigolo.buildings.troops.TroopGenerator;
import mt.grigolo.players.*;
import mt.grigolo.troops.types.Barbarian;

public class BarbarianGenerator extends TroopGenerator {

    public BarbarianGenerator(Village v) {
        super(new Barbarian(), v.getArmy(), v.getElixirStorage());
    }

    @Override
    public Barbarian generatedTroop() {
        return new Barbarian();
    }


}
