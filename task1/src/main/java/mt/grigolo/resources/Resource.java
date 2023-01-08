package mt.grigolo.resources;

import mt.grigolo.exceptions.NegativeResourceException;

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
        this.amount += amount;
    }

    public void decrement(int amount) {
        this.amount -= amount;
    }


    public void transferTo(Resource resource, int amountToSend) throws NegativeResourceException {
        if (this.getClass().equals(resource.getClass())) {
            if (this.amount >= amountToSend) {
                this.amount -= amountToSend;
                resource.amount += amountToSend;
            } else {
                throw new NegativeResourceException();
            }
        }
    }

    public void transferTo(Resource resource) {
        if (this.getClass().equals(resource.getClass())) {
            resource.amount += this.amount;
            this.amount = 0;
        }
    }

    public abstract String getName(boolean plural);
}
