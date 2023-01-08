package mt.grigolo.game;

import mt.grigolo.buildings.types.ResourceGenerator;
import mt.grigolo.exceptions.ArmyFullException;
import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.exceptions.MaxLevelException;
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
    public void getGoldStorage() {
        assertEquals(100, villageA.getGoldStorage().getAmount());
    }

    @Test
    public void getGemStorage() {
        assertEquals(100, villageA.getGemStorage().getAmount());
    }

    @Test
    public void getElixirStorage() {
        assertEquals(100, villageA.getElixirStorage().getAmount());
    }

    @Test
    public void doTick() {
        villageA.getVillageBuildings().add(new ResourceGenerator(villageA.getGemStorage(), villageA.getGoldStorage()));
        villageA.getVillageBuildings().add(new ResourceGenerator(villageA.getGoldStorage(), villageA.getGoldStorage()));
        villageA.getVillageBuildings().add(new ResourceGenerator(villageA.getElixirStorage(), villageA.getGoldStorage()));

        villageA.doTick();
        assertEquals(110, villageA.getGoldStorage().getAmount());
        assertEquals(110, villageA.getGemStorage().getAmount());
        assertEquals(110, villageA.getElixirStorage().getAmount());
    }

    @Test
    public void testLevelUp() {
        assertEquals(1, villageA.levelSystem.getLevel());
        villageA.getGemStorage().increment(1000);

        try {
            villageA.levelSystem.levelUp();
        } catch (InsufficientResourceException | MaxLevelException e) {
            fail();
        }

        assertEquals(1250, villageA.getHealth());
        assertEquals(4, villageA.getArmy().maxTroops);
        assertEquals(2, villageA.levelSystem.getLevel());


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
    public void initiateAttack() {

    }
}