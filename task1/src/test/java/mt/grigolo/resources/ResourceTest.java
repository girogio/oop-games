package mt.grigolo.resources;

import mt.grigolo.exceptions.NegativeResourceException;
import mt.grigolo.resources.types.ElixirStorage;
import mt.grigolo.resources.types.GemStorage;
import mt.grigolo.resources.types.GoldStorage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResourceTest {

    private Resource gemStorage;
    private Resource elixirStorage;
    private Resource goldStorage;

    @Before
    public void setUp() {
        gemStorage = new GemStorage(100, 1000);
        elixirStorage = new ElixirStorage(100, 1000);
        goldStorage = new GoldStorage(100, 1000);
    }

    @Test
    public void testGemStorage() {
        assertEquals(100, gemStorage.getAmount());
        assertEquals(1000, gemStorage.getMaxAmount());
        assertEquals("Gem", gemStorage.getName(false));
        assertEquals("Gems", gemStorage.getName(true));
    }

    @Test
    public void testElixirStorage() {
        assertEquals(100, elixirStorage.getAmount());
        assertEquals(1000, elixirStorage.getMaxAmount());
        assertEquals("Elixir", elixirStorage.getName(false));
        assertEquals("Elixir", elixirStorage.getName(true));
    }

    @Test
    public void testGoldStorage() {
        assertEquals(100, goldStorage.getAmount());
        assertEquals(1000, goldStorage.getMaxAmount());
        assertEquals("Gold", goldStorage.getName(false));
        assertEquals("Gold", goldStorage.getName(true));
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
    public void testGetName() {
        assertEquals("Gem", gemStorage.getName(false));
        assertEquals("Gems", gemStorage.getName(true));
        assertEquals("Elixir", elixirStorage.getName(false));
        assertEquals("Elixir", elixirStorage.getName(true));
        assertEquals("Gold", goldStorage.getName(false));
        assertEquals("Gold", goldStorage.getName(true));
    }

    @Test
    public void testResource() {
        Resource resource = new Resource(100, 1000) {
            @Override
            public void transferTo(Resource resource, int amount) {

            }

            @Override
            public String getName(boolean plural) {
                return plural ? "Resources" : "Resource";
            }
        };
        assertEquals(100, resource.getAmount());
        assertEquals(1000, resource.getMaxAmount());
        assertEquals("Resource", resource.getName(false));
        assertEquals("Resources", resource.getName(true));
    }

    @Test
    public void testAdd() {
        gemStorage.increment(100);
        assertEquals(200, gemStorage.getAmount());
        elixirStorage.increment(100);
        assertEquals(200, elixirStorage.getAmount());
        goldStorage.increment(100);
        assertEquals(200, goldStorage.getAmount());
    }

    @Test
    public void testRemove() {
        gemStorage.decrement(50);
        assertEquals(50, gemStorage.getAmount());
        elixirStorage.decrement(50);
        assertEquals(50, elixirStorage.getAmount());
        goldStorage.decrement(50);
        assertEquals(50, goldStorage.getAmount());
    }

    @Test
    public void testTransfer() {
        GemStorage gemStorage2 = new GemStorage(0, 1000);
        GoldStorage goldStorage2 = new GoldStorage(0, 1000);
        ElixirStorage elixirStorage2 = new ElixirStorage(0, 1000);

        try {
            gemStorage.transferTo(gemStorage2, 50);
            assertEquals(50, gemStorage2.getAmount());
            assertEquals(50, gemStorage.getAmount());
            goldStorage.transferTo(goldStorage2, 50);
            assertEquals(50, goldStorage2.getAmount());
            assertEquals(50, goldStorage.getAmount());
            elixirStorage.transferTo(elixirStorage2, 50);
            assertEquals(50, elixirStorage2.getAmount());
            assertEquals(50, elixirStorage.getAmount());
        } catch (NegativeResourceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fullTransfer() {
        GemStorage gemStorage2 = new GemStorage(0, 1000);
        GoldStorage goldStorage2 = new GoldStorage(0, 1000);
        ElixirStorage elixirStorage2 = new ElixirStorage(0, 1000);

        gemStorage.transferTo(gemStorage2);
        assertEquals(100, gemStorage2.getAmount());
        assertEquals(0, gemStorage.getAmount());
        goldStorage.transferTo(goldStorage2);
        assertEquals(100, goldStorage2.getAmount());
        assertEquals(0, goldStorage.getAmount());
        elixirStorage.transferTo(elixirStorage2);
        assertEquals(100, elixirStorage2.getAmount());
        assertEquals(0, elixirStorage.getAmount());

    }

    @Test
    public void testTransferInsufficientResource(){
        GemStorage gemStorage2 = new GemStorage(0, 1000);
        GoldStorage goldStorage2 = new GoldStorage(0, 1000);
        ElixirStorage elixirStorage2 = new ElixirStorage(0, 1000);

        try {
            gemStorage.transferTo(gemStorage2, 200);
        } catch (NegativeResourceException e) {
            assertEquals(100, gemStorage.getAmount());
            assertEquals(0, gemStorage2.getAmount());
        }
        try {
            goldStorage.transferTo(goldStorage2, 200);
        } catch (NegativeResourceException e) {
            assertEquals(100, goldStorage.getAmount());
            assertEquals(0, goldStorage2.getAmount());
        }
        try {
            elixirStorage.transferTo(elixirStorage2, 200);
        } catch (NegativeResourceException e) {
            assertEquals(100, elixirStorage.getAmount());
            assertEquals(0, elixirStorage2.getAmount());
        }
    }

}