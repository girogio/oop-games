package mt.grigolo.buildings.types;

import mt.grigolo.Globals;
import mt.grigolo.buildings.Building;
import mt.grigolo.exceptions.ArmyAwayException;
import mt.grigolo.exceptions.ArmyFullException;
import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.resources.Resource;
import mt.grigolo.troops.Army;
import mt.grigolo.troops.Troop;

public class TroopGenerator extends Building {

    private final Army targetArmy;

    private final Troop troopGenerated;

    private final Resource resourceConsumed;

    private float discount = 0;

    private final int baseCost;

    public TroopGenerator(Troop troopGenerated, Army targetArmy, Resource resourceConsumed) {
        super(resourceConsumed, 10, 30, 50, 50);
        this.troopGenerated = troopGenerated;
        this.targetArmy = targetArmy;
        this.baseCost = troopGenerated.getCost();
        this.resourceConsumed = resourceConsumed;
    }

    @Override
    public void levelUpLogic() {
        discount += Globals.TroopGeneratorDiscountIncrease;
    }

    public float getDiscount() {
        return discount;
    }

    @Override
    public void doTick() {
        // do nothing
    }

    @Override
    public void interact() throws InsufficientResourceException, ArmyFullException, ArmyAwayException {
        if (targetArmy.isInHomeVillage()) {
            int cost = (int) (baseCost * (1 - discount));
            if (resourceConsumed.getAmount() < cost) {
                throw new InsufficientResourceException(cost - resourceConsumed.getAmount());
            } else {
                if (!targetArmy.add(troopGenerated))
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
