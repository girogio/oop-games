package mt.grigolo.clash_of_clubs.buildings.resources.types;

import mt.grigolo.clash_of_clubs.buildings.resources.ResourceGenerator;
import mt.grigolo.clash_of_clubs.players.Village;

public class GoldGenerator extends ResourceGenerator {

    public GoldGenerator(Village v) {
        super(v.getGoldStorage(), v.getElixirStorage());
    }

}
