package mt.grigolo.buildings.troops.types;

import mt.grigolo.buildings.troops.TroopGenerator;
import mt.grigolo.players.Village;
import mt.grigolo.troops.types.Goblin;

public class GoblinGenerator extends TroopGenerator {

    public GoblinGenerator(Village v) {
        super(new Goblin(), v.getArmy(), v.getGoldStorage());
    }

    @Override
    public Goblin generatedTroop() {
        return new Goblin();
    }


}
