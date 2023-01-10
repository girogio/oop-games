package mt.grigolo.buildings.troops;

import mt.grigolo.buildings.Building;
import mt.grigolo.exceptions.ArmyAwayException;
import mt.grigolo.exceptions.ArmyFullException;
import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.resources.Resource;
import mt.grigolo.troops.Army;
import mt.grigolo.troops.Troop;
import mt.grigolo.utils.Globals;

public abstract class TroopGenerator extends Building {

    private final Army targetArmy;

    private final Troop troopGenerated;

    public abstract Troop generatedTroop();

    private final Resource resourceConsumed;

    private float discount = 0;

    public float getDiscount() {
        return discount;
    }

    private final int interactCost;

    public String getInteractCostString() {
        return interactCost + " " + getLevelUpResource().getName();
    }

    public TroopGenerator(Troop troopGenerated, Army targetArmy, Resource resourceConsumed) {
        super(resourceConsumed, 10, 30, 50, 50);
        this.troopGenerated = troopGenerated;
        this.targetArmy = targetArmy;
        this.interactCost = troopGenerated.getCost();
        this.resourceConsumed = resourceConsumed;
    }

    @Override
    public void levelUpLogic() {
        discount += Globals.TroopGeneratorDiscountIncrease;
    }

    @Override
    public void doTick() {
        // do nothing
    }

    @Override
    public void interact() throws InsufficientResourceException, ArmyFullException, ArmyAwayException {
        if (targetArmy.isInHomeVillage()) {
            int cost = (int) (interactCost * (1 - discount));
            if (resourceConsumed.getAmount() < cost) {
                throw new InsufficientResourceException(cost - resourceConsumed.getAmount());
            } else {
                if (!targetArmy.add(generatedTroop()))
                    throw new ArmyFullException();
                resourceConsumed.decrement(cost);
            }
        } else {
            throw new ArmyAwayException();
        }
    }

    @Override
    public String toString() {
        return ("Lvl. " + getLevel() + "/" + getMaxLevel() + " " + troopGenerated.getClass().getSimpleName() +
                " Training Hut (" + (int) Math.ceil((discount) * 100) + "% discount)");
    }

}
