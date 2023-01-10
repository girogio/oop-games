package mt.grigolo.resources;

import mt.grigolo.resources.types.Elixir;
import mt.grigolo.resources.types.Gem;
import mt.grigolo.resources.types.Gold;
import mt.grigolo.troops.Troop;
import mt.grigolo.troops.types.Archer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResourceTest {

    private Resource gemStorage;
    private Resource elixirStorage;
    private Resource goldStorage;

    @Before
    public void setUp() {
        gemStorage = new Gem(100, 1000);
        elixirStorage = new Elixir(100, 1000);
        goldStorage = new Gold(100, 1000);
    }

    @Test
    public void testGemStorage() {
        assertEquals(100, gemStorage.getAmount());
        assertEquals(1000, gemStorage.getMaxAmount());
    }

    @Test
    public void testElixirStorage() {
        assertEquals(100, elixirStorage.getAmount());
        assertEquals(1000, elixirStorage.getMaxAmount());
    }

    @Test
    public void testGoldStorage() {
        assertEquals(100, goldStorage.getAmount());
        assertEquals(1000, goldStorage.getMaxAmount());
    }


    @Test
    public void testSetAmount() {
        gemStorage.setAmount(200);
        assertEquals(200, gemStorage.getAmount());
        elixirStorage.setAmount(200);
        assertEquals(200, elixirStorage.getAmount());
        goldStorage.setAmount(200);
        assertEquals(200, goldStorage.getAmount());
    }

    @Test
    public void testSetMaxAmount() {
        gemStorage.setMaxAmount(2000);
        assertEquals(2000, gemStorage.getMaxAmount());
        elixirStorage.setMaxAmount(2000);
        assertEquals(2000, elixirStorage.getMaxAmount());
        goldStorage.setMaxAmount(2000);
        assertEquals(2000, goldStorage.getMaxAmount());
    }

    @Test
    public void testResource() {
        Resource resource = new Gold(100, 1000);
        assertEquals(100, resource.getAmount());
        assertEquals(1000, resource.getMaxAmount());
    }

    @Test
    public void testIncrement() {
        gemStorage.increment(100);
        assertEquals(200, gemStorage.getAmount());
        elixirStorage.increment(100);
        assertEquals(200, elixirStorage.getAmount());
        goldStorage.increment(100);
        assertEquals(200, goldStorage.getAmount());
    }

    @Test
    public void testDecrement() {
        gemStorage.decrement(50);
        assertEquals(50, gemStorage.getAmount());
        elixirStorage.decrement(50);
        assertEquals(50, elixirStorage.getAmount());
        goldStorage.decrement(50);
        assertEquals(50, goldStorage.getAmount());
        goldStorage.decrement(100);
        assertEquals(0, goldStorage.getAmount());
    }

    @Test
    public void testGiveToTroop() {
        Troop t = new Archer();
        gemStorage.giveToTroop(t, 1);
        assertEquals(1, t.getInventory().getAmount());
    }

}