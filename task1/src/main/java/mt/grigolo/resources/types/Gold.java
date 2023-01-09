package mt.grigolo.resources.types;

import mt.grigolo.resources.Resource;

public class Gold extends Resource {

    public Gold(int amount, int maxAmount) {
        super(amount, maxAmount);
    }

    @Override
    public String getName() {
        return "Gold";
    }

}


