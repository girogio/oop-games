package mt.grigolo.troops;

import mt.grigolo.troops.types.Archer;
import mt.grigolo.troops.types.Barbarian;
import mt.grigolo.troops.types.Goblin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        assertEquals(goblin.getHealth(), 75);
        assertEquals(archer.getHealth(), 100);
        assertEquals(barbarian.getHealth(), 150);
    }

    @Test
    public void testGetMaxHealth() {
        assertEquals(goblin.getMaxHealth(), 75);
        assertEquals(archer.getMaxHealth(), 100);
        assertEquals(barbarian.getMaxHealth(), 150);
    }

    @Test
    public void testGetAttack() {
        assertEquals(goblin.getAttack(), 50);
        assertEquals(archer.getAttack(), 100);
        assertEquals(barbarian.getAttack(), 150);
    }

    @Test
    public void testGetCost() {
        assertEquals(goblin.getCost(), 50);
        assertEquals(archer.getCost(), 100);
        assertEquals(barbarian.getCost(), 100);
    }

    @Test
    public void testGetMaxCapacity() {
        assertEquals(archer.getInventory().getMaxAmount(), 10);
        assertEquals(barbarian.getInventory().getMaxAmount(), 50);
        assertEquals(goblin.getInventory().getMaxAmount(), 100);
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
        assertEquals(barbarian.getMarchingSpeed(), 5);
        assertEquals(goblin.getMarchingSpeed(), 7);
        assertEquals(archer.getMarchingSpeed(), 10);
    }


}