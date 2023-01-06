package mt.grigolo.troops;

import mt.grigolo.troops.types.Archer;
import mt.grigolo.troops.types.Barbarian;
import mt.grigolo.troops.types.Goblin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertEquals(goblin.getHealth(), 75);
        Assert.assertEquals(archer.getHealth(), 100);
        Assert.assertEquals(barbarian.getHealth(), 150);
    }

    @Test
    public void testGetMaxHealth() {
        Assert.assertEquals(goblin.getMaxHealth(), 75);
        Assert.assertEquals(archer.getMaxHealth(), 100);
        Assert.assertEquals(barbarian.getMaxHealth(), 150);
    }

    @Test
    public void testGetAttack() {
        Assert.assertEquals(goblin.getAttack(), 50);
        Assert.assertEquals(archer.getAttack(), 100);
        Assert.assertEquals(barbarian.getAttack(), 150);
    }

    @Test
    public void testGetCost() {
        Assert.assertEquals(goblin.getCost(), 50);
        Assert.assertEquals(archer.getCost(), 100);
        Assert.assertEquals(barbarian.getCost(), 100);
    }

    @Test
    public void testGetMaxCapacity() {
        Assert.assertEquals(archer.getMaxCapacity(), 10);
        Assert.assertEquals(barbarian.getMaxCapacity(), 50);
        Assert.assertEquals(goblin.getMaxCapacity(), 100);
    }

    @Test
    public void testSetCapacity() {
        archer.setCapacity(5);
        Assert.assertEquals(archer.getCurrentCapacity(), 5);
        barbarian.setCapacity(25);
        Assert.assertEquals(barbarian.getCurrentCapacity(), 25);
        goblin.setCapacity(50);
        Assert.assertEquals(goblin.getCurrentCapacity(), 50);
    }

    @Test
    public void setHealth() {
        archer.setHealth(50);
        Assert.assertEquals(archer.getHealth(), 50);
        barbarian.setHealth(75);
        Assert.assertEquals(barbarian.getHealth(), 75);
        goblin.setHealth(25);
        Assert.assertEquals(goblin.getHealth(), 25);
    }

    @Test
    public void testGetCurrentCapacity() {
        Assert.assertEquals(archer.getCurrentCapacity(), 0);
        Assert.assertEquals(barbarian.getCurrentCapacity(), 0);
        Assert.assertEquals(goblin.getCurrentCapacity(), 0);
    }

    @Test
    public void testGetMarchingSpeed() {
        Assert.assertEquals(barbarian.getMarchingSpeed(), 5);
        Assert.assertEquals(goblin.getMarchingSpeed(), 7);
        Assert.assertEquals(archer.getMarchingSpeed(), 10);
    }

    @Test
    public void testToString() {
        String s = "";
        s += "Cost: " + archer.getCost() + "\n";
        s += "Health: " + archer.getHealth() + "/" + archer.getMaxHealth();
        s += "Attack: " + archer.getAttack() + "\n";
        s += "Capacity: " + archer.getCurrentCapacity() + "/" + archer.getMaxCapacity() + "\n";
        s += "Speed: " + archer.getMarchingSpeed();
        Assert.assertEquals(archer.toString(), s);
    }
}