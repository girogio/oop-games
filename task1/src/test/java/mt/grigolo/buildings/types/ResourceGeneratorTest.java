package mt.grigolo.buildings.types;

import mt.grigolo.buildings.resources.ResourceGenerator;
import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.exceptions.MaxLevelException;
import mt.grigolo.players.Village;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResourceGeneratorTest {

    Village v;
    ResourceGenerator resourceGenerator;

    @Before
    public void setUp() {
        v = new Village(0, 0);
        resourceGenerator = new ResourceGenerator(v.getGemStorage(), v.getGemStorage());
    }

    @Test
    public void levelUpLogic() throws MaxLevelException, InsufficientResourceException {

        resourceGenerator.levelUp();
        assertEquals(2, resourceGenerator.getLevel());
    }

    @Test
    public void testTick() {
        resourceGenerator.interact();
        resourceGenerator.doTick();
    }
}