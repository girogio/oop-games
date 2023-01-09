package mt.grigolo.buildings;

import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.resources.Resource;
import mt.grigolo.players.Player;
import mt.grigolo.utils.LevelableObject;

public abstract class Building extends LevelableObject {

    private final int buildCost;

    public Building(Resource levelUpResource, int maxLevel, int levelUpCost, int costIncrease, int cost) {
        super(maxLevel, levelUpCost, costIncrease, levelUpResource);
        this.buildCost = cost;
    }

    public int getBuildCost() {
        return buildCost;
    }

    public abstract void doTick();

    protected abstract void interact() throws InsufficientResourceException;

}
