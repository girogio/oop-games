package mt.grigolo.players;

import mt.grigolo.Globals;
import mt.grigolo.buildings.Building;
import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.resources.types.ElixirStorage;
import mt.grigolo.resources.types.GemStorage;
import mt.grigolo.resources.types.GoldStorage;
import mt.grigolo.troops.Army;
import mt.grigolo.troops.Troop;
import mt.grigolo.utils.LevelableObject;
import mt.grigolo.utils.Position;

import java.util.ArrayList;

public class Village extends LevelableObject {

    public final Position pos;

    private int health, maxHealth = 1000;

    private int healPerTick = 10;

    private final GoldStorage goldStorage;

    private final GemStorage gemStorage;

    private final ElixirStorage elixirStorage;

    public GoldStorage getGoldStorage() {
        return goldStorage;
    }

    public GemStorage getGemStorage() {
        return gemStorage;
    }

    public ElixirStorage getElixirStorage() {
        return elixirStorage;
    }

    public ArrayList<Building> getBuildings() {
        return villageBuildings;
    }

    private final ArrayList<Building> villageBuildings = new ArrayList<>();

    private Army army;
    private final ArrayList<Army> enemyArmies;

    public Village(int x, int y) {
        super(5, 500, 300, null);
        this.pos = new Position(x, y);
        this.health = maxHealth;
        this.setArmy(new Army(this));
        this.goldStorage = new GoldStorage(Globals.initialVillageResourceAmount, 1000);
        this.gemStorage = new GemStorage(Globals.initialVillageResourceAmount, 1000);
        this.elixirStorage = new ElixirStorage(Globals.initialVillageResourceAmount, 1000);
        super.setLevelUpResource(gemStorage);
        enemyArmies = new ArrayList<>();
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
        s += "Resources:\n";
        s += "\t Elixir: " + elixirStorage.getAmount() + "/" + elixirStorage.getMaxAmount() + "\n";
        s += "\t Gold: " + goldStorage.getAmount() + "/" + goldStorage.getMaxAmount() + "\n";
        s += "\t Gems: " + gemStorage.getAmount() + "/" + gemStorage.getMaxAmount() + "\n";
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
