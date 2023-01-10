package mt.grigolo.buildings.troops.types;

import mt.grigolo.buildings.troops.TroopGenerator;
import mt.grigolo.players.Village;
import mt.grigolo.troops.types.Archer;

public class ArcherGenerator extends TroopGenerator {

    public ArcherGenerator(Village v) {
        super(new Archer(), v.getArmy(), v.getGemStorage());
    }

    @Override
    public Archer generatedTroop() {
        return new Archer();
    }


}
