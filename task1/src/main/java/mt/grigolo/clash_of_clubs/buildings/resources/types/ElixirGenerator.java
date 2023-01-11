package mt.grigolo.clash_of_clubs.buildings.resources.types;

import mt.grigolo.clash_of_clubs.buildings.resources.ResourceGenerator;
import mt.grigolo.clash_of_clubs.players.Village;

public class ElixirGenerator extends ResourceGenerator {

    public ElixirGenerator(Village v) {
        super(v.getElixirStorage(), v.getGemStorage());
    }
}
