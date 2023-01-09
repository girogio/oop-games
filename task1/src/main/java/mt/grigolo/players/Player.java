package mt.grigolo.players;

import mt.grigolo.buildings.Building;
import mt.grigolo.buildings.types.ResourceGenerator;
import mt.grigolo.buildings.types.TroopGenerator;
import mt.grigolo.resources.Resource;
import mt.grigolo.troops.Army;
import mt.grigolo.troops.Troop;
import mt.grigolo.troops.types.Archer;
import mt.grigolo.troops.types.Barbarian;
import mt.grigolo.troops.types.Goblin;

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
        if (village.getArmy().isInHomeVillage()) {
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
        for (Building buildings : village.getBuildings()) {
            buildings.doTick();
        }

        // Player input
        playerInput();

    }

    public boolean build(int building, int type) {
        Building b = null;

        switch (building) {

            case 1 -> {
                switch (type) {
                    case 1 -> b = new ResourceGenerator(village.getGemStorage(), village.getGoldStorage());
                    case 2 -> b = new ResourceGenerator(village.getGoldStorage(), village.getElixirStorage());
                    case 3 -> b = new ResourceGenerator(village.getElixirStorage(), village.getGemStorage());
                }
            }
            case 2 -> {
                Troop t = null;
                Resource r = null;
                switch (type) {
                    case 1 -> {
                        t = new Archer();
                        r = getVillage().getGemStorage();
                    }
                    case 2 -> {
                        t = new Goblin();
                        r = getVillage().getGoldStorage();
                    }
                    case 3 -> {
                        t = new Barbarian();
                        r = getVillage().getElixirStorage();
                    }
                }
                b = new TroopGenerator(t, village.getArmy(), r, 2);
            }
        }

        village.getBuildings().add(b);
        return true;


    }

}
