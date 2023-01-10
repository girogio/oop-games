package mt.grigolo.players;

import mt.grigolo.utils.Globals;
import mt.grigolo.buildings.Building;
import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.resources.types.Elixir;
import mt.grigolo.resources.types.Gem;
import mt.grigolo.resources.types.Gold;
import mt.grigolo.troops.Army;
import mt.grigolo.troops.Troop;
import mt.grigolo.utils.LevelableObject;
import mt.grigolo.utils.Position;

import java.util.ArrayList;

public class Village extends LevelableObject {

    public final Position pos;

    private int health, maxHealth;

    private int healPerTick;

    private final Gold gold;

    private final Gem gem;

    private final Elixir elixir;

    public Gold getGoldStorage() {
        return gold;
    }

    public Gem getGemStorage() {
        return gem;
    }

    public Elixir getElixirStorage() {
        return elixir;
    }

    public ArrayList<Building> getBuildings() {
        return villageBuildings;
    }

    private final ArrayList<Building> villageBuildings = new ArrayList<>();

    private Army army;
    private final ArrayList<Army> enemyArmies;

    public Village(int x, int y) {
        super(5, 500, 300, null);
        this.gold = new Gold(Globals.initialVillageResourceAmount, Globals.initialMaxResourceAmount);
        this.gem = new Gem(Globals.initialVillageResourceAmount, Globals.initialMaxResourceAmount);
        this.elixir = new Elixir(Globals.initialVillageResourceAmount, Globals.initialMaxResourceAmount);
        super.setLevelUpResource(gem);
        this.pos = new Position(x, y);
        this.maxHealth = Globals.initialVillageHealth;
        this.health = maxHealth;
        this.army = new Army(this);
        enemyArmies = new ArrayList<>();
        healPerTick = Globals.initialHealPerTick;
    }

    @Override
    public void levelUpLogic() {
        maxHealth += 250;
        health += 250;
        army.setMaxTroops(army.getMaxTroops() + 2);
        healPerTick += 5;
    }

    public void doTick() {

        for (Building building : villageBuildings) {
            building.doTick();
        }

        health += healPerTick;
        if (health > maxHealth) health = maxHealth;

    }

    public void damage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Lvl. " + getLevel() + " village at (" + pos.getX() + ", " + pos.getY() + ") with " + health + "/" + maxHealth + "hp\n";
        s += "Buildings:\n";
        for (Building b : getBuildings()) {
            s += "\t " + b + "\n";
        }
        s += "Army:\n";
        s += army;
        s += "\nResources:\n";
        s += "\t " + elixir + " \n";
        s += "\t " + gold + " \n";
        s += "\t " + gem + " \n";
        return s;
    }

    public void giveToTroop(Troop troop, int amount) {
        getGemStorage().giveToTroop(troop, amount);
        getGoldStorage().giveToTroop(troop, amount);
        getElixirStorage().giveToTroop(troop, amount);
    }

    public void takeFromTroop(Troop troop, int amount) {
        getGemStorage().takeFromTroop(troop, amount);
        getGoldStorage().takeFromTroop(troop, amount);
        getElixirStorage().takeFromTroop(troop, amount);
    }


    public void buyBuilding(Building building) throws InsufficientResourceException {
        if (building.getBuildCost() <= building.getLevelUpResource().getAmount()) {
            building.getLevelUpResource().decrement(building.getBuildCost());
            getBuildings().add(building);
        } else {
            throw new InsufficientResourceException(building.getBuildCost() - building.getLevelUpResource().getAmount());
        }
    }


    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public ArrayList<Army> getEnemyArmies() {
        return enemyArmies;
    }

}
