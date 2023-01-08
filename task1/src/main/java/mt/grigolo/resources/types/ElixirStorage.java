package mt.grigolo.resources.types;

import mt.grigolo.resources.Resource;

public class ElixirStorage extends Resource {

    public ElixirStorage(int amount, int maxAmount) {
        super(amount, maxAmount);
    }

    @Override
    public String getName() {
        return "Elixir";
    }

}
