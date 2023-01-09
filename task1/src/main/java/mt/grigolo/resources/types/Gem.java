package mt.grigolo.resources.types;

import mt.grigolo.resources.Resource;

public class Gem extends Resource {

    public Gem(int amount, int maxAmount) {
        super(amount, maxAmount);
    }

    @Override
    public String getName() {
        return "Gem/s";
    }
}
