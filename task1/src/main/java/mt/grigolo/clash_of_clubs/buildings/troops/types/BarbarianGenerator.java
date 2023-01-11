package mt.grigolo.clash_of_clubs.buildings.troops.types;

import mt.grigolo.clash_of_clubs.buildings.troops.TroopGenerator;
import mt.grigolo.clash_of_clubs.players.Village;
import mt.grigolo.clash_of_clubs.troops.types.Barbarian;

public class BarbarianGenerator extends TroopGenerator {

    public BarbarianGenerator(Village v) {
        super(new Barbarian(), v.getArmy(), v.getElixirStorage());
    }

    @Override
    public Barbarian generatedTroop() {
        return new Barbarian();
    }


}
