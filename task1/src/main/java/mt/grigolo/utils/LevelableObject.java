package mt.grigolo.utils;

import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.exceptions.MaxLevelException;
import mt.grigolo.resources.Resource;
import mt.grigolo.resources.types.Gem;

public abstract class LevelableObject {


    private int level = 1;
    private final int maxLevel;
    private int levelUpCost;
    private int costIncrease;

    private Resource levelUpResource;

    private final static double costIncreaseFactor = 1.2;

    public LevelableObject(int maxLevel, int levelUpCost, int costIncrease, Resource levelUpResource) {
        this.maxLevel = maxLevel;
        this.levelUpCost = levelUpCost;
        this.costIncrease = costIncrease;
        this.levelUpResource = levelUpResource;
    }

    public void levelUp() throws InsufficientResourceException, MaxLevelException {
        if (level > maxLevel) {
            throw new MaxLevelException();
        } else if (levelUpResource.getAmount() < levelUpCost) {
            throw new InsufficientResourceException(levelUpCost - levelUpResource.getAmount());
        } else {
            level++;
            this.levelUpResource.decrement(levelUpCost);
            levelUpCost += costIncrease;
            costIncrease *= costIncreaseFactor;
            levelUpLogic();
        }
    }

    public int getLevelUpCost() {
        return levelUpCost;
    }

    public String getLevelUpString() {
        if (levelUpResource instanceof Gem)
            return levelUpCost + " " + levelUpResource.getClass().getSimpleName() + "s";
        else
            return levelUpCost + " " + levelUpResource.getClass().getSimpleName();
    }

    protected abstract void levelUpLogic();

    public int getLevel() {
        return level;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public Resource getLevelUpResource() {
        return levelUpResource;
    }

    public  void setLevelUpResource(Resource levelUpResource) {
        this.levelUpResource = levelUpResource;
    }

}
