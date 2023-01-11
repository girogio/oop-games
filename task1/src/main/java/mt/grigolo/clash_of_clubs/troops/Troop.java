package mt.grigolo.clash_of_clubs.troops;

import mt.grigolo.clash_of_clubs.resources.Resource;

public abstract class Troop {

    private int health;

    private final int maxHealth;

    private final int cost;

    private final int attack;

    private final int marchingSpeed;


    public int getHealth() {
        return health;
    }


    public int getCost() {
        return cost;
    }


    public int getAttack() {
        return attack;
    }


    public int getMarchingSpeed() {
        return marchingSpeed;
    }


    public int getMaxHealth() {
        return maxHealth;
    }

    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
    }

    private final Resource inventory;

    public Troop(int cost, int attack, Resource resourceType, int marchingSpeed, int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.cost = cost;
        this.inventory = resourceType;
        this.attack = attack;
        this.marchingSpeed = marchingSpeed;
    }

    public Resource getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        String s = getClass().getSimpleName() + " (" + health + "/" + maxHealth + "hp, ";
        s += "Attack: " + attack + ", ";
        s += this.getInventory() + ", ";
        s += "Speed: " + marchingSpeed + ")";
        return s;
    }

    public void fight(Troop enemyTroop) {
        this.health = Math.max(0, this.health - enemyTroop.attack);
        enemyTroop.health = Math.max(0, enemyTroop.health - this.attack);
    }

    public boolean isDead() {
        return health <= 0;
    }

}
