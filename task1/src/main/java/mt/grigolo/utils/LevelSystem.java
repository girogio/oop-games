package mt.grigolo.utils;

import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.exceptions.MaxLevelException;
import mt.grigolo.resources.Resource;

public abstract class LevelSystem {

    private final Resource resourceConsumed;
    private int level = 1;
    private final int maxLevel;
    private int levelUpCost;
    private int costIncrease;
    private final static double costIncreaseFactor = 1.2;

    public LevelSystem(Resource resourceConsumed, int maxLevel, int levelUpCost, int costIncrease) {
        this.resourceConsumed = resourceConsumed;
        this.maxLevel = maxLevel;
        this.levelUpCost = levelUpCost;
        this.costIncrease = costIncrease;
    }

    public void levelUp() throws InsufficientResourceException, MaxLevelException {
        if (level > maxLevel) {
            throw new MaxLevelException();
        } else if (resourceConsumed.getAmount() < levelUpCost) {
            throw new InsufficientResourceException();
        } else {
            level++;
            this.resourceConsumed.decrement(levelUpCost);
            levelUpCost += costIncrease;
            costIncrease *= costIncreaseFactor;
            levelUpLogic();
        }
    }

    public abstract void levelUpLogic();

    public int getLevel() {
        return level;
    }
}
