package mt.grigolo.troops;

import mt.grigolo.exceptions.ArmyFullException;
import mt.grigolo.players.Village;
import mt.grigolo.utils.Position;

import java.util.ArrayList;

public class Army extends ArrayList<Troop> {


    private int maxTroops;

    private int ticksUntilArrival;

    public boolean isInRange(Village v) {
        return destination == v && ticksUntilArrival == 0;
    }

    public void addTroop(Troop troop) throws ArmyFullException {
        if (this.size() < getMaxTroops()) {
            add(troop);
        } else throw new ArmyFullException();
    }


    private final Village sourceVillage;

    private Village destination;

    Position currentPos;

    public int getTicksUntilArrival() {
        return ticksUntilArrival;
    }

    public void setTicksUntilArrival(int ticksUntilArrival) {
        this.ticksUntilArrival = ticksUntilArrival;
    }

    public void march() {
        if (destination != null && ticksUntilArrival > 0) ticksUntilArrival--;
    }

    public Army(Village sourceVillage) {
        this.sourceVillage = sourceVillage;
        ticksUntilArrival = 0;
        currentPos = sourceVillage.pos;
        setMaxTroops(2);
    }

    public void emptyInventory() {
        for (Troop troop : this) {
            sourceVillage.takeFromTroop(troop, troop.getInventory().getAmount());
        }
        destination = null;
    }

    public Village getDestination() {
        return destination;
    }

    public Village getSourceVillage() {
        return sourceVillage;
    }

    public void setDestination(Village destination) {
        this.destination = destination;
        ticksUntilArrival = (int) Math.ceil((double) (Position.distance(sourceVillage.pos, destination.pos) - getArmyMinSpeed()) / getArmyMinSpeed());
    }

    public void initiateAttack(Village enemyVillage) {
        enemyVillage.getEnemyArmies().add(this);
        setDestination(enemyVillage);
    }

    public int attackValue() {
        int totalDamage = 0;
        for (Troop troop : this) {
            totalDamage += troop.getAttack();
        }
        return totalDamage;
    }


    public boolean attack(Village defendingVillage) {
        for (Troop troop : this) {
            if (!defendingVillage.getArmy().isEmpty() && defendingVillage.getArmy().isInHomeVillage()) {

                // Each troop attacks a random enemy troop
                troop.fight(defendingVillage.getArmy().get((int) (Math.random() * defendingVillage.getArmy().size())));

                // If any troop is dead, remove it from the army
                this.cleanCorpses();
                defendingVillage.getArmy().cleanCorpses();

                // stay in the village until there are no more troops
                return false;

            } else {

                // If the defending village has no army, or it is away, attack the village directly
                defendingVillage.damage(troop.getAttack());

                // take as much resource as possible from the village
                defendingVillage.giveToTroop(troop, troop.getInventory().getMaxAmount());

                // If the village has been hit, send the troops back to their home
                return true;
            }
        }

        if (!defendingVillage.getArmy().isEmpty() && defendingVillage.getArmy().isInHomeVillage()) {
            for (Troop troop : this) {
                troop.fight(defendingVillage.getArmy().get((int) (Math.random() * defendingVillage.getArmy().size())));
                defendingVillage.getArmy().cleanCorpses();
            }
            this.cleanCorpses();
        } else {
            defendingVillage.damage(attackValue());
        }
        return false;
    }

    public void cleanCorpses() {
        removeIf(Troop::isDead);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\t Capacity: " + size() + "/" + this.getMaxTroops() + "\n");
        for (Troop troop : this) {
            s.append("\t ").append(troop.toString()).append("\n");
        }
        return s.toString();
    }

    @Override
    public boolean add(Troop troop) {
        if (this.size() < getMaxTroops()) {
            return super.add(troop);
        } else {
            return false;
        }
    }

    public boolean isInHomeVillage() {
        return destination == null && isInRange(sourceVillage);
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
