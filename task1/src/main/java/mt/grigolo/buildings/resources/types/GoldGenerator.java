package mt.grigolo.buildings.resources.types;

import mt.grigolo.buildings.resources.ResourceGenerator;
import mt.grigolo.players.Village;

public class GoldGenerator extends ResourceGenerator {

    public GoldGenerator(Village v) {
        super(v.getGoldStorage(), v.getElixirStorage());
    }

}
