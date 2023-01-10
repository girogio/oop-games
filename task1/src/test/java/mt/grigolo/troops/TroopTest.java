package mt.grigolo.troops;

import mt.grigolo.Globals;
import mt.grigolo.troops.types.Archer;
import mt.grigolo.troops.types.Barbarian;
import mt.grigolo.troops.types.Goblin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TroopTest {

    private Archer archer;
    private Barbarian barbarian;
    private Goblin goblin;

    @Before
    public void setUp() {
        archer = new Archer();
        goblin = new Goblin();
        barbarian = new Barbarian();
    }

    @Test
    public void testGetHealth() {
        assertEquals(goblin.getHealth(), Globals.goblinHealth);
        assertEquals(archer.getHealth(), Globals.archerHealth);
        assertEquals(barbarian.getHealth(), Globals.barbarianHealth);
    }

    @Test
    public void testGetMaxHealth() {
        assertEquals(goblin.getMaxHealth(), Globals.goblinHealth);
        assertEquals(archer.getMaxHealth(), Globals.archerHealth);
        assertEquals(barbarian.getMaxHealth(), Globals.barbarianHealth);
    }

    @Test
    public void testGetAttack() {
        assertEquals(goblin.getAttack(), Globals.goblinDamage);
        assertEquals(archer.getAttack(), Globals.archerDamage);
        assertEquals(barbarian.getAttack(), Globals.barbarianDamage);
    }

    @Test
    public void testGetCost() {
        assertEquals(goblin.getCost(), Globals.goblinCost);
        assertEquals(archer.getCost(), Globals.archerCost);
        assertEquals(barbarian.getCost(), Globals.barbarianCost);
    }

    @Test
    public void testGetMaxCapacity() {
        assertEquals(goblin.getInventory().getMaxAmount(), Globals.goblinMaxInventory);
        assertEquals(archer.getInventory().getMaxAmount(), Globals.archerMaxInventory);
        assertEquals(barbarian.getInventory().getMaxAmount(), Globals.barbarianMaxInventory);
    }

    @Test
    public void testSetCapacity() {
        archer.getInventory().setAmount(5);
        assertEquals(5, archer.getInventory().getAmount());
        barbarian.getInventory().setAmount(25);
        assertEquals(25, barbarian.getInventory().getAmount());
        goblin.getInventory().setAmount(50);
        assertEquals(50, goblin.getInventory().getAmount());
    }

    @Test
    public void setHealth() {
        archer.setHealth(50);
        assertEquals(archer.getHealth(), 50);
        barbarian.setHealth(75);
        assertEquals(barbarian.getHealth(), 75);
        goblin.setHealth(25);
        assertEquals(goblin.getHealth(), 25);
    }

    @Test
    public void testGetCurrentCapacity() {
        assertEquals(archer.getInventory().getAmount(), 0);
        assertEquals(barbarian.getInventory().getAmount(), 0);
        assertEquals(goblin.getInventory().getAmount(), 0);
    }

    @Test
    public void testGetMarchingSpeed() {
        assertEquals(archer.getMarchingSpeed(), Globals.archerSpeed);
        assertEquals(barbarian.getMarchingSpeed(), Globals.barbarianSpeed);
        assertEquals(goblin.getMarchingSpeed(), Globals.goblinSpeed);
    }

    @Test
    public void testFight() {
        archer.fight(barbarian);
        assertEquals(archer.getHealth(), Globals.archerHealth - Globals.barbarianDamage);
        assertEquals(barbarian.getHealth(), Globals.barbarianHealth - Globals.archerDamage);
        assertFalse(archer.isDead());
    }

}