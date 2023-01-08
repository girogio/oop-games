package mt.grigolo.buildings;

import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.resources.Resource;
import mt.grigolo.players.Player;
import mt.grigolo.utils.LevelableObject;

public abstract class Building extends LevelableObject {


    public Building(Resource levelUpResource, int maxLevel, int levelUpCost, int costIncrease) {
        super(maxLevel, levelUpCost, costIncrease, levelUpResource);
    }

    public abstract void doTick();

    protected abstract void interact() throws InsufficientResourceException;

}
