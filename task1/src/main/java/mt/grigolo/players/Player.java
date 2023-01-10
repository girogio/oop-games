package mt.grigolo.players;

import mt.grigolo.buildings.Building;
import mt.grigolo.buildings.resources.types.ElixirGenerator;
import mt.grigolo.buildings.resources.types.GemGenerator;
import mt.grigolo.buildings.resources.types.GoldGenerator;
import mt.grigolo.buildings.troops.types.ArcherGenerator;
import mt.grigolo.buildings.troops.types.BarbarianGenerator;
import mt.grigolo.buildings.troops.types.GoblinGenerator;
import mt.grigolo.exceptions.InsufficientResourceException;

public abstract class Player {

    Village village;

    private final int id;

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
            getVillage().getArmy().forEach(t -> village.takeFromTroop(t, t.getInventory().getAmount()));
        }

        // Enemy troop arrival
        for (int i = 0; i < village.getEnemyArmies().size(); i++) {
            if (village.getEnemyArmies().get(i).isInRange(village)) {
                if (village.getEnemyArmies().get(i).attack(village)) {
                    village.getEnemyArmies().remove(i);
                    i--;
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

    public void build(int building, int type) throws InsufficientResourceException {

        Building b;

        switch (building) {
            case 1 -> {
                switch (type) {
                    case 1 -> b = new GemGenerator(getVillage());
                    case 2 -> b = new GoldGenerator(getVillage());
                    case 3 -> b = new ElixirGenerator(getVillage());
                    default -> {
                        return;
                    }
                }
            }

            case 2 -> {
                switch (type) {
                    case 1 -> b = new ArcherGenerator(getVillage());
                    case 2 -> b = new GoblinGenerator(getVillage());
                    case 3 -> b = new BarbarianGenerator(getVillage());
                    default -> {
                        return;
                    }
                }
            }

            default -> {
                return;
            }

        }

        try {
            village.buyBuilding(b);
        } catch (InsufficientResourceException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getId() {
        return String.valueOf(id);
    }

}
