package mt.grigolo.resources;

import mt.grigolo.troops.Troop;

public abstract class Resource {

    private int amount;

    private int maxAmount;

    public Resource(int amount, int maxAmount) {
        this.amount = amount;
        this.maxAmount = maxAmount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public void increment(int amount) {
        if(this.amount + amount > maxAmount) {
            this.amount = maxAmount;
        } else {
            this.amount += amount;
        }
    }

    public void decrement(int amount) {
        if(this.amount - amount < 0) {
            this.amount = 0;
        } else {
            this.amount -= amount;
        }
    }

    public void giveToTroop(Troop troop, int amountToSend) {
        if (troop.getInventory().getClass().equals(this.getClass())) {
            troop.getInventory().increment(amountToSend);
            this.decrement(amountToSend);
        }
    }

    public void takeFromTroop(Troop troop, int amountToTake) {
        if (troop.getInventory().getClass() == this.getClass()) {
            this.increment(amountToTake);
            troop.getInventory().decrement(amountToTake);
        }
    }

    public abstract String getName();
}
