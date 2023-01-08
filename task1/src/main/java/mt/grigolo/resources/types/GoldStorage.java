package mt.grigolo.resources.types;

import mt.grigolo.resources.Resource;

public class GoldStorage extends Resource {

    public GoldStorage(int amount, int maxAmount) {
        super(amount, maxAmount);
    }

    @Override
    public String getName() {
        return "Gold";
    }

}


