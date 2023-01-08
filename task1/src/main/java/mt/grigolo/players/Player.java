package mt.grigolo.players;

import mt.grigolo.buildings.Building;
import mt.grigolo.troops.Army;

public abstract class Player {

    Village village;

    private int id;

    public Player(int x, int y, int id) {
        village = new Village(x, y);
        this.id = id;
    }

    public boolean isAlive() {
        return village.getHealth() > 0;
    }

    public Village getVillage() {
        return village;
    }

    public abstract void playerInput();


    public void doTurn() {

        // Friendly troop arrival
        if (village.getArmy().isInHomeVillage()){
            village.getArmy().emptyInventory();
        }

        // Enemy troop arrival
        for (Army enemyArmy : village.getEnemyArmies()) {
            if (enemyArmy.isInRange(village)) {
                if (enemyArmy.attack(village)) {
                    village.getEnemyArmies().remove(enemyArmy);
                    enemyArmy.setDestination(enemyArmy.getSourceVillage());
                }
            }
        }

        // Resource gathering
        for (Building buildings: village.getBuildings()) {
            buildings.doTick();
        }

        // Player input
        playerInput();

    }

}
