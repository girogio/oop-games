package mt.grigolo.players;

public abstract class Player {

    Village village;

    public Player(int x, int y) {
        village = new Village(x, y);
    }

    public boolean isAlive() {
        return village.getHealth() > 0;
    }

    public Village getVillage() {
        return village;
    }

    public abstract void playerInput();


    public void doTurn() {

        village.getArmy().emptyInventory();

        playerInput();

    }

}
