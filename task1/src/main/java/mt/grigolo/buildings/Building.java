package mt.grigolo.buildings;

import mt.grigolo.exceptions.ArmyAwayException;
import mt.grigolo.exceptions.ArmyFullException;
import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.resources.Resource;
import mt.grigolo.resources.types.Gem;
import mt.grigolo.utils.LevelableObject;

public abstract class Building extends LevelableObject {

    private final int buildCost;

    public Building(Resource levelUpResource, int maxLevel, int levelUpCost, int costIncrease, int buildCost) {
        super(maxLevel, levelUpCost, costIncrease, levelUpResource);
        this.buildCost = buildCost;
    }

    public int getBuildCost() {
        return buildCost;
    }

    public String getBuildCostString() {
        String s = getBuildCost() + " " + getLevelUpResource().getClass().getSimpleName();
        if (getLevelUpResource().getClass().getSimpleName().equals(Gem.class.getSimpleName()))
            s += "s";

        return s;
    }

    public abstract void doTick();

    public abstract void interact() throws InsufficientResourceException, ArmyFullException, ArmyAwayException;

    public abstract String toString();

}
