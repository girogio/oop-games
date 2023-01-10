package mt.grigolo.buildings.resources.types;

import mt.grigolo.buildings.resources.ResourceGenerator;
import mt.grigolo.players.Village;

public class GemGenerator extends ResourceGenerator {

    public GemGenerator(Village v) {
        super(v.getGemStorage(), v.getGoldStorage());
    }

}
