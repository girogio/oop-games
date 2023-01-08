package mt.grigolo.troops;

import mt.grigolo.exceptions.ArmyFullException;
import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.exceptions.MaxLevelException;
import mt.grigolo.exceptions.NegativeResourceException;
import mt.grigolo.game.Village;
import mt.grigolo.troops.types.Archer;
import mt.grigolo.troops.types.Barbarian;
import mt.grigolo.troops.types.Goblin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

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

        archer.getInventory().setAmount(50);
        barbarian.getInventory().setAmount(50);
        goblin.getInventory().setAmount(50);

        v.getArmy().getTroops().add(archer);
        v.getArmy().getTroops().add(barbarian);
        v.getArmy().getTroops().add(goblin);
    }

    @Test
    public void emptyInventory() throws NegativeResourceException, ArmyFullException, MaxLevelException, InsufficientResourceException {

        v.getGemStorage().increment(1000);
        v.levelSystem.levelUp();

        assertEquals(600, v.getGemStorage().getAmount());


        v.getArmy().emptyInventory();

        for (Troop t : v.getArmy().getTroops()) {
            Assert.assertEquals(0, t.getInventory().getAmount());
        }

        assertEquals(650, v.getGemStorage().getAmount());
        assertEquals(150, v.getGoldStorage().getAmount());
        assertEquals(150, v.getElixirStorage().getAmount());
    }

    @Test
    public void testArmyFull() {

    }
}