package mt.grigolo.troops;

public abstract class Troop {

    private int health;

    private final int maxHealth;

    private final int cost;

    private final int attack;

    private final int maxCapacity;

    private int capacity;

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


    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getMarchingSpeed() {
        return marchingSpeed;
    }

    public int getCurrentCapacity() {
        return capacity;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
    }

    public void setCapacity(int currentCapacity) {
        this.capacity = currentCapacity;
    }

    public Troop(int cost, int attack, int maxCapacity, int marchingSpeed, int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.cost = cost;
        this.attack = attack;
        this.maxCapacity = maxCapacity;
        this.capacity = 0;
        this.marchingSpeed = marchingSpeed;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Cost: " + getCost() + "\n";
        s += "Health: " + getHealth() + "/" + getMaxHealth();
        s += "Attack: " + getAttack() + "\n";
        s += "Capacity: " + getCurrentCapacity() + "/" + getMaxCapacity() + "\n";
        s += "Speed: " + getMarchingSpeed();
        return s;
    }


}
