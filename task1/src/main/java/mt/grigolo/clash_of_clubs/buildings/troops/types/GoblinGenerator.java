package mt.grigolo.clash_of_clubs.buildings.troops.types;

import mt.grigolo.clash_of_clubs.buildings.troops.TroopGenerator;
import mt.grigolo.clash_of_clubs.players.Village;
import mt.grigolo.clash_of_clubs.troops.types.Goblin;

public class GoblinGenerator extends TroopGenerator {

    public GoblinGenerator(Village v) {
        super(new Goblin(), v.getArmy(), v.getGemStorage());
    }

    @Override
    public Goblin generatedTroop() {
        return new Goblin();
    }


}
