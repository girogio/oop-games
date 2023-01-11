package mt.grigolo.clash_of_clubs.troops;

import mt.grigolo.clash_of_clubs.exceptions.ArmyBusyException;
import mt.grigolo.clash_of_clubs.exceptions.ArmyEmptyException;
import mt.grigolo.clash_of_clubs.exceptions.ArmyFullException;
import mt.grigolo.clash_of_clubs.players.Village;
import mt.grigolo.clash_of_clubs.utils.Globals;
import mt.grigolo.clash_of_clubs.utils.Input;
import mt.grigolo.clash_of_clubs.utils.Position;

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
        for (int i = 0; i < this.size(); i++) {
            if (!defendingVillage.getArmy().isEmpty() && defendingVillage.getArmy().isInHomeVillage()) {

                // get a random opposing troop
                int troopToAttack = Input.randomInt(0, defendingVillage.getArmy().size() - 1);

                // Each troop attacks a random enemy troop
                get(i).fight(defendingVillage.getArmy().get(troopToAttack));

                // If friendly troop is dead, remove it from the army
                if(get(i).isDead()) {
                    remove(i);
                    i--;
                }

                // if enemy troop is dead, remove it from the army
                if(defendingVillage.getArmy().get(troopToAttack).isDead()) {
                    defendingVillage.getArmy().remove(troopToAttack);
                }

            } else {

                // If the defending village has no army, or it is away, attack the village directly
                defendingVillage.damage(get(i).getAttack());

                // take as much resource as possible from the village
                defendingVillage.giveToTroop(get(i), get(i).getInventory().getMaxAmount());

                // flag attack as successful
                fightSuccess = true;
            }
        }
        // start marching back to base!
        if (fightSuccess) {
            setDestination(defendingVillage, sourceVillage);
        }

        return fightSuccess;
    }

    @Override
    public String toString() {

        String s = "Army ";

        if(ticksUntilArrival <= 0)
            s += "located at " + (isInHomeVillage() ? "home" : currentPos);
        else
            s += "on the way to " + destination.pos + ". (" + ticksUntilArrival + " ticks until arrival)";


        s += ".\n\nTroops: \n";

        StringBuilder sb = new StringBuilder();

        for (Troop troop : this)
            sb.append("\t   ").append(troop).append("\n");

        s += sb.toString();

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
