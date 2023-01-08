package mt.grigolo.game;

import mt.grigolo.buildings.Building;
import mt.grigolo.resources.types.ElixirStorage;
import mt.grigolo.resources.types.GemStorage;
import mt.grigolo.resources.types.GoldStorage;
import mt.grigolo.troops.Army;
import mt.grigolo.utils.LevelSystem;
import mt.grigolo.utils.Position;

import java.util.ArrayList;

public class Village {

    public final Position pos;

    private int health, maxHealth = 1000;

    private int healPerTick = 10;

    private final GoldStorage goldStorage = new GoldStorage(100, 1000);

    private final GemStorage gemStorage = new GemStorage(100, 1000);

    private final ElixirStorage elixirStorage = new ElixirStorage(100, 1000);

    public GoldStorage getGoldStorage() {
        return goldStorage;
    }

    public GemStorage getGemStorage() {
        return gemStorage;
    }

    public ElixirStorage getElixirStorage() {
        return elixirStorage;
    }

    public ArrayList<Building> getVillageBuildings() {
        return villageBuildings;
    }

    private final ArrayList<Building> villageBuildings = new ArrayList<>();

    private Army army;

    public LevelSystem levelSystem;

    public Village(int x, int y) {

        this.pos = new Position(x, y);
        this.health = maxHealth;
        this.setArmy(new Army(this));
        this.levelSystem = new LevelSystem(gemStorage, 5, 500, 300) {
            @Override
            public void levelUpLogic() {
                maxHealth += 250;
                health += 250;
                army.maxTroops += 2;
                healPerTick += 5;
            }
        };

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
        s += "Lvl. " + levelSystem.getLevel() + " village at (" + pos.getX() + ", " + pos.getY() + ") with " + health + "/" + maxHealth + "hp\n";
        s += "Army:\n";
        s += army;
        s += "Resources:\n";
        s += "\t Elixir: " + elixirStorage.getAmount() + "/" + elixirStorage.getMaxAmount() + "\n";
        s += "\t Gold: " + goldStorage.getAmount() + "/" + goldStorage.getMaxAmount() + "\n";
        s += "\t Gems: " + gemStorage.getAmount() + "/" + gemStorage.getMaxAmount() + "\n";
        return s;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    //floor of log3 base 4 is
}
