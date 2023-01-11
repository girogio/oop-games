package mt.grigolo.clash_of_clubs.buildings;

import mt.grigolo.clash_of_clubs.exceptions.ArmyAwayException;
import mt.grigolo.clash_of_clubs.exceptions.ArmyFullException;
import mt.grigolo.clash_of_clubs.exceptions.InsufficientResourceException;
import mt.grigolo.clash_of_clubs.resources.Resource;
import mt.grigolo.clash_of_clubs.utils.LevelableObject;

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
        return getBuildCost() + " " + getLevelUpResource().getName();
    }

    public abstract void doTick();

    public abstract void interact() throws ArmyFullException, ArmyAwayException, InsufficientResourceException;

    public abstract String toString();

}
