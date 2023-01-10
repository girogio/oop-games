package mt.grigolo.troops;

import mt.grigolo.exceptions.ArmyBusyException;
import mt.grigolo.exceptions.ArmyEmptyException;
import mt.grigolo.exceptions.ArmyFullException;
import mt.grigolo.players.Village;
import mt.grigolo.utils.Globals;
import mt.grigolo.utils.Position;

import java.util.ArrayList;

public class Army extends ArrayList<Troop> {


    private int maxTroops;

    private int ticksUntilArrival;

    public boolean isInRange(Village v) {
        return currentPos == v.pos && ticksUntilArrival == 0;
    }

    public void addTroop(Troop troop) throws ArmyFullException {
        if (this.size() < getMaxTroops()) {
            add(troop);
        } else throw new ArmyFullException();
    }

    private final Village sourceVillage;

    private Village destination;
    private Position currentPos;

    public int getTicksUntilArrival() {
        return ticksUntilArrival;
    }

    public void march() {
        if (currentPos != destination.pos && ticksUntilArrival > 0) {
            ticksUntilArrival--;
        }
        if (ticksUntilArrival == 0) {
            currentPos = destination.pos;
        }
    }

    public Army(Village sourceVillage) {
        this.sourceVillage = sourceVillage;
        this.destination = this.sourceVillage;
        this.currentPos = this.sourceVillage.pos;
        ticksUntilArrival = 0;
        setMaxTroops(Globals.initialMaxTroops);
    }

    public void emptyInventory() {
        this.forEach(troop -> sourceVillage.takeFromTroop(troop, troop.getInventory().getAmount()));
        destination = sourceVillage;
    }

    public void setDestination(Village startPoint, Village destination) {
        this.ticksUntilArrival = (int) Math.ceil((double) (Position.distance(startPoint.pos, destination.pos) - getArmyMinSpeed()) / getArmyMinSpeed());
        this.currentPos = startPoint.pos;
        this.destination = destination;
    }

    public void initiateAttack(Village enemyVillage) throws ArmyBusyException, ArmyEmptyException {
        if (!this.isInHomeVillage()) {
            throw new ArmyBusyException();
        } else if (isEmpty()) {
            throw new ArmyEmptyException();
        } else {
            setDestination(sourceVillage, enemyVillage);
            enemyVillage.getEnemyArmies().add(this);
        }
    }

    public boolean isInHomeVillage() {
        return ticksUntilArrival == 0 && currentPos.equals(sourceVillage.pos);
    }

    public boolean attack(Village defendingVillage) {
        boolean fightSuccess = false;
        for (Troop troop : this) {
            if (!defendingVillage.getArmy().isEmpty() && defendingVillage.getArmy().isInHomeVillage()) {

                // Each troop attacks a random enemy troop
                troop.fight(defendingVillage.getArmy().get((int) (Math.random() * defendingVillage.getArmy().size())));

                // If any troop is dead, remove it from the army
                this.cleanCorpses();
                defendingVillage.getArmy().cleanCorpses();

            } else {
                // If the defending village has no army, or it is away, attack the village directly
                defendingVillage.damage(troop.getAttack());

                // take as much resource as possible from the village
                defendingVillage.giveToTroop(troop, troop.getInventory().getMaxAmount());

                fightSuccess = true;
            }
        }
        // start returning to base!
        if (fightSuccess) {
            setDestination(defendingVillage, sourceVillage);
        }

        return fightSuccess;
    }

    public void cleanCorpses() {
        removeIf(Troop::isDead);
    }

    @Override
    public String toString() {
        String s = "Army located at " + currentPos;
        if (ticksUntilArrival > 0) {
            s += " and will arrive at " + destination.pos + " in " + ticksUntilArrival + " ticks";
        }

        s += ".\n\nTroops: \n";

        for (Troop troop : this) {
            s += "\t   " + troop + "\n";
        }

        return s;
    }

    @Override
    public boolean add(Troop troop) {
        if (this.size() < getMaxTroops()) {
            return super.add(troop);
        } else {
            return false;
        }
    }


    public int getMaxTroops() {
        return maxTroops;
    }

    public void setMaxTroops(int maxTroops) {
        this.maxTroops = maxTroops;
    }

    private int getArmyMinSpeed() {
        int minSpeed = Integer.MAX_VALUE;
        for (Troop troop : this) {
            if (troop.getMarchingSpeed() < minSpeed) minSpeed = troop.getMarchingSpeed();
        }
        return minSpeed;
    }

}
