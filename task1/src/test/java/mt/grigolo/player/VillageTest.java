package mt.grigolo.player;

import mt.grigolo.utils.Globals;
import mt.grigolo.buildings.Building;
import mt.grigolo.buildings.resources.types.ElixirGenerator;
import mt.grigolo.buildings.resources.types.GemGenerator;
import mt.grigolo.buildings.resources.types.GoldGenerator;
import mt.grigolo.buildings.troops.types.GoblinGenerator;
import mt.grigolo.exceptions.ArmyAwayException;
import mt.grigolo.exceptions.ArmyFullException;
import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.exceptions.MaxLevelException;
import mt.grigolo.players.Village;
import mt.grigolo.troops.types.Archer;
import mt.grigolo.troops.types.Goblin;
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

        assertEquals(1250, villageA.getHealth());
        assertEquals(4, villageA.getArmy().getMaxTroops());
        assertEquals(2, villageA.getLevel());


    }

    @Test
    public void damage() {
        villageA.damage(100);
        assertEquals(900, villageA.getHealth());
    }

    @Test
    public void getHealth() {
        assertEquals(1000, villageA.getHealth());
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

        System.out.println(villageA);
    }
}