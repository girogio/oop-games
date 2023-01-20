package mt.grigolo.clash_of_clubs.buildings.troops.types;

import mt.grigolo.clash_of_clubs.buildings.troops.TroopGenerator;
import mt.grigolo.clash_of_clubs.players.Village;
import mt.grigolo.clash_of_clubs.troops.types.Archer;

public class ArcherGenerator extends TroopGenerator {

    public ArcherGenerator(Village v) {
        super(new Archer(), v.getArmy(), v.getGoldStorage());
    }

    @Override
    public Archer generatedTroop() {
        return new Archer();
    }


}
