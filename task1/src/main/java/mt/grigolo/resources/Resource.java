package mt.grigolo.resources;

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

    public void add(int amount) {
        this.amount += amount;
    }

    public void remove(int amount) {
        this.amount -= amount;
    }

    public abstract String getName(boolean plural) ;
}
