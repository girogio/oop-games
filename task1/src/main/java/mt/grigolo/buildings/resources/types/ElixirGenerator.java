package mt.grigolo.buildings.resources.types;

import mt.grigolo.buildings.resources.ResourceGenerator;
import mt.grigolo.players.Village;

public class ElixirGenerator extends ResourceGenerator {

    public ElixirGenerator(Village v) {
        super(v.getElixirStorage(), v.getGemStorage());
    }
}
