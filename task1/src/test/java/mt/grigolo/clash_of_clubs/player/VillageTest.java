package mt.grigolo.clash_of_clubs.player;

import mt.grigolo.clash_of_clubs.buildings.Building;
import mt.grigolo.clash_of_clubs.buildings.resources.types.ElixirGenerator;
import mt.grigolo.clash_of_clubs.buildings.resources.types.GemGenerator;
import mt.grigolo.clash_of_clubs.buildings.resources.types.GoldGenerator;
import mt.grigolo.clash_of_clubs.buildings.troops.types.GoblinGenerator;
import mt.grigolo.clash_of_clubs.exceptions.ArmyAwayException;
import mt.grigolo.clash_of_clubs.exceptions.ArmyFullException;
import mt.grigolo.clash_of_clubs.exceptions.InsufficientResourceException;
import mt.grigolo.clash_of_clubs.exceptions.MaxLevelException;
import mt.grigolo.clash_of_clubs.players.Village;
import mt.grigolo.clash_of_clubs.troops.types.Archer;
import mt.grigolo.clash_of_clubs.troops.types.Goblin;
import mt.grigolo.clash_of_clubs.utils.Globals;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class VillageTest {

    Village villageA = new Village(4, 12);
    Village villageB = new Village(22, 31);

    @Before
    public void setUp() throws ArmyFullException {
        villageA.getArmy().addTroop(new Archer());
        villageB.getArmy().addTroop(new Goblin());
    }

    @Test
    public void testGetStorages() {
        assertEquals(Globals.initialVillageResourceAmount, villageA.getGoldStorage().getAmount());
        assertEquals(Globals.initialVillageResourceAmount, villageA.getGemStorage().getAmount());
        assertEquals(Globals.initialVillageResourceAmount, villageA.getElixirStorage().getAmount());

    }

    @Test
    public void doTick() {
        villageA.getBuildings().add(new GemGenerator(villageA));
        villageA.getBuildings().add(new GoldGenerator(villageA));
        villageA.getBuildings().add(new ElixirGenerator(villageA));

        villageA.doTick();
        assertEquals(Globals.initialVillageResourceAmount + 10, villageA.getGoldStorage().getAmount());
        assertEquals(Globals.initialVillageResourceAmount + 10, villageA.getGemStorage().getAmount());
        assertEquals(Globals.initialVillageResourceAmount + 10, villageA.getElixirStorage().getAmount());
    }

    @Test
    public void testLevelUp() {
        assertEquals(1, villageA.getLevel());
        villageA.getGemStorage().increment(1000);

        try {
            villageA.levelUp();
        } catch (InsufficientResourceException | MaxLevelException e) {
            fail();
        }

        assertEquals(Globals.initialVillageHealth + 250, villageA.getHealth());
        assertEquals(4, villageA.getArmy().getMaxTroops());
        assertEquals(2, villageA.getLevel());


    }

    @Test
    public void damage() {
        villageA.damage(100);
        assertEquals(Math.max(Globals.initialVillageHealth - 100, 0), villageA.getHealth());
    }

    @Test
    public void getHealth() {
        assertEquals(Globals.initialVillageHealth, villageA.getHealth());
    }

    @Test
    public void setHealth() {
        villageA.setHealth(500);
        assertEquals(500, villageA.getHealth());
    }

    @Test
    public void testToString() {

    }

    @Test
    public void initiateAttack() throws ArmyFullException, ArmyAwayException, InsufficientResourceException, MaxLevelException {
        Building ala = new GoblinGenerator(villageA);

        villageA.getGemStorage().setAmount(600);
        villageA.levelUp();

        ala.interact();
        ala.interact();

        villageA.getArmy().get(1).setHealth(2);

    }
}