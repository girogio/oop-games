package mt.grigolo.troops;

import mt.grigolo.utils.Globals;
import mt.grigolo.exceptions.*;
import mt.grigolo.players.Village;
import mt.grigolo.troops.types.Archer;
import mt.grigolo.troops.types.Barbarian;
import mt.grigolo.troops.types.Goblin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ArmyTest {
    Village v;
    Army army;

    @Before
    public void setUp() {
        v = new Village(0, 0);
        army = new Army(v);

        Archer archer = new Archer();
        Barbarian barbarian = new Barbarian();
        Goblin goblin = new Goblin();

        archer.getInventory().setAmount(archer.getInventory().getMaxAmount());
        barbarian.getInventory().setAmount(barbarian.getInventory().getMaxAmount());
        goblin.getInventory().setAmount(goblin.getInventory().getMaxAmount());

        v.getArmy().add(archer);
        v.getArmy().add(barbarian);
        v.getArmy().add(goblin);
    }

    @Test
    public void emptyInventory() {

        assertEquals(Globals.initialVillageResourceAmount, v.getGemStorage().getAmount());

        v.getArmy().emptyInventory();

        for (Troop t : v.getArmy()) {
            Assert.assertEquals(0, t.getInventory().getAmount());
        }

        assertEquals(110, v.getGemStorage().getAmount());
        assertEquals(110, v.getGoldStorage().getAmount());
        assertEquals(100, v.getElixirStorage().getAmount());
    }

    @Test
    public void testArmyFull() {

    }

    @Test
    public void marchTest() throws ArmyFullException, ArmyBusyException, ArmyEmptyException {
        Village v1 = new Village(0, 0);
        Village v2 = new Village(10, 1);

        v1.getArmy().addTroop(new Archer());

        v1.getArmy().initiateAttack(v2);

        assertEquals(5, v1.getArmy().getTicksUntilArrival());

        v1.getArmy().march();

        assertEquals(4, v1.getArmy().getTicksUntilArrival());

        assertFalse(v1.getArmy().isInRange(v2));
    }


    @Test
    public void initiateAttack() throws ArmyFullException, ArmyBusyException, ArmyEmptyException {
        Village v1 = new Village(0, 0);
        Village v2 = new Village(10, 1);
        v1.getArmy().addTroop(new Archer());
        v1.getArmy().initiateAttack(v2);

        assertFalse(v1.getArmy().isInRange(v2));

        assertEquals(5, v1.getArmy().getTicksUntilArrival());

        v1.getArmy().march();
        assertFalse(v1.getArmy().isInRange(v2));

        assertEquals(4, v1.getArmy().getTicksUntilArrival());
        assertEquals(1, v2.getEnemyArmies().size());
        assertEquals(v1.getArmy(), v2.getEnemyArmies().get(0));
    }
}