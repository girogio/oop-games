package mt.grigolo.resources.types;

import mt.grigolo.resources.Resource;

public class Elixir extends Resource {

    public Elixir(int amount, int maxAmount) {
        super(amount, maxAmount);
    }

    @Override
    public String getName() {
        return "Elixir";
    }

}
