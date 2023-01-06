package mt.grigolo.resources.types;

import mt.grigolo.resources.Resource;

public class GemStorage extends Resource {

    public GemStorage(int amount, int maxAmount) {
        super(amount, maxAmount);
    }

    @Override
    public String getName(boolean plural) {
        return plural ? "Gems" : "Gem";
    }


}
